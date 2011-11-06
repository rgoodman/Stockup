package app.stocks;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;
import java.util.Scanner;

public class WebReader
{
	private static Scanner scanner;
	
	int trade;
	int from;
	int to;
	
	String input;
	String price;
	
	/* 
	 * Method to build the URL and read data from a Web site
	 */
	public WebReader(URL url)
	{
		try
		{
			URLConnection website = url.openConnection();
			InputStream inputStream = website.getInputStream();
			scanner = new Scanner(new BufferedInputStream(inputStream));
		}
		catch (IOException ioe) 
		{
			
		}
	}

	/* 
	 * 
	 * 
	 * Reference: http://weblogs.java.net/blog/pat/archive/2004/10/stupid_scanner_1.html
	 */
	public String readLine()
	{
		if (scanner.hasNextLine())
		{			
			return scanner.useDelimiter("\\A").next();
		}
		
		return null;
	}
	
	public double getPrice()
	{		
		input = readLine();
		
		trade = input.indexOf("Last Trade:", 0);
		from = input.indexOf("<b><span", trade); 
		from = input.indexOf(">", from + 4);	
		int to = input.indexOf("</span></b>", from);

		price = input.substring(from + 1, to); 
		return Double.parseDouble(price);
	}
}
