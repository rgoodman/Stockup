

import android.app.Activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import jass.shares.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.text.NumberFormat;
import java.math.*;

public class Portfolio extends Activity
{
	public ArrayList<ShareSet> myShares = Main.sharePortfolio;	
	Iterator<ShareSet> iterator = myShares.iterator();
	ShareSet mySet;

	TextView GT, fieldID;

	WebReader reader;
	
	double sharePrice;	
	
	float grandTotal;
	float shareSetTotal;

	String input;
	String total;

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
		//((ImageView) findViewById(R.id.refresh)).setOnClickListener(refreshPortfolioListener);
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
			fieldID = (TextView)findViewById(mySet.getPortfolioFieldID());
			reader = new WebReader(mySet.getStockURL());
			input = reader.readLine();
			sharePrice = reader.getCurrentPrice(input);
			shareSetTotal = (float)(sharePrice * mySet.getQuantity());
			grandTotal += shareSetTotal;
			total = formatCurrency((shareSetTotal/100));
			fieldID.setText(String.valueOf(sharePrice) + "    x    " + mySet.getQuantity() + " shares " + "   =   " + total);
		}
		
		GT.setText(formatCurrency(grandTotal/100));
	}
	
	private String formatCurrency(double value)
	{
		BigDecimal myDecimal = new BigDecimal(value);
		myDecimal.setScale(0, BigDecimal.ROUND_DOWN);
		
	    NumberFormat myFormat = NumberFormat.getCurrencyInstance(Locale.UK);
	    String output = myFormat.format(myDecimal);
	    return output;
	}
	
	public int multiply(int x)
	{
		x= x*10;
		return x;
	}
}





