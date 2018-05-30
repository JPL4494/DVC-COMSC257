/*
	Programmer: Josh Long
	Assignment: 3 GUI Socket
	Date: 1/29/16
 */

package socket;

import java.io.*;
import java.net.*;

public class Server 
{
	ServerSocket ss = null;
	Socket s = null;
	OutputStream sout = null;
	BufferedReader sin = null;
	String fileName;
	FileInputStream fin = null;
	
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
	      while (true)
	      {
	 
	        //Accept a connection from Client. Save communication Socket returned in s.
	        s = ss.accept();
	       //Call run method to perform download using Socket s.
	       run();
	      }
	    }
	    catch (Exception ex)
	    {
	      ex.printStackTrace();
	    }	   
	}
	
	//Perform file download using Socket s.
    public void run()
    {
      String str;
      int r = 0;
 
	try
      {
        //Create OuputStream using socket.
        sout = s.getOutputStream();
       
        //Create InputStream using Socket.
        //Create InputStreamReader over InpuStream over Socket.
        //Create BufferReader over InputStreamReader over
        //InputStream over Socket for input text lines.
        sin = new BufferedReader(new InputStreamReader(s.getInputStream()));
       
        //Input a text line from BufferedReader over Socket
        //This should be the download file name.
        fileName = sin.readLine();
       
        //Create FileInputStream Object
        fin = new FileInputStream(fileName);
 
        //Repeat:
        //Read one byte at a time from FileInputStream.
        //Write the byte to OutputStream over Socket.
        while ((r = fin.read()) != -1)
        {
            sout.write((byte) r);
            sout.flush();
        }
 
      }
      catch (Exception ex)
      {
 
        System.err.println("Server: File error");
        ex.printStackTrace();
      }
      finally
      {
        try
        {
       
        if (sin != null)
          sin.close();
        if (sout != null)
          sout.close();
        if (fin != null)
          fin.close();
        }
        catch(Exception ex)
        {
          ex.printStackTrace();
        }
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
