package app.stockup;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

import app.stockup.Main;
import app.stockup.ShareSet;
import app.stockup.WebReader;
import app.stockup.R;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

public class Portfolio extends Activity
{
	public ArrayList<ShareSet> myShares = Main.sharePortfolio;	
	ShareSet mySet;

	TextView changeStatus, connectionStatus, fieldID, GT;

	WebReader reader;
	
	double sharePrice;	
	
	float grandTotal;
	float shareSetTotal;

	String input;
	String total;
	
	TableRow parent;
	View view;
	ImageView imageView;
	
	private String pattern = "£###,###,###,###"; //The String pattern used for formatting the display of currency.
	
	
	OnClickListener refreshPortfolioListener = new OnClickListener()
	{
		public void onClick(View v)
		{
			refreshPortfolioPage();
		}
	};
	
	/**
	 * onCreate
	 * 
	 * @param savedInstanceState
	 */
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.portfolio);
		((ImageView) findViewById(R.id.refresh)).setOnClickListener(refreshPortfolioListener);
		connectionStatus = (TextView)findViewById(R.id.connectionStatus);
		getConnectionStatus();		
	}
	
	/**
	 * onResume
	 * 
	 * @param savedInstanceState
	 */
	public void onResume(Bundle savedInstanceState)
	{	
		getConnectionStatus();
	}
	
	
	/**
	 * getConnectionStatus
	 * 
	 * Indicates and Sets the status of the Internet Connection. 
	 */
	private void getConnectionStatus()
	{
		if (hasConnection() == true)
		{
			setAlertStyle(connectionStatus, "Connection Established", R.drawable.alert_green, R.drawable.alert_green_icon);
			refreshPortfolioPage();
		}
		else
		{
			setAlertStyle(connectionStatus, "No Internet Access", R.drawable.alert_red, R.drawable.alert_red_icon);
		}	
	} // end method getConnectionStatus
	
	
	/**
	 * hasConnection
	 * 
	 * Checks if there is an internet connection available.
	 * 
	 * @return true if an internet connection is available, false otherwise.
	 */
	private boolean hasConnection()
	{
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo[] netInfo = cm.getAllNetworkInfo();

	    for (NetworkInfo ni : netInfo)
	    {
	        if ((ni.getTypeName().equalsIgnoreCase("WIFI") || ni.getTypeName().equalsIgnoreCase("MOBILE")) && ni.isConnected())
	        {
	        	return true;
	        }
	    }
	    return false;
	} // end method hasConnection
	
	
	/**
	 * refreshPortfolioPage
	 * 
	 * Updates the ShareSet information displayed on the portfolio page.
	 * 
	 */
	public void refreshPortfolioPage()
	{
		grandTotal = 0;		
		GT = (TextView)findViewById(R.id.textGT);
		Iterator<ShareSet> iterator = myShares.iterator();
		
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
			fieldID.setText(mySet.getCompanyName() + "\n" + String.valueOf(sharePrice) + "    x    " + mySet.getQuantity() + " shares " + "   =   " + total);
		}
		
		GT.setText("Grand Total : \t" + formatCurrency(grandTotal/100));
	} // end method refreshPortfolioPage
	
	
	/**
	 * setAlertStyle
	 * 
	 * Sets up an AlertStyle for the specified TextView. 
	 * 
	 * @param field The TextView whose properties are to be set to the specified values.
	 * @param text Sets this TextViews setText to the specified text.
	 * @param fieldColor Sets this TextViews Colour to the one specified.
	 * @param icon Sets the Icon this TextView will display.
	 */
	private void setAlertStyle(TextView field, String text, int fieldColor, int icon)
	{
		imageView = null;
		field.setVisibility(View.VISIBLE);
		field.setText(text);
		
		parent = (TableRow) field.getParent();
		parent.setBackgroundDrawable(this.getResources().getDrawable(fieldColor));
		
		for (int itemPos = 0; itemPos < parent.getChildCount(); itemPos++)
		{
		    view = parent.getChildAt(itemPos);
		    if (view instanceof ImageView)
		    {
		    	imageView = (ImageView) view;
		        break;
		    }
		}
		
		imageView.setBackgroundDrawable(this.getResources().getDrawable(icon));
	} // end method setAlertStyle
	
	
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
} // end class Portfolio





