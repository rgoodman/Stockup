package app.stocks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Main extends Activity
{
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
}




