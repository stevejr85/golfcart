import java.util.*;
import java.io.*;
import java.net.*;

public class runscript 
{
	BufferedReader reader;
	BufferedWriter connection;
	GolfCartStatus gc;
	String status;
	URL url;
	InputStream urlStream;
	String currentline;
	String currentStatus;
	String[] splitStatus;
	String currentHeading;
	String currentRev;
	String currentTicks;
	String currentSpeed;
	int newHeading;
	int numberOfRevs;
	int endingRevs;
	int numberOfTicks;
	int newSpeed;
	boolean turncomplete = false;
	
	public runscript(BufferedWriter mywriter, GolfCartStatus s)
	{
		gc = s;
		status = gc.getString();
		try
		{
			url = new URL("");
    		urlStream = url.openStream();
    		reader = new BufferedReader(new InputStreamReader(urlStream));
			connection = mywriter;
	
			currentline = reader.readLine();
		}
		catch(IOException e)
		{
		}
		
		String[] command = currentline.split(" ");
		
		while(currentline != null)
 		{	
 			try
 			{
 				if (command[0].equals("forward"))
 				{
 					numberOfRevs = ((Integer.parseInt(command[1])*12)/51);
 					numberOfTicks = ((Integer.parseInt(command[1])*12)%51)/7;
 				
 					connection.write("bvf");
 					connection.newLine();
 					connection.flush();
 				
 					connection.write("status");
 					connection.newLine();
 					connection.flush();
 					currentStatus = status;
 					splitStatus = currentStatus.split(",");
 					currentRev = splitStatus[3];
 					endingRevs = Integer.parseInt(currentRev) + numberOfRevs;
 				
 					while(Integer.parseInt(currentRev) < endingRevs)
 					{
 						connection.write("status");
 						connection.newLine();
 						connection.flush();
 						currentStatus = status;
 						splitStatus = currentStatus.split(",");
 						currentRev = splitStatus[3];
 					}
 				
 					connection.write("rsTicks");
 					connection.newLine();
 					connection.flush();
 					connection.write("status");
 					connection.newLine();
 					connection.flush();
 					currentStatus = status;
 					splitStatus = currentStatus.split(",");
 					currentTicks = splitStatus[2];
 				
 					while(Integer.parseInt(currentTicks) < numberOfTicks)
 					{
 						connection.write("status");
 						connection.newLine();
 						connection.flush();
 						currentStatus = status;
 						splitStatus = currentStatus.split(",");
 						currentRev = splitStatus[2];
 					}
 				}	
 				else if (command[0].equals("left"))
 				{
 					connection.write("status");
 					connection.newLine();
 					connection.flush();
 					currentStatus = status;
 					splitStatus = currentStatus.split(",");
 					currentHeading = splitStatus[1];
 				
 					newHeading = Integer.parseInt(currentHeading) - Integer.parseInt(command[1]);
 				
 					if (newHeading < 0)
 					{
 						newHeading = newHeading + 359;
 					}
 				
 					connection.write("bvl");
 					connection.newLine();
 					connection.flush();
 				
 					while (turncomplete == false)
 					{
 						if (newHeading > Integer.parseInt(currentHeading))
 						{
 							connection.write("status");
 							connection.newLine();
 							connection.flush();
 							currentStatus = status;
 							splitStatus = currentStatus.split(",");
 							currentHeading = splitStatus[1];
 						}
 						else
 						{
 							if(Integer.parseInt(currentHeading) > newHeading)
 							{
 								connection.write("status");
 								connection.newLine();
 								connection.flush();
 								currentStatus = status;
 								splitStatus = currentStatus.split(",");
 								currentHeading = splitStatus[1];
 							}
 							else
 								turncomplete = true;
 						}
 					}
 				
 					turncomplete = false;
 				
 					connection.write("evl");
 					connection.newLine();
 					connection.flush();
 				}
 				else if (command[0].equals("right"))
 				{
 					connection.write("status");
 					connection.newLine();
 					connection.flush();
 					currentStatus = status;
 					splitStatus = currentStatus.split(",");
 					currentHeading = splitStatus[1];
 				
 					newHeading = Integer.parseInt(currentHeading) + Integer.parseInt(command[1]);
 				
 					if (newHeading > 359)
 					{
 						newHeading = newHeading - 359;
 					}
 				
 					connection.write("bvr");
					connection.newLine();
 					connection.flush();
 				
 					while (turncomplete == false)
 					{
 						if (newHeading < Integer.parseInt(currentHeading))
 						{
 							connection.write("status");
 							connection.newLine();
 							connection.flush();
 							currentStatus = status;
 							splitStatus = currentStatus.split(",");
 							currentHeading = splitStatus[1];
 						}
 						else
 						{
 							if(Integer.parseInt(currentHeading) < newHeading)
 							{
 								connection.write("status");
 								connection.newLine();
 								connection.flush();
 								currentStatus = status;
 								splitStatus = currentStatus.split(",");
 								currentHeading = splitStatus[1];
 							}
 							else
 								turncomplete = true;
 						}
 					}
 				
 					turncomplete = false;
 				
 					connection.write("evr");
 					connection.newLine();
 					connection.flush();
 				}
 				else if (command[0].equals("stop"))
 				{
 					connection.write("evf");
 					connection.newLine();
 					connection.flush();
 				}
 				else if (command[0].equals("speed"))
 				{
 					newSpeed = Integer.parseInt(command[1]);
 					currentStatus = status;
 					splitStatus = currentStatus.split(",");
 					currentSpeed = splitStatus[3];
 				
 					if (newSpeed < Integer.parseInt(currentSpeed))
 					{
 						while (newSpeed != Integer.parseInt(currentSpeed))
 						{
 							connection.write("f");
 							connection.newLine();
 							connection.flush();
 						
 							currentStatus = status;
 							splitStatus = currentStatus.split(",");
 							currentSpeed = splitStatus[3];
 						}
 					}
 					else
 					{
 						while (newSpeed != Integer.parseInt(currentSpeed))
 						{
 							connection.write("s");
 							connection.newLine();
 							connection.flush();
 						
 							currentStatus = status;
 							splitStatus = currentStatus.split(",");
 							currentSpeed = splitStatus[3];
 						}
 					}
 				
 				}
 				else if (command[0].equals("reverse"))
 				{
 					numberOfRevs = ((Integer.parseInt(command[1])*12)/51);
 					numberOfTicks = ((Integer.parseInt(command[1])*12)%51)/7;
 				
 					connection.write("evf");
 					connection.newLine();
 					connection.flush();
 					connection.write("rev");
 					connection.newLine();
 					connection.flush();
 					connection.write("bvf");
 					connection.newLine();
 					connection.flush();
 				
 					connection.write("status");
 					connection.newLine();
 					connection.flush();
 					currentStatus = status;
 					splitStatus = currentStatus.split(",");
 					currentRev = splitStatus[3];
 					endingRevs = Integer.parseInt(currentRev) + numberOfRevs;
 				
 					while(Integer.parseInt(currentRev) < endingRevs)
 					{	
 						connection.write("status");
 						connection.newLine();
 						connection.flush();
 						currentStatus = status;
 						splitStatus = currentStatus.split(",");
 						currentRev = splitStatus[3];
 					}
 				
 					connection.write("rsTicks");
 					connection.newLine();
 					connection.flush();
 					connection.write("status");
 					connection.newLine();
 					connection.flush();
 					currentStatus = status;
 					splitStatus = currentStatus.split(",");
 					currentTicks = splitStatus[2];
 				
 					while(Integer.parseInt(currentTicks) < numberOfTicks)
 					{
 						connection.write("status");
 						connection.newLine();
 						connection.flush();
 						currentStatus = status;
 						splitStatus = currentStatus.split(",");
 						currentRev = splitStatus[2];
 					}
 				
 					connection.write("evf");
 					connection.newLine();
 					connection.flush();
 					connection.write("fwd");
 					connection.newLine();
 					connection.flush();
 					connection.write("bvf");
 					connection.newLine();
 					connection.flush();
 				}
 			
 				currentline = reader.readLine();
 				command = currentline.split(" ");
			}
			catch(IOException e)
			{
			}
		}
	}
}