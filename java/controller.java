import java.util.*;
import java.io.*;
import java.net.*;

public class controller
{
	
	public controller(BufferedWriter mywriter)
	{
		Timer controlTimer = new Timer();
		controlTimer.schedule(new controlFile(mywriter),0,1000);
	}
}

class controlFile extends TimerTask
{
		BufferedReader reader;
		BufferedWriter connection;
		String currentLine;
		boolean isRunning;
		
		public controlFile(BufferedWriter mywriter)
		{
			connection = mywriter;
			
			try
			{
				url = new URL("");
    			urlStream = url.openStream();
    			reader = new BufferedReader(new InputStreamReader(urlStream));
    		}
    		catch(IOException e)
    		{
    		}
    	
    	
    		Timer filetimer = new Timer();
			filetimer.schedule(new checkfile(connection), 0, 1000);
			isRunning = true;
    	
    		currentLine = reader.readLine();
    	
    		if(currentLine.equals("remote"))
    		{
    			if (isRunning == false)
    			{
    				filetimer.schedule(new checkfile(connection), 0, 1000);
					isRunning = true;
    			}
    		}
    		else if (currentLine.equals("script"))
    		{
    			if (isRunning == true)
    			{
    				filetimer.cancel();
    				filetimer.purge();
    				isRunning = false;
    			}
    		
    			runscript x = new runscript();
    		}
    	}
}