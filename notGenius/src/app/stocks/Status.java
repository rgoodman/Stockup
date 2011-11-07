package app.stocks;

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
	
	double currentPrice;
	double openPrice;
	double difference;
	
	String input;
	String percentage;
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
			fieldID = (TextView)findViewById(mySet.getStatusFieldID());
			reader = new WebReader(mySet.getStockURL());
			input = reader.readLine();
			currentPrice = reader.getCurrentPrice(input);
			openPrice = reader.getOpenPrice(input);
			difference = Math.abs(openPrice - currentPrice);
			percentage = String.format("%.2f", 100/(openPrice/difference));
			
			if(currentPrice >= openPrice)
			{
				color = (int) Color.GREEN;
				shareDirection = "rocketed";
			}
			
			else if(currentPrice <= openPrice)
			{
				color = (int) Color.RED;
				shareDirection = "plummeted";
			}

			fieldID.setText(mySet.getStockCode() + " has " + shareDirection + " by " + percentage + "%");
			fieldID.setTextColor(color);
		}
	}
}