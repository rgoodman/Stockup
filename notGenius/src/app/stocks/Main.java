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
	OnClickListener portfolioListener = new OnClickListener()
	{
		public void onClick(View v)
		{
			loadPage(shares.class);
		}
	};	

	OnClickListener statusPageListener = new OnClickListener()
	{
		public void onClick(View v)
		{
			loadPage(summary.class);
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		((Button) findViewById(R.id.btnPortfolio)).setOnClickListener(portfolioListener);
		((Button) findViewById(R.id.btnStatus)).setOnClickListener(statusPageListener);
	}


	/*
	 * 
	 */
	public void loadPage(Class<? extends Activity> hi)
	{
		Intent i = new Intent(this, hi);
		startActivity(i);
	}
}




