import java.util.*;
import java.io.*;
import java.net.*;

/*
 * to compile:
 * javac -cp /usr/share/java/RXTXcomm.jar:. *.java
 * 
 * If your serial port isn't detected when you try to connect to it,  specify it as an 
 * execution parameter.  
 * for example, -Dgnu.io.rxtx.SerialPorts=/dev/ttyACM0:/dev/ttyS8:/dev/<your serial port name here>
 * 
 * to run:
 * java -cp /usr/share/java/RXTXcomm.jar:. -Dgnu.io.rxtx.SerialPorts=/dev/ttyACM0    ArduinoReadWrite
 * 
 * You'll need the RXTX library installed, and then specify
 * the path to the RXTXcomm.jar file.  Depending your linux distro, 
 * it may not be in /usr/share/java
 *
 * inside the arduino directory, there is a project called serial_read.  
 * the arduino reads in stuff on the serial port, then prints it right back
 * out to the serial port.  Use this arduino code to test this java program.
 */

public class readwrite
{ 
     public static void main(String[] args)
     {
          GolfCartStatus thestatus = new GolfCartStatus();
          BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
          String input = "";
          
          String port = "/dev/ttyACM0";
          port = "/dev/tty.usbmodem1411";
          String consoleReaderName = "Reader";
          try 
          {
               ArduinoReadWrite arduino = new ArduinoReadWrite(thestatus);
               arduino.connect(port);
               OutputStream arduinoStream = arduino.getTheStream();
               
               BufferedWriter mywriter = new BufferedWriter(new OutputStreamWriter(arduinoStream));
               
               ConsoleReadingThread consoleReader1 = new ConsoleReadingThread(mywriter,reader,consoleReaderName);
               consoleReader1.start();
               System.out.println("port: " + port + " ready");
               
               //start the event timer
               //mytimer t = new mytimer(mywriter,reader,1); //how many seconds
               
               Timer filetimer = new Timer();
		  	   filetimer.schedule(new checkfile(mywriter), 0, 1000);
		  	   
          }
          catch ( Exception e )
          {
               e.printStackTrace();
          }
		  
     }//end main
}//end class readwrite

class checkfile extends TimerTask
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