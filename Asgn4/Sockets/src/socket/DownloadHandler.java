/*
	Programmer: Josh Long
	Assignment: 4a GUI Sockets
	Date: 2/5/16
 */

package socket;

import java.io.*;
import java.net.*;

public class DownloadHandler implements Runnable
{
	private Socket s;
	
	OutputStream sout = null;
	BufferedReader sin = null;
	String fileName;
	FileInputStream fin = null;
	
	public DownloadHandler(Socket socket)
	{
		s = socket;
	}
	
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
}
