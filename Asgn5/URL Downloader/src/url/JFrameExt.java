/*
	Programmer: Josh Long
	Assignment: 5c URL downloader
	Date: 2/18/16
*/

package url;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.*;

public class JFrameExt extends JFrame implements ActionListener
{
	JLabel u = new JLabel("URL: ");
	JLabel fn = new JLabel("File Name: ");
	JTextField tU = new JTextField(20);
	JTextField tFN = new JTextField(13);
	JButton download = new JButton("URL Download");
	JButton clear = new JButton("Clear");
	private JTextArea jta = new JTextArea (11, 40);
	
	JScrollPane jscrMessage = new JScrollPane (jta,
			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	
	JPanel north = new JPanel();
	JPanel center = new JPanel();
	JPanel south = new JPanel();
	
	public JFrameExt()
	{
		super("URL Downloader");
		
		north.add(u);
		north.add(tU);
		north.add(fn);
		north.add(tFN);
		
		center.add(jscrMessage);
		
		south.add(download);
		south.add(clear);
		
		add(north, BorderLayout.NORTH);
		add(center);
		add(south, BorderLayout.SOUTH);
		
		setSize(550, 327);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		download.addActionListener(this);
		clear.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String s = tU.getText();
		String f = tFN.getText();
		
		if(e.getSource() == clear)
		{
			jta.setText("");
		}
		
		if(e.getSource() == download)
		{
			URL url = null;
			try {
				url = new URL (s);
			} catch (MalformedURLException e4) {
				e4.printStackTrace();
			}
		    URLConnection urlc = null;
			try {
				urlc = url.openConnection();
			} catch (IOException e4) {
				e4.printStackTrace();
			}
		    InputStream is = null;
			try {
				is = urlc.getInputStream();
			} catch (IOException e4) {
				e4.printStackTrace();
			}
		     
		    byte [] buf = new byte [1024];
		 
		    FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(f);
			} catch (FileNotFoundException e3) {
				e3.printStackTrace();
			}
		  
		    int byteCount = 0;
		    long startTime = new Date().getTime();
		    
		    //Read from is InputStrem over the internet
		    //Write to local file using fos FileOutputStream.
		    int n;
		    try {
				while ((n = is.read(buf,0,buf.length)) != -1)
				  {
				    try {
						fos.write(buf,0,n);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				    byteCount = byteCount + n;  
				  }
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		      
		      try {
				fos.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		      
		      long endTime = new Date().getTime();
	    		
	    		double diffTime =(double) (endTime - startTime);
		    	 
	    	    if (diffTime > 0)
	    	    	diffTime = diffTime/1000.0;
		      
		      jta.append("Starting download of " + s);
		      jta.append("\n");
		      jta.append("Byte Count: " + byteCount + "\n");
		      jta.append("Download Time: " + diffTime + "\n");
		      jta.append("Ending download of " + s + "\n\n");
		}
	}
}
