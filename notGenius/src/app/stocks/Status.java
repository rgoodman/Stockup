package app.stocks;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class Status extends Activity
{
	private TextView BP, HSBC, EXP, MS, SN, BLVN, STAT;
	String[] symbols = {"BLVN", "BP", "EXP", "HSBC", "MS", "SN"};
	
	private ArrayList<ShareSet> myShares = Main.sharePortfolio;
	Iterator<ShareSet> iterator = myShares.iterator();
	ShareSet mySet;
	
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
		STAT = (TextView)findViewById(R.id.txtStat); 
		STAT.setText("Connection Status: Connected");
		compareSharePrice();
	}

	/*
	 *
	 */
	public void compareSharePrice()
	{
		BLVN = (TextView)findViewById(R.id.txtBLVN);
		BP = (TextView)findViewById(R.id.txtBP);
		EXP = (TextView)findViewById(R.id.txtEXP);
		HSBC = (TextView)findViewById(R.id.txtHSBC);
		MS = (TextView)findViewById(R.id.txtMS);
		SN = (TextView)findViewById(R.id.txtSN);

		TextView[]symbol1 = {BLVN, BP, EXP, HSBC, MS, SN};

		int x = 0;
		
		while(iterator.hasNext())
		{
			mySet = iterator.next();
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

			symbol1[x].setText(symbols[x] + " has " + shareDirection + " by " + percentage + "%");
			symbol1[x].setTextColor(color);
			
			x++;
		}
	}
}