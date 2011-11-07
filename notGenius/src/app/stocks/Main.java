package app.stocks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

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
		createPortfolio();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		((Button) findViewById(R.id.btnPortfolio)).setOnClickListener(portfolioPageListener);
		((Button) findViewById(R.id.btnStatus)).setOnClickListener(statusPageListener);
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
		sharePortfolio.add(new ShareSet("BLVN", "Bowleven", 3960, R.id.textBLVN, R.id.txtBLVN));
		sharePortfolio.add(new ShareSet("BP", "British Petroleum", 192, R.id.textBP, R.id.txtBP));
		sharePortfolio.add(new ShareSet("EXPN", "Experian", 258, R.id.textEXPN, R.id.txtEXPN));
		sharePortfolio.add(new ShareSet("HSBA", "HSBC", 343, R.id.textHSBC, R.id.txtHSBC));
		sharePortfolio.add(new ShareSet("MKS", "Marks & Spencers", 485, R.id.textMS, R.id.txtMS));
		sharePortfolio.add(new ShareSet("SN", "Smith & Nephew", 1219, R.id.textSN, R.id.txtSN));
	}
}




