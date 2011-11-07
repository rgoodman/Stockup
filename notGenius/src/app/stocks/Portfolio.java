package app.stocks;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

public class Portfolio extends Activity
{
	public ArrayList<ShareSet> myShares = Main.sharePortfolio;	
	Iterator<ShareSet> iterator = myShares.iterator();
	ShareSet mySet;

	TextView GT, textField;

	WebReader reader;
	
	double sharePrice;	
	
	float grandTotal;
	float shareSetTotal;

	String input;
	String result;

	OnClickListener refreshPortfolioListener = new OnClickListener()
	{
		public void onClick(View v)
		{
			refreshPortfolioPage();
		}
	};

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.portfolio);
		((ImageView) findViewById(R.id.refresh)).setOnClickListener(refreshPortfolioListener);
		refreshPortfolioPage();
	}

	/*
	 * Refreshes all Portfolio values
	 */
	public void refreshPortfolioPage()
	{
		grandTotal = 0;		
		GT = (TextView)findViewById(R.id.textGT);
		
		while(iterator.hasNext())
		{
			mySet = iterator.next();
			textField = mySet.getTextFieldName();
			textField = (TextView)findViewById(mySet.getTextFieldContent());
			reader = new WebReader(mySet.getStockURL());
			input = reader.readLine();
			sharePrice = reader.getCurrentPrice(input);
			shareSetTotal = (float)(sharePrice * mySet.getQuantity());
			grandTotal += shareSetTotal;
			result = String.format("%.0f", shareSetTotal/100);
			textField.setText(String.valueOf(sharePrice) + "    x    " + mySet.getQuantity() + " shares " + "   =   £" + result);
		}
		
		result = String.format("%.0f", grandTotal/100);
		GT.setText("£" + result);
	}
}





