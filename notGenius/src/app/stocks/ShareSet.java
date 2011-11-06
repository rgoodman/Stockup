package app.stocks;

import java.net.URL;

/*
 * ShareSet
 * 
 *  Description : The ShareSet class stores the required information for sets of shares
 */
public class ShareSet
{
	private String stockCode;
	private String companyName;
	private int quantity;
	private URL stockURL;
	private static final String prefixURL = "http://uk.finance.yahoo.com/q?s=";
	private static final String londonExchangeURL = ".l";
	
	/*
	 * ShareSet 
	 * 
	 * @param stockCode
	 * @param companyName
	 * @param quantity
	 */
	public ShareSet(String stockCode, String companyName, int quantity)
	{
		this.stockCode = stockCode;
		this.companyName = companyName;
		this.quantity = quantity;
		this.stockURL = buildStockURL();
	}
	
	private URL buildStockURL()
	{
		URL url = null;
		
		try
		{
			url = new URL(prefixURL + stockCode + londonExchangeURL);
		}
		catch(Exception e)
		{
			
		}
		
		return url;
	}
	
	// Accessors 
	public String getStockCode()
	{
		return this.stockCode;
	}
	
	public String getCompanyName()
	{
		return this.companyName;
	}
	
	public int getQuantity()
	{
		return this.quantity;
	}
	
	public URL getStockURL()
	{
		return this.stockURL;
	}	
	
	// Mutators
	public void setStockCode(String newStockCode)
	{
		this.stockCode = newStockCode;
	}
	
	public void setCompanyName(String newCompanyName)
	{
		this.companyName = newCompanyName;
	}
	
	public void setQuantity(int newQuantity)
	{
		this.quantity = newQuantity;
	}
	
	public void setStockURL(URL newStockURL)
	{
		this.stockURL = newStockURL;
	}
}