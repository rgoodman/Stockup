package app.stocks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;

public class shares extends Activity {
    /** Called when the activity is first created. */
 
    
    private EditText BP, HSBC, EXP , MS, SN, GT, BLVN;
	 String[] symbol = {"BLVN.l", "BP.l", "EXPN.l", "HSBA.l", "MKS.l", "SN.l"};
	// String[] symbol = {"BP.l", "HSBA.l", "EXPN.l", "MKS.l", "SN.l", "BLVN.L"};

	 int[] sharesN = {192, 343, 258, 485, 1219, 3960};
	//private Read rd = new Read();
	
	String input;

	OnClickListener rf = new OnClickListener() {
       public void onClick(View v) {
          refresh();
       }
   };
    
    
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);//reuse
        refresh();
        ((ImageView) findViewById(R.id.refresh)).setOnClickListener(rf);
    }
    
    
    
    /*
     * 
     */
    public void refresh()
    {
    	 float gt =0;
    	 BP = (EditText)findViewById(R.id.textBP);
         HSBC = (EditText)findViewById(R.id.textHSBC);
         EXP = (EditText)findViewById(R.id.textEXP);
         MS = (EditText)findViewById(R.id.textMS);
         SN = (EditText)findViewById(R.id.textSN);
         GT = (EditText)findViewById(R.id.textGT);
         BLVN = (EditText)findViewById(R.id.textBW);
         
         EditText[]symbol1 = {BLVN, BP, EXP, HSBC, MS, SN};
         
    	for(int x=0;x<6;x++)
        {
        	temp net = new temp(symbol[x]);
            input = net.readAll();
            double price = currentPrice();
            float total = (float)(price*sharesN[x]) ;
            gt += total;
            String result = String.format("%.0f", total/100);
            symbol1[x].setText(String.valueOf(price) + "    x    " + sharesN[x] + " shares " + "   =   £" +result);
        }
    	String result = String.format("%.0f", gt/100);

    	GT.setText("£" + result);
    	
  }
    
    
    
    /*
     * 
     */
    public double currentPrice() {
        //In page = new In("http://finance.yahoo.com/q?s=" + symbol); 
        //String input = page.readAll(); 
        int trade = input.indexOf("Last Trade:", 0);         // "Last trade:" index
        int from  = input.indexOf("<b><span", trade);        // "<b><span" index
        from      = input.indexOf(">", from + 4);            // ">" index
        int to    = input.indexOf("</span></b>", from);      // "</b>" index
        String price = input.substring(from + 1, to); 
        return Double.parseDouble(price); 
    } 
    
    
    /*
     * 
     */
    public double openPrice()
    {
    	int trade = input.indexOf("Open:", 0);
    	int from  = input.indexOf("><td class", trade);
    	from 	  = input.indexOf(">", from + 3);
    	int to	  = input.indexOf("</td></tr>", from);
    	String price = input.substring(from + 1, to);
    	return Double.parseDouble(price);
    }
    
    
    /*
     * 
     * 
     */
   
    
    
    
    
}





