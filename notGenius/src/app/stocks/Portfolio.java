package app.stocks;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Iterator;

public class Portfolio extends Activity
{
	public ArrayList<ShareSet> myShares = Main.sharePortfolio;	
	Iterator<ShareSet> iterator = myShares.iterator();
	ShareSet mySet;
	
	EditText BP, HSBC, EXP , MS, SN, GT, BLVN;

	WebReader reader;
	
	int counter;
	double sharePrice;	
	
	float grandTotal;
	float total;

	String input;
	String result;

	OnClickListener refreshPortfolioListener = new OnClickListener()
	{
		public void onClick(View v)
		{
			refreshPage();
		}
	};

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.portfolio);
		((ImageView) findViewById(R.id.refresh)).setOnClickListener(refreshPortfolioListener);
		refreshPage();
	}

	/*
	 * Refreshes all Portfolio values
	 */
	public void refreshPage()
	{
		counter = 0;
		grandTotal = 0;
		
		BLVN = (EditText)findViewById(R.id.textBW);
		BP = (EditText)findViewById(R.id.textBP);
		HSBC = (EditText)findViewById(R.id.textHSBC);
		EXP = (EditText)findViewById(R.id.textEXP);
		MS = (EditText)findViewById(R.id.textMS);
		SN = (EditText)findViewById(R.id.textSN);
		GT = (EditText)findViewById(R.id.textGT);

		EditText [] symbol1 = {BLVN, BP, EXP, HSBC, MS, SN};
		
		while(iterator.hasNext())
		{
			mySet = iterator.next();			
			reader = new WebReader(mySet.getStockURL());
			sharePrice = reader.getPrice();
			total = (float)(sharePrice * mySet.getQuantity());
			grandTotal += total;
			result = String.format("%.0f", total/100);
			symbol1[counter].setText(String.valueOf(sharePrice) + "    x    " + mySet.getQuantity() + " shares " + "   =   £" + result);
			counter++;
		}
		
		result = String.format("%.0f", grandTotal/100);
		GT.setText("£" + result);
	}
}





