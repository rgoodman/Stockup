package app.stockup;

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
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

public class Status extends Activity
{
	private ArrayList<ShareSet> myShares = Main.sharePortfolio;	
	Iterator<ShareSet> iterator = myShares.iterator();
	ShareSet mySet;
	
	TextView changeStatus, connectionStatus, fieldID;
	
	boolean change;

	int color;
	int icon;
	WebReader reader;

	float currentPrice;
	float openPrice;
	float difference;

	String input;
	String percentage;
	String shareDirection;
	
	TableRow parent;
	View view;
	ImageView imageView;
	
	/**
	 * onCreate
	 * 
	 * @param savedInstanceState
	 */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.status);
		changeStatus = (TextView)findViewById(R.id.changeStatus);
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
			compareSharePrice();
		}
		else
		{
			setAlertStyle(connectionStatus, "No Internet Access", R.drawable.alert_red, R.drawable.alert_red_icon);
		}	
	}
	
	
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
	}
	
	
	/**
	 * compareSharePrice
	 * 
	 * Compares Share Prices to check if any Rockets or Plummets have occured.
	 */
	public void compareSharePrice()
	{		
		while(iterator.hasNext())
		{
			mySet = iterator.next();
			readData();
			
			if(!getMarketValues())
			{
				setAlertStyle(connectionStatus, "Unable to retrieve data!", R.drawable.alert_amber, R.drawable.alert_amber_icon);
			}
			
			else
			{	
				change = false;
				
				calculatePercentage();
	
				if (currentPrice == 0 || openPrice == 0)
				{
					change = true;
					color = (int)R.drawable.alert_amber;
					icon = (int)R.drawable.alert_amber_icon;
					shareDirection = "has invalid data";
				}
	
				else if(currentPrice >= openPrice*1.1f)
				{
					change = true;
					color = (int)R.drawable.alert_green;
					icon = (int)R.drawable.arrow_up_icon;
					shareDirection = "rocketed";
				}
				
				else if(openPrice*0.2 + currentPrice <= openPrice)
				{
					change = true;
					color = (int)R.drawable.alert_red;
					icon = (int)R.drawable.arrow_down_icon;
					shareDirection = "plummeted";
				}
				
				if(change)
				{
					displayShareSetDetails();				
				}
			}
		}
		
		if(!change)
		{
			setAlertStyle(changeStatus, "No rockets or plummets!", R.drawable.alert_amber, R.drawable.alert_amber_icon);
		}
	} // end method compareSharePrice

	
	/**
	 * displayShareSetDetails
	 * 
	 * Displays the details of a ShareSet on screen.
	 */
	public void displayShareSetDetails()
	{
		setAlertStyle(fieldID, (mySet.getStockCode() + " has " + shareDirection + " by " + percentage + "%"), color, icon);
	}
	
	
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
		field.setText(text);
		
		parent = (TableRow) field.getParent();
		parent.setVisibility(View.VISIBLE);
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
	}


	/**
	 * calculatePercentage
	 * 
	 * Calculates the percentage difference between the current and open market values.
	 */
	public void calculatePercentage()
	{
		difference = Math.abs(openPrice - currentPrice);
		percentage = String.format("%.2f", 100/(openPrice/difference));
	}
	
	
	/**
	 * getMarketValues
	 * 
	 * Reads in the Current and Open price market values.
	 * 
	 * @return true if successful, false otherwise.
	 */
	public boolean getMarketValues()
	{
		try
		{
			currentPrice = (float)reader.getCurrentPrice(input);
			openPrice = (float)reader.getOpenPrice(input);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	
	/**
	 * readData
	 * 
	 * Reads stock market data from a website. 
	 */
	public void readData()
	{
		fieldID = (TextView)findViewById(mySet.getStatusFieldID());
		reader = new WebReader(mySet.getStockURL());
		input = reader.readLine();
	}
}