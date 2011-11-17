package app.stockup;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

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
	Iterator<ShareSet> iterator = myShares.iterator();
	ShareSet mySet;

	TextView changeStatus, connectionStatus, fieldID, GT;

	WebReader reader;
	
	int counter = 0;
	
	double sharePrice;	
	
	float grandTotal;
	float shareSetTotal;

	String input;
	String total;
	
	TableRow parent;
	View view;
	ImageView imageView;
	
	
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
			setAlertStyle(connectionStatus, "Connection Established", R.drawable.alert_green, R.drawable.alert_green_icon);
			refreshPortfolioPage();
		}
		else
		{
			setAlertStyle(connectionStatus, "No Internet Access", R.drawable.alert_red, R.drawable.alert_red_icon);
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
			//sharePrice = reader.getCurrentPrice(input);
			sharePrice = reader.getCurrentPrice(counter);
			counter++;
			shareSetTotal = (float)(sharePrice * mySet.getQuantity());
			grandTotal += shareSetTotal;
			total = formatCurrency((shareSetTotal/100));
			fieldID.setText(String.valueOf(sharePrice) + "    x    " + mySet.getQuantity() + " shares " + "   =   " + total);
		}
		
		GT.setText(formatCurrency(grandTotal/100));
	}
	
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
	}
	
	private String formatCurrency(double value)
	{
		BigDecimal myDecimal = new BigDecimal(value);
		myDecimal.setScale(0, BigDecimal.ROUND_DOWN);
		
	    NumberFormat myFormat = NumberFormat.getCurrencyInstance(Locale.UK);
	    String output = myFormat.format(myDecimal);
	    return output;
	}
}





