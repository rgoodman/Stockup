package app.stocks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class Menu extends Activity {

	OnClickListener StatusListener = new OnClickListener() {
        public void onClick(View v) {
        	 status();
        }
    };
    
    
    
    
   
	
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
       
       
        

        ((ImageView) findViewById(R.id.btnStatus1)).setOnClickListener(StatusListener);
      //((ImageView) findViewById(R.id.shares)).setOnClickListener(shareListener);
        //((ImageView) findViewById(R.id.portfolio)).setOnClickListener(PortfolioListener);
        
    }
    
    
    
  
    
    
    private void shares()
    {
    	Intent i = new Intent(this, shares.class);
        startActivity(i);
    }
    
    
    private void status()
    {
    	Intent i = new Intent(this, shares.class);
        startActivity(i);
    }

}
