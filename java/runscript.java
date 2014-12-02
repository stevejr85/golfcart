import java.util.*;
import java.io.*;
import java.net.*;

public class runscript 
{
	BufferedReader reader;
	BufferedWriter connection;
	cartinfo status;
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
	int turn = 0; 
	
	public runscript(BufferedWriter mywriter, cartinfo s)
	{
		
		status = s;
		
		try
		{
			url = new URL("http://golfcart/data/script.txt");
    		urlStream = url.openStream();
    		reader = new BufferedReader(new InputStreamReader(urlStream));
			connection = mywriter;
	
			currentline = reader.readLine();
		}
		catch(IOException e)
		{
		}
		
		String[] command;
		
		while(currentline != null)
 		{	
 			try
 			{
 				command = currentline.split(" ");
 				System.out.println("reading command " + command[0]);
 				if (command[0].equals("forward"))
 				{
 					numberOfRevs = ((Integer.parseInt(command[1])*12)/51);
 					numberOfTicks = ((Integer.parseInt(command[1])*12)%51)/7;
 				
 					connection.write("bvf");
 					connection.newLine();
 					connection.flush();
 					
 					try
 					{
 						Thread.sleep(1000);
 					}
 					catch(InterruptedException e)
 					{
 					}
 					
 					connection.write("status");
 					connection.newLine();
 					connection.flush();
 					
 					try
 					{
 						Thread.sleep(1000);
 					}
 					catch(InterruptedException e)
 					{
 					}
 					
 					currentStatus = status.getString();
 					splitStatus = currentStatus.split(",");
 					while (splitStatus.length != 5)
					{
						currentStatus = status.getString();
						splitStatus = currentStatus.split(",");
					}
					currentRev = splitStatus[3];
 					endingRevs = Integer.parseInt(currentRev) + numberOfRevs;
 					System.out.println("ending revs is " + endingRevs);
 					while(Integer.parseInt(currentRev) <= endingRevs)
 					{
 						connection.write("status");
 						connection.newLine();
 						connection.flush();
 						
 						try
 						{
 							Thread.sleep(1000);
 						}
 						catch(InterruptedException e)
 						{
 						}
 						
 						currentStatus = status.getString();
 						splitStatus = currentStatus.split(",");
						 while (splitStatus.length != 5)
	                                        {
                                               		 currentStatus = status.getString();
                                               		 splitStatus = currentStatus.split(",");
                                       		 }

 						currentRev = splitStatus[3];
 					}
 				/*
 					connection.write("rsTicks");
 					connection.newLine();
 					connection.flush();
 					
 					try
 					{
 						Thread.sleep(1000);
 					}
 					catch(InterruptedException e)
 					{
 					}
 					
 					connection.write("status");
 					connection.newLine();
 					connection.flush();
 					
 					try
 					{
 						Thread.sleep(1000);
 					}
 					catch(InterruptedException e)
 					{
 					}
 					
 					currentStatus = status.getString();
 					splitStatus = currentStatus.split(",");
 					currentTicks = splitStatus[2];
 					System.out.println("ending ticks is " + numberOfTicks);
 					while(Integer.parseInt(currentTicks) <= numberOfTicks)
 					{
 						connection.write("status");
 						connection.newLine();
 						connection.flush();
 						
 						try
 						{
 							Thread.sleep(1000);
 						}
 						catch(InterruptedException e)
 						{
 						}
 						
 						currentStatus = status.getString();
 						splitStatus = currentStatus.split(",");
 						currentRev = splitStatus[2];
 					}*/
 				}	
 				else if (command[0].equals("left"))
 				{
 					connection.write("status");
 					connection.newLine();
 					connection.flush();
 					
 					try
 					{
 						Thread.sleep(1000);
 					}
 					catch(InterruptedException e)
 					{
 					}
 					
 					currentStatus = status.getString();
 					splitStatus = currentStatus.split(",");
					  while (splitStatus.length != 5)
                                        {
                                                currentStatus = status.getString();
                                                splitStatus = currentStatus.split(",");
                                        }

 					currentHeading = splitStatus[1];
 				
 					newHeading = Integer.parseInt(currentHeading) - Integer.parseInt(command[1]);
 					System.out.println("new heading is " + newHeading);
 					if (newHeading < 0)
 					{
 						newHeading = newHeading + 359;
 					}
 				
 					turn = 1;
 					connection.write("bvl");
 					connection.newLine();
 					connection.flush();
 					
 					try
 					{
 						Thread.sleep(1000);
 					}
 					catch(InterruptedException e)
 					{
 					}
 				
 					while (turncomplete == false)
 					{
 						if (newHeading > Integer.parseInt(currentHeading))
 						{
 							connection.write("status");
 							connection.newLine();
 							connection.flush();
 							
 							try
 							{
 								Thread.sleep(1000);
 							}
 							catch(InterruptedException e)
 							{
 							}
 					
 							currentStatus = status.getString();
 							splitStatus = currentStatus.split(",");
							  while (splitStatus.length != 5)
		                                        {
                		                                currentStatus = status.getString();
                                		                splitStatus = currentStatus.split(",");
                                      			  }

 							currentHeading = splitStatus[1];
 						}
 						else
 							turncomplete = true;
 					}
 					
 					turncomplete = false;
 					
 					while (turncomplete == false)
 					{
 						if(Integer.parseInt(currentHeading) > newHeading)
 						{
							connection.write("status");
							connection.newLine();
							connection.flush();
							
							try
							{
								Thread.sleep(1000);
							}
							catch(InterruptedException e)
							{
							}
					
							currentStatus = status.getString();
							splitStatus = currentStatus.split(",");
							  while (splitStatus.length != 5)
		                                        {
                		                                currentStatus = status.getString();
                                		                splitStatus = currentStatus.split(",");
                                       			 }

							currentHeading = splitStatus[1];
						}
						else
							turncomplete = true;
					
 					}
 				
 					turncomplete = false;
 				
 					connection.write("evl");
 					connection.newLine();
 					connection.flush();
 					
 					try
 					{
 						Thread.sleep(1000);
 					}
 					catch(InterruptedException e)
 					{
 					}
 				}
 				else if (command[0].equals("right"))
 				{
 					connection.write("status");
 					connection.newLine();
 					connection.flush();
 					
 					try
 					{
 						Thread.sleep(1000);
 					}
 					catch(InterruptedException e)
 					{
 					}
 					
 					currentStatus = status.getString();
 					splitStatus = currentStatus.split(",");
					  while (splitStatus.length != 5)
                                        {
                                                currentStatus = status.getString();
                                                splitStatus = currentStatus.split(",");
                                        }

 					currentHeading = splitStatus[1];
 				
 					newHeading = Integer.parseInt(currentHeading) + Integer.parseInt(command[1]);
 					System.out.println("new heading is " + newHeading);
 					if (newHeading > 359)
 					{
 						newHeading = newHeading - 359;
 					}
 				
 					turn = 2;
 					connection.write("bvr");
					connection.newLine();
 					connection.flush();
 					
 					try
 					{
 						Thread.sleep(1000);
 					}
 					catch(InterruptedException e)
 					{
 					}
 				
 					while (turncomplete == false)
 					{
 						if (newHeading < Integer.parseInt(currentHeading))
 						{
 							connection.write("status");
 							connection.newLine();
 							connection.flush();
 							
 							try
 							{
 								Thread.sleep(1000);
 							}
 							catch(InterruptedException e)
 							{
 							}
 					
 							currentStatus = status.getString();
 							splitStatus = currentStatus.split(",");
							 while (splitStatus.length != 5)
		                                        {
                		                                currentStatus = status.getString();
                                		                splitStatus = currentStatus.split(",");
                                       			 }

 							currentHeading = splitStatus[1];
 						}
 						else
 							turncomplete = true;
 					}
 					
 					turncomplete = false;
 					
 					while (turncomplete == false)
 					{
						if(Integer.parseInt(currentHeading) < newHeading)
						{
							connection.write("status");
							connection.newLine();
							connection.flush();
							
							try
							{
								Thread.sleep(1000);
							}
							catch(InterruptedException e)
							{
							}
				
							currentStatus = status.getString();
							splitStatus = currentStatus.split(",");
							 while (splitStatus.length != 5)
		                                        {
                		                                currentStatus = status.getString();
                                		                splitStatus = currentStatus.split(",");
                                      			  }

							currentHeading = splitStatus[1];
						}
						else
							turncomplete = true;
 					}
 				
 					turncomplete = false;
 				
 					connection.write("evr");
 					connection.newLine();
 					connection.flush();
 					
 					try
 					{
 						Thread.sleep(1000);
 					}
 					catch(InterruptedException e)
 					{
 					}
 					
 				}
 				else if (command[0].equals("stop"))
 				{
 					connection.write("evf");
 					connection.newLine();
 					connection.flush();
 					
 					try
 					{
 						Thread.sleep(1000);
 					}
 					catch(InterruptedException e)
 					{
 					}
 					
 				}
 				else if (command[0].equals("speed"))
 				{
 					System.out.println("new speed is " + command[1]);
 					connection.write(command[1]);
 					connection.newLine();
 					connection.flush();
 					
 					try
 					{
 						Thread.sleep(1000);
 					}
 					catch(InterruptedException e)
 					{
 					}
 					
 				}
 				else if (command[0].equals("reverse"))
 				{
 					numberOfRevs = ((Integer.parseInt(command[1])*12)/51);
 					numberOfTicks = ((Integer.parseInt(command[1])*12)%51)/7;
 				
 					connection.write("evf");
 					connection.newLine();
 					connection.flush();
 					
 					try
 					{
 						Thread.sleep(1000);
 					}
 					catch(InterruptedException e)
 					{
 					}
 					
 					connection.write("rev");
 					connection.newLine();
 					connection.flush();
 					
 					try
 					{
 						Thread.sleep(1000);
 					}
 					catch(InterruptedException e)
 					{
 					}
 					
 					connection.write("bvf");
 					connection.newLine();
 					connection.flush();
 					
 					try
 					{
 						Thread.sleep(1000);
 					}
 					catch(InterruptedException e)
 					{
 					}
 					
 					connection.write("status");
 					connection.newLine();
 					connection.flush();
 					
 					try
 					{
 						Thread.sleep(1000);
 					}
 					catch(InterruptedException e)
 					{
 					}
 					
 					currentStatus = status.getString();
 					splitStatus = currentStatus.split(",");
					 while (splitStatus.length != 5)
                                        {
                                                currentStatus = status.getString();
                                                splitStatus = currentStatus.split(",");
                                        }

 					currentRev = splitStatus[3];
 					endingRevs = Integer.parseInt(currentRev) + numberOfRevs;
 					System.out.println("ending revs is " + endingRevs);
 					while(Integer.parseInt(currentRev) <= endingRevs)
 					{	
 						connection.write("status");
 						connection.newLine();
 						connection.flush();
 						
 						try
 						{
 							Thread.sleep(1000);
 						}
 						catch(InterruptedException e)
 						{
 						}
 					
 						currentStatus = status.getString();
 						splitStatus = currentStatus.split(",");
						 while (splitStatus.length != 5)
	                                        {
        	                                        currentStatus = status.getString();
                	                                splitStatus = currentStatus.split(",");
                        	                }

 						currentRev = splitStatus[3];
 					}
 				/*
 					connection.write("rsTicks");
 					connection.newLine();
 					connection.flush();
 					
 					try
 					{
 						Thread.sleep(1000);
 					}
 					catch(InterruptedException e)
 					{
 					}
 					
 					connection.write("status");
 					connection.newLine();
 					connection.flush();
 					
 					try
 					{
 						Thread.sleep(1000);
 					}
 					catch(InterruptedException e)
 					{
 					}
 					
 					currentStatus = status.getString();
 					splitStatus = currentStatus.split(",");
 					currentTicks = splitStatus[2];
 					System.out.println("ending ticks is " + numberOfTicks);
 					while(Integer.parseInt(currentTicks) <= numberOfTicks)
 					{
 						connection.write("status");
 						connection.newLine();
 						connection.flush();
 						
 						try
 						{
 							Thread.sleep(1000);
 						}
 						catch(InterruptedException e)
 						{
 						}
 					
 						currentStatus = status.getString();
 						splitStatus = currentStatus.split(",");
 						currentRev = splitStatus[2];
 					}
 				*/
 					connection.write("evf");
 					connection.newLine();
 					connection.flush();
 					
 					try
 					{
 						Thread.sleep(1000);
 					}
 					catch(InterruptedException e)
 					{
 					}
 					
 					connection.write("fwd");
 					connection.newLine();
 					connection.flush();
 					
 					try
 					{
 						Thread.sleep(1000);
 					}
 					catch(InterruptedException e)
 					{
 					}
 					
 				}
 			
 				currentline = reader.readLine();
 					
			}
			catch(IOException e)
			{
			}
		}
	}
}
