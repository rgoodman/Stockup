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

public class Main extends Activity {
   

	OnClickListener portfol = new OnClickListener() {
       public void onClick(View v) {
          portfolio();
       }
   };
	
   
   OnClickListener stat = new OnClickListener() {
       public void onClick(View v) {
          status();
       }
   };
	
   
   
   
   @Override
    public void onCreate(Bundle savedInstanceState) 
   {
        super.onCreate(savedInstanceState);
        
        	setContentView(R.layout.menu);
        	boolean b = false;//??
        	((Button) findViewById(R.id.btnPortfolio)).setOnClickListener(portfol);
        	((Button) findViewById(R.id.btnStatus1)).setOnClickListener(stat);
        	if (!isOnline())
        	{
        		Intent i = new Intent(this, Menu.class);
                startActivity(i);
        	}
        	///else
        	////{
        		//Intent i = new Intent(this, Menu.class);
            	//startActivity(i);
        	//}
     }
    
   
    /*
     * 
     */
    public boolean isOnline() 
    {
    	final ConnectivityManager conMgr =  (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    	final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
    	if (activeNetwork != null && activeNetwork.getState() == NetworkInfo.State.CONNECTED) {
    	    return true;
    	} else {
    	    return false;
    	} 
    	
    }
    	
    	/*final ConnectivityManager connMgr = (ConnectivityManager)
    		     this.getSystemService(Context.CONNECTIVITY_SERVICE);

    		     final android.net.NetworkInfo wifi =
    		     connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

    		     final android.net.NetworkInfo mobile =
    		     connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

    		     if( wifi.isAvailable() ){

    		     return true;
    		     }
    		     else if( mobile.isAvailable() ){

    		     return true;
    		     }
    		     else
    		     {

    		         return false;
    		     }

    		}
    	 /*final ConnectivityManager manager = (ConnectivityManager) getSystemService (Context.CONNECTIVITY_SERVICE);
    	 boolean is3g = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected();
 	     boolean isWifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected();
 	     // if (manager.getActiveNetworkInfo() != null && manager.getActiveNetworkInfo().isAvailable() &&   manager.getActiveNetworkInfo().isConnected()) {
            if (is3g || isWifi)  
            	return true;
         
         else 
             return false;
         */
    


    
    
    public void portfolio()
    {
    	Intent i = new Intent(this, shares.class);
        startActivity(i);
        
    }
    	
    
    public void status()
    {
    	Intent i = new Intent(this, summary.class);
        startActivity(i);
        
    }
    	
  }
    
    
    
   
