

import java.net.URL;

/*
 * ShareSet
 * 
 *  Description : The ShareSet class stores the required information for sets of shares
 */
public class ShareSet
{
	private int portfolioFieldID;
	private int quantity;
	private int statusFieldID;
	
	private static final String prefixURL = "http://uk.finance.yahoo.com/q?s=";
	private static final String londonExchangeURL = ".l";
	
	private String companyName;
	private String stockCode;
		
	private URL stockURL;
	
	/*
	 * ShareSet 
	 * 
	 * @param stockCode
	 * @param companyName
	 * @param quantity
	 */
	public ShareSet(String stockCode, String companyName, int quantity, int portfolioFieldID, int statusFieldID)
	{
		this.companyName = companyName;
		this.portfolioFieldID = portfolioFieldID;
		this.quantity = quantity;
		this.statusFieldID = statusFieldID;
		this.stockCode = stockCode;
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
	public String getCompanyName()
	{
		return companyName;
	}
	
	public int getPortfolioFieldID()
	{
		return portfolioFieldID;
	}
	
	public int getQuantity()
	{
		return quantity;
	}
	
	public String getStockCode()
	{
		return stockCode;
	}	
	
	public URL getStockURL()
	{
		return stockURL;
	}
		
	public int getStatusFieldID()
	{
		return statusFieldID;
	}
	
	// Mutators
	
	public void setCompanyName(String newCompanyName)
	{
		this.companyName = newCompanyName;
	}
	
	public void setQuantity(int newQuantity)
	{
		this.quantity = newQuantity;
	}
	
	public void setStockCode(String newStockCode)
	{
		this.stockCode = newStockCode;
	}
			
	public void setStockURL(URL newStockURL)
	{
		this.stockURL = newStockURL;
	}
}
