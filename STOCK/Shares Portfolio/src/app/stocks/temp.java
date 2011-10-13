package app.stocks;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;
import java.util.Scanner;

public class temp {
	
	 private static Scanner scanner;
	 private String charsetName = "ISO-8859-1";
	 private Locale usLocale = new Locale("en", "US");
	 
	 public temp( String s) {
		 try {
			 s="http://uk.finance.yahoo.com/q?s="+s;
		 
	            // first try to read file from local file system
	            File file = new File(s);
	            if (file.exists()) {
	                scanner = new Scanner(file, charsetName);
	                scanner.useLocale(usLocale);
	                return;
	            }

	            // next try for files included in jar
	            URL url = getClass().getResource(s);

	            // or URL from web
	            if (url == null) { url = new URL(s); }

	            URLConnection site = url.openConnection();
	            InputStream is     = site.getInputStream();
	            scanner            = new Scanner(new BufferedInputStream(is), charsetName);
	            scanner.useLocale(usLocale);
	        }
	        catch (IOException ioe) 
	        {
	            System.err.println("Could not open " + s);
	        }
	 }
	 
	 
	 
	
	
	 
	 
	 public String readAll() {
	        if (!scanner.hasNextLine()) { return null; }

	        // reference: http://weblogs.java.net/blog/pat/archive/2004/10/stupid_scanner_1.html
	        return scanner.useDelimiter("\\A").next();
	    }

	 
	 public boolean hasNextLine() {
	        return scanner.hasNextLine();
	    }
	 
	 
	 
	 public void close() { scanner.close();  }


}
