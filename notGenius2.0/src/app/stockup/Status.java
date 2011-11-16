package app.stockup;

import java.util.ArrayList;
import java.util.Iterator;

import app.stockup.Main;
import app.stockup.ShareSet;
import app.stockup.WebReader;
import app.stockup.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.TextView;

public class Status extends Activity
{
	private ArrayList<ShareSet> myShares = Main.sharePortfolio;	
	Iterator<ShareSet> iterator = myShares.iterator();
	ShareSet mySet;
	
	TextView changeStatus, connectionStatus, fieldID;
	
	boolean change;

	int color;
	WebReader reader;

	float currentPrice;
	float openPrice;
	float difference;

	String input;
	String percentage;
	String shareDirection;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.status);
		changeStatus = (TextView)findViewById(R.id.changeStatus);
		connectionStatus = (TextView)findViewById(R.id.connectionStatus);
		getConnectionStatus();		
	}
	
	public void onResume(Bundle savedInstanceState)
	{	
		getConnectionStatus();
	}
	
	private void getConnectionStatus()
	{
		if (hasConnection() == true)
		{
			connectionStatus.setText("Connection Established");
			compareSharePrice();
		}
		else
		{
			connectionStatus.setText("No Internet Access");
		}	
	}
	
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
	
	public void compareSharePrice()
	{		
		while(iterator.hasNext())
		{
			mySet = iterator.next();
			readData();
			
			if(!getMarketValues())
			{
				connectionStatus.setText("Unable to retrieve data!");
			}
			
			else
			{	
				change = false;
				color = (int) Color.WHITE;
				
				calculatePercentage();
	
				if (currentPrice == 0 || openPrice == 0)
				{
					shareDirection = "has invalid data";
					change = true;
				}
	
				else if(currentPrice >= openPrice*1.1f)
				{
					color = R.color.green;
					shareDirection = "rocketed";
					change = true;
				}
				
				else if(openPrice*0.2 + currentPrice <= openPrice)
				{
					color = R.color.red;
					shareDirection = "plummeted";
					change = true;
				}
				
				if(change)
				{
					displayDetails();				
				}
			}
		}
		
		if(!change)
		{
			changeStatus.setBackgroundResource(R.color.amber);
			changeStatus.setHeight(36);
			changeStatus.setText("No recent changes");
			changeStatus.setTextColor(Color.WHITE);
		}
	}

	/*
	 * 
	 */
	public void displayDetails()
	{
		fieldID.setBackgroundResource(color);
		fieldID.setHeight(36);
		fieldID.setText(mySet.getStockCode() + " has " + shareDirection + " by " + percentage + "%");
		fieldID.setTextColor(Color.WHITE);
	}

	/*
	 * 
	 */
	public void calculatePercentage()
	{
		difference = Math.abs(openPrice - currentPrice);
		percentage = String.format("%.2f", 100/(openPrice/difference));
	}


	/*
	 *
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
	/*
	 * 
	 */
	public void readData()
	{
		fieldID = (TextView)findViewById(mySet.getStatusFieldID());
		reader = new WebReader(mySet.getStockURL());
		input = reader.readLine();
	}
}