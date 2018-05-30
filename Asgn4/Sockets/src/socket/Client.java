/*
	Programmer: Josh Long
	Assignment: 4a GUI Sockets
	Date: 2/5/16
 */

package socket;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class Client implements ActionListener
{
	JFrame main = new JFrame("Server Download");
	JPanel jpNorth = new JPanel();
	JPanel jpCenter = new JPanel();
	JPanel jpSouth = new JPanel();
	private JLabel label1 = new JLabel("Remote Host: ");
	private JTextField jtfRemoteHost = new JTextField (8);
	private JLabel label2 = new JLabel("File Name: ");
	private JTextField jtfFileName = new JTextField (8);
	
	private JTextArea jta = new JTextArea (11, 30);
	
	JScrollPane jscrMessage = new JScrollPane (jta,
			  JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			  JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	
	JButton download = new JButton("Download");
	JButton clear = new JButton("Clear");
	
	public Client()
	{	
		main.setLayout(new BorderLayout(5, 10));
		
		jpNorth.add(label1);
		jpNorth.add(jtfRemoteHost);
		jpNorth.add(label2);
		jpNorth.add(jtfFileName);

		jpCenter.add(jscrMessage);
		
		jpSouth.add(download);
		jpSouth.add(clear);
		
		main.add(jpNorth, BorderLayout.NORTH);
		main.add(jpCenter);
		main.add(jpSouth, BorderLayout.SOUTH);
		
		main.setLocation(675,250);
		main.setSize(400, 327);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setVisible(true);
		
		clear.addActionListener(this);
		download.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == clear)
		{
			jta.setText("");
		}
		
		if(e.getSource() == download)
		{
			jta.append("Starting download of " + jtfFileName.getText() + "\n");
			
			long startTime = new Date().getTime();
    		long lines = 0;
    		
    		try
    	    {
    	      //Declare a Socket
    	      Socket s = null;
    	     
    	     
    	      //Get remote host and down load file name from text fields
    	      String remoteHost = jtfRemoteHost.getText();
    	      String fileName = jtfFileName.getText();
    	     
    	      //Set remote port to 1234
    	      int remotePort = 1234;
    	     
    	      //Create a socket connected to remote server
    	      if (remoteHost.equalsIgnoreCase("localhost"))
    	      {
    	        s = new Socket("localhost",remotePort);
    	      }
    	      else
    	      {
    	        InetAddress inet = InetAddress.getByName(remoteHost);
    	        s = new Socket(inet , remotePort);
    	      }
    	     
    	      //Get an InputStream over Socket for inputting bytes.
    	      InputStream sin = s.getInputStream();
    	     
    	      //Get an OutputStream over Socket.
    	      //Get PrintWriter over OutPutStream over Socket for outputting text lines.
    	      PrintWriter sout = new PrintWriter(s.getOutputStream(), true);
    	     
    	      //Send file name as a text line to PrintWriter (sout) over Socket.
    	      //Issue a flush to make sure, it goes there immediately.
    	      sout.println(fileName.trim());
    	      sout.flush();
    	     
    	      //Create FileOutputStream (fout) for writing bytes to file.
    	      FileOutputStream fout = new FileOutputStream(fileName+".copy");
    	 
    	     
    	     //initialize byte count
    	      int byteCount = 0;
    	 
    	      //Read byte at a time from InputStrem (sin) over Socket
    	      //Write the byte to file using FileOutputStream (fout).
    	      int r;
    	      while ((r = sin.read()) != -1)
    	      {
    	        fout.write((byte) r);
    	        byteCount = byteCount + 1;
    	       
    	        //Display a dot every 1000 bytes.
    	        //Paint text area immediately.
    	        if (byteCount%1000==0)
    	        {
    	          jta.append(".");
    	          jta.paintImmediately(0,0,jta.getWidth(), jta.getHeight());
    	        }
    	        lines++;
    	 
    	      }
    	 
    	      //Close both socket streams and the file stream.
    	      fout.close();
    	      sin.close();
    	      sout.close();
    	      s.close();
    	 
    	    }
    	    catch (Exception ex)
    	    {
    	       ex.printStackTrace( );
    	    }
    		
    		long endTime = new Date().getTime();
    		
    		double diffTime =(double) (endTime - startTime);
	    	 
    	    if (diffTime > 0)
    	    	diffTime = diffTime/1000.0;
    	    
			jta.append("\nByte Count: " + lines + "\n");
			jta.append("Download time: " + diffTime + " seconds\n");
			jta.append("Ending download of " + jtfFileName.getText() + "\n\n");
		}
	}
	
	public static void main(String[] args) 
	{
		Client c = new Client();
	}
}

