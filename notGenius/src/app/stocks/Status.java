

package app.stocks;

import java.util.ArrayList;
import java.util.Iterator;

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
	
	TextView connectionStatus, fieldID;
	
	int color;
	WebReader reader;
	
	float currentPrice;
	float openPrice;
	float difference;
	
	String input;
	String percentage;
	float percentage1;
	String shareDirection;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.status);
		connectionStatus = (TextView)findViewById(R.id.textStat); 
		if (HaveNetworkConnection() == true)
		{
			compareSharePrice();
			connectionStatus.setText("Internet : Connected");		

		}
		else
		{
			connectionStatus.setText("Internet : Not Connected");		
		}
	}

	/*
	 *
	 */
	public void compareSharePrice()
	{		
		while(iterator.hasNext())
		{
			mySet = iterator.next();
			readData();
			if(getDetails()==false)
			{
				connectionStatus.setText("Unable to retrieve data!");
			}
			calculate();
			
			
			if (currentPrice == 0 || openPrice == 0)
			{
				color = (int) Color.WHITE;
				shareDirection = "has invalid data";
			}
			
			else if(currentPrice >= openPrice*1.1f)
			{
					color = (int) Color.GREEN;
					shareDirection = "rocketed";
			}
			else if(openPrice*0.2 + currentPrice <= openPrice)
			{
					color = (int) Color.RED;
					shareDirection = "plummeted";
			}
		}
	}
	
	/*
	 * 
	 */
	public void displayDetails()
	{
		fieldID.setText(mySet.getStockCode() + " has " + shareDirection + " by " + percentage + "%");
		fieldID.setTextColor(color);
	}
	
	/*
	 *Overridden method 
	 */
	
	/*
	 * 
	 */
	public void calculate()
	{
		difference = Math.abs(openPrice - currentPrice);
		percentage = String.format("%.2f", 100/(openPrice/difference));
	}
	
	
	/*
	 *
	 */
	public boolean getDetails()
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
	
	/*
	 * 
	 */
	
	public void noConnection ()
	{
		connectionStatus.setText("No connection");
	}
	/*
	 * 
	 */
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