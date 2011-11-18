package app.stocks;

import android.app.Activity;
import android.content.Context;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.text.DecimalFormat;
import java.math.*;

public class Portfolio extends Activity
{
	public ArrayList<ShareSet> myShares = Main.sharePortfolio;	
	Iterator<ShareSet> iterator = myShares.iterator();
	ShareSet mySet;

	TextView connectionStatus, GT, fieldID;

	WebReader reader;
	
	double sharePrice;	
	
	float grandTotal;
	float shareSetTotal;

	String input;
	String total;
	
	private String pattern = "£###,###,###,###"; //The String pattern used for formatting the display of currency.

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
		connectionStatus = (TextView)findViewById(R.id.connectionStatus);

		if (HaveNetworkConnection() == true)
		{
			refreshPortfolioPage();
		}
		else
		{
			connectionStatus.setText("Internet : Not Connected");		
		}
	}

	/**
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
	
	/**
	 * formatCurrency
	 * 
	 * Returns a formatted String representing the currency value of shares.
	 *  
	 * @param value a double representing the currency value to be formatted.
	 * @return output the String containing the formatted currency.
	 */
	private String formatCurrency(double value)
	{
		BigDecimal myDecimal = new BigDecimal(value);
		myDecimal.setScale(0, BigDecimal.ROUND_DOWN);
		
		DecimalFormat myFormat = new DecimalFormat(pattern);
	    String output = myFormat.format(myDecimal);
	    return output;
	} // end method formatCurrency
	
	
	private boolean HaveNetworkConnection()
	{
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo[] netInfo = cm.getAllNetworkInfo();
	    for (NetworkInfo ni : netInfo)
	    {
	        if (ni.getTypeName().equalsIgnoreCase("WIFI"))
	            if (ni.isConnected())
	                return true;
	        if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
	            if (ni.isConnected())
	                return true;
	    }
	    return false;
	}
}





