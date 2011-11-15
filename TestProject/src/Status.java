

import jass.shares.R;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;
import android.graphics.Color;
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
		
		connectionStatus.setText("Internet : Connected");
		compareSharePrice();
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
			
			
			if(currentPrice >= openPrice)
			{
				color = (int) Color.GREEN;
				shareDirection = "rocketed";
				displayDetails();
			}
			
			else if(currentPrice <= openPrice)
			{
				color = (int) Color.RED;
				shareDirection = "plummeted";
				displayDetails();
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
	public  String compareSharePrice(float openPrice, float currentPrice)
	{
		this.openPrice = openPrice;
		this.currentPrice = currentPrice;
		//calculate();
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
		return shareDirection;
	}
	/*
	 * 
	 */
	public void calculate()
	{
		difference = Math.abs(openPrice - currentPrice);
		percentage = String.format("%.2f", 100/(openPrice/difference));
		
	}
	/*
	 * Overridden method
	 */
	public float calculate(float openPrice, float currentPrice)
	{
		difference = Math.abs(openPrice - currentPrice);
		percentage1 = Float.parseFloat(String.format("%.2f", 100/(openPrice/difference)));
		return percentage1;
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
	
}