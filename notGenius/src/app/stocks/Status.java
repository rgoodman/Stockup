package app.stocks;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Status extends Activity
{
	private TextView BP, HSBC, EXP, MS, SN, BLVN, STAT;
	String[] symbol = {"BLVN.l", "BP.l", "EXPN.l", "HSBA.l", "MKS.l", "SN.l"};
	String[] symbols = {"BLVN", "BP", "EXP", "HSBC", "MS", "SN"};

	int[] sharesN = {192, 343, 258, 485, 1219, 3960};
	String input;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.summary);
		STAT = (TextView)findViewById(R.id.txtStat); 
		STAT.setText("Connection Status: Connected" );
		compare();
	}

	/*
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

		for(int x = 0; x < 6; x++)
		{
			WebReader net = new WebReader(symbol[x]);
			input = net.readLine();
			double currentPrice = currentPrice();
			double openPrice = openPrice();
			double diff = Math.abs(openPrice - currentPrice);
			String perc= String.format("%.2f", 100/(openPrice/diff));
			
			if(currentPrice >= openPrice)
			{
				symbol1[x].setText( symbols[x] + " rockets by " + perc + "%" );
				symbol1[x].setTextColor(Color.GREEN);
			}
			
			else if(currentPrice <= openPrice)
			{
				symbol1[x].setText(symbols[x] + " plummets by " + perc + "%" );
				symbol1[x].setTextColor(Color.RED);

			}
		}
	}
	
	/*
	 * 
	 */
	public double openPrice()
	{
		int trade = input.indexOf("Open:", 0);
		int from = input.indexOf("><td class", trade);
		from = input.indexOf(">", from + 3);
		int to = input.indexOf("</td></tr>", from);
		String price = input.substring(from + 1, to);
		return Double.parseDouble(price);
	}

	/*
	 * 
	 */
	public double currentPrice() {
		int trade = input.indexOf("Last Trade:", 0);
		int from = input.indexOf("<b><span", trade); 
		from = input.indexOf(">", from + 4);
		int to = input.indexOf("</span></b>", from);
		String price = input.substring(from + 1, to); 
		return Double.parseDouble(price); 
	} 
}