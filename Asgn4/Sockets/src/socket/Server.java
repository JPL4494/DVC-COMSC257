/*
	Programmer: Josh Long
	Assignment: 4a GUI Sockets
	Date: 2/5/16
 */

package socket;

import java.io.*;
import java.net.*;

public class Server 
{
	ServerSocket ss = null;
	Socket s = null;
	
	public Server()
	{
		int serverPort = 1234;
	    try
	    {
	      //Create a SeverSocket to server port.
	      ss = new ServerSocket(serverPort);
	 
	      //Repeat:
	      //Accept a connection from client
	      //Return Socket communicating with Client.
	      while(true)
	      {
	 
	    	//Accept a connection from Client
	        s = ss.accept();
	   
	        //Create DownloadHandler object passing it the Socket object in its constructor.
	        DownloadHandler dh = new DownloadHandler(s);
	        
	        //Create a Thread object passing it the DownloadHandler object.
	        Thread thread = new Thread(dh);
	   
	        //Call the start method of Thread object.
	        //This will start the execution of run method of DownloadHandler object on a new thread.
	        thread.start();
	      }
	    }
	    catch (Exception ex)
	    {
	      ex.printStackTrace();
	    }	   
	}
	
	
	public static void main(String [] args)
    {
		try
	    {
			FileOutputStream hundredk = new FileOutputStream("HundredK.dat");
	        PrintWriter tenk =  new PrintWriter (new FileWriter ("TenK.txt"));
	 
	        byte b=0;
	 
	        for (int i=0;i<1024*100;i++)
	        {
	        	hundredk.write(b);
	            b++;
	        }
	        hundredk.close();
	 
	        String t="Hi";
	 
	        for (int i=0;i<1024*10;i++)
	        {
	            tenk.println(t);
	        }
	        tenk.close();
	    }
	    catch (Exception e)
	    {
	    	System.err.println("Input error");
	    }
		
		new Server();
    }
}
