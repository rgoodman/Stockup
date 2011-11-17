package app.stockup;

import java.util.ArrayList;

import app.stockup.R;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TabHost;

public class Main extends TabActivity
{
	public static ArrayList<ShareSet> sharePortfolio = new ArrayList<ShareSet>();
	
	Resources resources;
	TabHost tabHost;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		resources = getResources();
		tabHost = getTabHost();
		
		buildPortfolio();
		buildPage(Status.class, "Status", R.drawable.status_tab);
		buildPage(Portfolio.class, "Portfolio", R.drawable.portfolio_tab);
		
		//setTabColour();
		
		tabHost.setCurrentTab(0);
	}
	
	public void setTabColour()
	{
		for(int i = 0; i < tabHost.getTabWidget().getChildCount(); i++)
		{
			tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#FF0000"));
		}
		
		tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#0000FF"));
	}
	
	private void buildPage(Class<? extends Activity> className, String tabName, int tabID)
	{
		Intent intent = new Intent(this, className);
		
		tabHost.addTab(tabHost.newTabSpec(tabName)
			   .setIndicator(tabName, resources.getDrawable(tabID))
			   .setContent(intent));
	}
	
	private void buildPortfolio()
	{		
		sharePortfolio.add(new ShareSet("BLVN", "Bowleven", 3960, R.id.portfolioTextBLVN, R.id.statusTextBLVN));
		sharePortfolio.add(new ShareSet("BP", "British Petroleum", 192, R.id.portfolioTextBP, R.id.statusTextBP));
		sharePortfolio.add(new ShareSet("EXPN", "Experian", 258, R.id.portfolioTextEXPN, R.id.statusTextEXPN));
		sharePortfolio.add(new ShareSet("HSBA", "HSBC", 343, R.id.portfolioTextHSBC, R.id.statusTextHSBC));
		sharePortfolio.add(new ShareSet("MKS", "Marks & Spencers", 485, R.id.portfolioTextMS, R.id.statusTextMS));
		sharePortfolio.add(new ShareSet("SN", "Smith & Nephew", 1219, R.id.portfolioTextSN, R.id.statusTextSN));
	}
}