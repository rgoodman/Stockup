package app.stocks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;

public class Main extends Activity {
    /** Called when the activity is first created. */
	private EditText BP, HSBC, EXP , MS, SN, GT;
	 String[] symbol = {"BP.l", "HSBA.l", "EXPN.l", "MKS.l", "SN.l"};
	 int[] sharesN = {192, 343, 258, 485, 1219};
	//private Read rd = new Read();
	
	String input;

    OnClickListener sumListener = new OnClickListener() {
        public void onClick(View v) {
            summary();
        }
    };
    OnClickListener dListener = new OnClickListener() {
        public void onClick(View v) {
            detailed();
        }
    };
    OnClickListener shareListener = new OnClickListener() {
        public void onClick(View v) {
            shares();
        }
    };
    
    
    OnClickListener rf = new OnClickListener() {
        public void onClick(View v) {
            refresh();
        }
    };
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        refresh();
       
        

        ((ImageView) findViewById(R.id.refresh)).setOnClickListener(rf);
        //((ImageView) findViewById(R.id.compiled)).setOnClickListener(dListener);
        //((ImageView) findViewById(R.id.shares)).setOnClickListener(shareListener);
        
    }
    
    
    
    
    public void refresh()
    {
    	 float gt =0;
    	 BP = (EditText)findViewById(R.id.textBP);
         HSBC = (EditText)findViewById(R.id.textHSBC);
         EXP = (EditText)findViewById(R.id.textEXP);
         MS = (EditText)findViewById(R.id.textMS);
         SN = (EditText)findViewById(R.id.textSN);
         GT = (EditText)findViewById(R.id.textGT);
         EditText[]symbol1 = {BP, HSBC, EXP, MS, SN};
    	for(int x=0;x<5;x++)
        {
        	temp net = new temp(symbol[x]);
            input = net.readAll();
            double price = price();
            float total = (float)(price*sharesN[x]) ;
            gt += total;
            String result = String.format("%.2f", total/100);
            symbol1[x].setText(String.valueOf(price) + "p    x    " + sharesN[x] + " shares " + "   =   £" + result);
        }
    	String result = String.format("%.2f", gt/100);

    	GT.setText("£" + result);
 
    }
    public double price() {
        //In page = new In("http://finance.yahoo.com/q?s=" + symbol); 
        //String input = page.readAll(); 
        int trade = input.indexOf("Last Trade:", 0);         // "Last trade:" index
        int from  = input.indexOf("<b><span", trade);        // "<b><span" index
        from      = input.indexOf(">", from + 4);            // ">" index
        int to    = input.indexOf("</span></b>", from);      // "</b>" index
        String price = input.substring(from + 1, to); 
        return Double.parseDouble(price); 
    } 
    
    private void summary()
    {
    	Intent i = new Intent(this, summary.class);
        startActivity(i);
    }
    private void shares()
    {
    	Intent i = new Intent(this, shares.class);
        startActivity(i);
    }
    
    private void detailed()
    {
    	Intent i = new Intent(this, detailed.class);
        startActivity(i);
        }
}