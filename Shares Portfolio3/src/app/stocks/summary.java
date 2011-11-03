package app.stocks;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class summary extends Activity {
    /** Called when the activity is first created. */
	private TextView BP, HSBC, EXP , MS, SN, GT, BLVN, STAT;
	 String[] symbol = {"BP.l", "HSBA.l", "EXPN.l", "MKS.l", "SN.l", "BLVN.L"};
	 String[] symbols = {"BP", "HSBA", "EXPN", "MS", "SN","GT", "BLVN"};
	 
	 int[] sharesN = {192, 343, 258, 485, 1219, 3960};
	//private Read rd = new Read();
	Main shareapp =new Main();
	String input;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary);
        STAT = (TextView)findViewById(R.id.txtStat); 
        //if(!shreapp.isOnline())
        	//STAT.setText("Connection Status:  Connected" );
        //else 
        	//STAT.setText("Connection Status:  Not Connected" );

        compare();
    
    }
    
    
    /*
     * 
     *
     */
    public void compare()
    {
    	 
    	 BP = (TextView)findViewById(R.id.txtBP);
         HSBC = (TextView)findViewById(R.id.txtHSBC);
         EXP = (TextView)findViewById(R.id.txtEXP);
         MS = (TextView)findViewById(R.id.txtMS);
         SN = (TextView)findViewById(R.id.txtSN);
         GT = (TextView)findViewById(R.id.txtGT);
         BLVN = (TextView)findViewById(R.id.txtBLVN);
         
         TextView[]symbol1 = {BP, HSBC, EXP, MS, SN, BLVN};
         
    	for(int x=0;x<6;x++)
        {
        	temp net = new temp(symbol[x]);
            input = net.readAll();
            double currentPrice = currentPrice();
            double openPrice = openPrice();
            double diff = Math.abs(openPrice - currentPrice);
            int perc =  (int)(openPrice/diff);
            if(currentPrice >= openPrice*1.1 )//10% rocket
            {
            	//rocket
                symbol1[x].setText( symbols[x] + " share price rockets by " + perc/10 + "%" );//String.valueOf(price) + "    x    " + sharesN[x] + " shares " + "   =   £" + result);
                symbol1[x].setTextColor(Color.GREEN);
            }
            else if( currentPrice*1.2 <= openPrice )
            {
            	symbol1[x].setText(symbols[x] + " share price plumets by " + perc/10 + "%" );
                symbol1[x].setTextColor(Color.RED);

            }
            
            //float total = (float)(price*sharesN[x]) ;
           // gt += total;
            //String result = String.format("%.0f", total/100);
            //symbol1[x].setText(String.valueOf(price) + "    x    " + sharesN[x] + " shares " + "   =   £" + result);
        }
    	//String result = String.format("%.0f", gt/100);

    	//GT.setText("£" + result);
    	
  }
    
    
    
    
    public void priceRocket()
    {
    	
    	
    	
    
    }
    
    
    
    public void pricePlummet()
    {
    	
    	
    }
    
    
    /*
     * 
     */
   
    
    
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
}