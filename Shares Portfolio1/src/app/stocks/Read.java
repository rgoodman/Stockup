package app.stocks;
import java.io.*;
import java.net.URL;

public class Read {
	public static BufferedReader rd(String url) throws Exception
	{
		return new BufferedReader(new InputStreamReader(new URL(url).openStream()));
	}
	
	
	public float getVal(String stock)
	{
		try{
			BufferedReader reader = rd("htttp://www.google.co.uk/finance?q=LON%3A" + stock + "#");
			String line = reader.readLine();
			float stockValue = 0;
			
			while(line != null)
			{
				line = reader.readLine();
				if (line.contains("_1\">"))
				{
					char [] temp = line.toCharArray();
					char [] temp2 = new char[6];
					
					for(int i=0; i<6; i++)
					{
						temp2[i] = temp[temp.length-13 +i];
						if (temp2[i] == '<')
						{
							break;
						}
					}
					stockValue = Float.parseFloat(new String(temp2));
					break;
				}
			}
			return stockValue;
					
		}catch(Exception e)
		{
					System.out.println(e.getMessage());
		}
		return 0;		
				
				
	}
			
}


