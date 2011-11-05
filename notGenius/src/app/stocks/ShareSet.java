package app.stocks;

import java.net.URL;

/**
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
	
	public ShareSet()
	{
		
	} // end constructor for class ShareSet
	
	
	// Accessors 
	public String getStockCode()
	{
		return this.stockCode;
	} // end method getStockCode
	
	public String getCompanyName()
	{
		return this.companyName;
	} // end method getCompanyName
	
	public int getQuantity()
	{
		return this.quantity;
	} // end method getQuantity
	
	public URL getStockURL()
	{
		return this.stockURL;
	} // end method getStockURL
	
	
	// Mutators
	public void setStockCode(String newStockCode)
	{
		this.stockCode = newStockCode;
	} // end method setStockCode
	
	public void setCompanyName(String newCompanyName)
	{
		this.companyName = newCompanyName;
	} // end method setCompanyName
	
	public void setQuantity(int newQuantity)
	{
		this.quantity = newQuantity;
	} // end method setQuantity
	
	public void setStockURL(URL newStockURL)
	{
		this.stockURL = newStockURL;
	} // end method setStockURL
} // end class ShareSet
