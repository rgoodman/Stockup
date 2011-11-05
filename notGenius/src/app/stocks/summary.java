package app.stocks;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class summary extends Activity {
    /** Called when the activity is first created. */
	private TextView BP, HSBC, EXP , MS, SN, BLVN,STAT;
	 String[] symbol = {"BLVN.l", "BP.l", "EXPN.l", "HSBA.l", "MKS.l", "SN.l"};
	 //String[] symbols = {"BP", "HSBA", "EXPN", "MS", "SN", "BLVN"};
	 String[] symbols = {"BLVN", "BP", "EXP", "HSBC", "MS", "SN"};

	 int[] sharesN = {192, 343, 258, 485, 1219, 3960};
	//private Read rd = new Read();
	Main shareapp =new Main();
	String input;
	
	public summary()
	{
    	//STAT.setText("Connection Status: " + status );

		
	
	}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary);
        STAT = (TextView)findViewById(R.id.txtStat); 
        //if(shareapp.isOnline()==false)
        	///STAT.setText("Connection status:Not Connected" );
        //if(shareapp.isOnline()==true)
        	STAT.setText("Connection Status: Connected" );//STAT.setText("Connection Status:  Not Connected" );
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
         
         BLVN = (TextView)findViewById(R.id.txtBLVN);
         
         TextView[]symbol1 = {BP, HSBC, EXP, MS, SN, BLVN};
         
    	for(int x=0;x<6;x++)
        {
    		WebReader net = new WebReader(symbol[x]);
            input = net.readAll();
            double currentPrice = currentPrice();
            double openPrice = openPrice();
            double diff = Math.abs(openPrice - currentPrice);
            //float perc =  (float)(100/(openPrice/diff));
            String perc= String.format("%.2f", 100/(openPrice/diff));
            if(currentPrice >= openPrice )//10% rocket
            {
            	//rocket
                symbol1[x].setText( symbols[x] + " share price rockets by " + perc + "%" );//String.valueOf(price) + "    x    " + sharesN[x] + " shares " + "   =   £" + result);
                symbol1[x].setTextColor(Color.GREEN);
            }
            else if( currentPrice <= openPrice )
            {
            	symbol1[x].setText(symbols[x] + " share price plumets by " + perc + "%" );
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