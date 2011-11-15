

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import jass.shares.R;

import java.util.ArrayList;

public class Main extends Activity
{	
	public static ArrayList<ShareSet> sharePortfolio = new ArrayList<ShareSet>();	
	
	/*
	 * Creates a Listener for Status Button
	 */
	OnClickListener portfolioPageListener = new OnClickListener()
	{
		public void onClick(View v)
		{
			loadPage(Portfolio.class);
		}
	};	

	/*
	 * Creates a Listener for Portfolio Button
	 */
	OnClickListener statusPageListener = new OnClickListener()
	{
		public void onClick(View v)
		{
			loadPage(Status.class);
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		if (HaveNetworkConnection())
		{
			createPortfolio();
			super.onCreate(savedInstanceState);
			setContentView(R.layout.menu);
			((Button) findViewById(R.id.btnPortfolio)).setOnClickListener(portfolioPageListener);
			((Button) findViewById(R.id.btnStatus)).setOnClickListener(statusPageListener);
		}
		else
		{
			Context context = getApplicationContext();
			CharSequence text = "You have no internet connection!";
			int duration = Toast.LENGTH_SHORT;

			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		}
	}

	/*
	 * Loads a page
	 */
	public void loadPage(Class<? extends Activity> className)
	{
		Intent intent = new Intent(this, className);
		startActivity(intent);
	}
	
	private void createPortfolio()
	{		
		sharePortfolio.add(new ShareSet("BLVN", "Bowleven", 3960, R.id.portfolioTextBLVN, R.id.statusTextBLVN));
		sharePortfolio.add(new ShareSet("BP", "British Petroleum", 192, R.id.portfolioTextBP, R.id.statusTextBP));
		sharePortfolio.add(new ShareSet("EXPN", "Experian", 258, R.id.portfolioTextEXPN, R.id.statusTextEXPN));
		sharePortfolio.add(new ShareSet("HSBA", "HSBC", 343, R.id.portfolioTextHSBC, R.id.statusTextHSBC));
		sharePortfolio.add(new ShareSet("MKS", "Marks & Spencers", 485, R.id.portfolioTextMS, R.id.statusTextMS));
		sharePortfolio.add(new ShareSet("SN", "Smith & Nephew", 1219, R.id.portfolioTextSN, R.id.statusTextSN));
	}
	
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




