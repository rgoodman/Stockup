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
	private static final String prefixURL = "http://uk.finance.yahoo.com/q?s=";
	
	/* 
	 * Method to build the URL and read data from a Web site
	 */
	public WebReader(String stockURL)
	{
		try
		{
			URL url = new URL(prefixURL + stockURL);
			URLConnection site = url.openConnection();
			InputStream inputStream = site.getInputStream();
			scanner = new Scanner(new BufferedInputStream(inputStream));
		}
		catch (IOException ioe) 
		{
			System.err.println("Could not open " + stockURL);
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
}
