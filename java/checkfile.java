import java.util.*;
import java.io.*;
import java.net.*;

public class checkfile extends TimerTask
{
	BufferedReader reader;
	BufferedWriter connection;
	URL url;
	InputStream urlStream;
	
	public checkfile(BufferedWriter mywriter)
	{
		
		// try 
//         {
//    				url = new URL("http://157.182.184.52/~student1/data/comFile.txt");
//     			urlStream = url.openStream();
//     			reader = new BufferedReader(new InputStreamReader(urlStream));
// 		} 
// 		catch (MalformedURLException e) 
// 		{ 
//     			System.out.println("Bad URL");
// 		} 
// 		catch (IOException e) 
// 		{   
//    				 System.out.println("URL connection failed");
// 		}
		
		connection = mywriter;
	}
		
	public void run()
	{
		String currentline;
		try
		{
			url = new URL("http://192.168.0.5/data/comFile.txt");
    		urlStream = url.openStream();
    		reader = new BufferedReader(new InputStreamReader(urlStream));
			currentline = reader.readLine();
			
			if (currentline != null)
			{
				String[] command = currentline.split(" ");
			
				if (command[1].equals("up"))
				{
					connection.write("bvf");
					connection.newLine();
					connection.flush();
				}	
				else if (command[1].equals("down"))
				{
				}
				else if (command[1].equals("left"))
				{
					connection.write("bvl");
					connection.newLine();
					connection.flush();
				}
				else if (command[1].equals("right"))
				{
					connection.write("bvr");
					connection.newLine();
					connection.flush();
				}
				else if (command[1].equals("stop"))
				{
					connection.write("evf");
					connection.newLine();
					connection.flush();
				}
				else if (command[1].equals("svt"))
				{
					connection.write("evl");
					connection.newLine();
					connection.flush();
					connection.write("evr");
					connection.newLine();
					connection.flush();
				}
			}
		}
		catch(IOException e)
		{
		}
		
	}
}