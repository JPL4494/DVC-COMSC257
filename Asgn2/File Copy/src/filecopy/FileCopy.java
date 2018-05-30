/*
	Programmer: Josh Long
	Assignment: 2 GUI File Copy
	Date: 1/24/16
 */

package filecopy;

import java.awt.EventQueue;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class FileCopy 
{
	private JFrame frame;
	private JTextField tFrom;
	private JTextField tTo;

	public static void main(String[] args) 
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
		
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					FileCopy window = new FileCopy();
					window.frame.setVisible(true);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	
	public FileCopy() 
	{
		initialize();
	}
	
	private void initialize() 
	{
		frame = new JFrame("File Copy");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel file = new JPanel();
		frame.getContentPane().add(file, BorderLayout.NORTH);
		
		JLabel lblFrom = new JLabel("From:");
		file.add(lblFrom);
		
		tFrom = new JTextField();
		file.add(tFrom);
		tFrom.setColumns(10);
		
		JLabel lblTo = new JLabel("To:");
		file.add(lblTo);
		
		tTo = new JTextField();
		file.add(tTo);
		tTo.setColumns(10);
		
		JLabel lblType = new JLabel("Type");
		file.add(lblType);
		
		JComboBox cType = new JComboBox();
		file.add(cType);
		
		cType.addItem("");
		cType.addItem("Binary");
		cType.addItem("Text");
		
		JPanel status = new JPanel();
		frame.getContentPane().add(status, BorderLayout.CENTER);		
		
        JTextArea jtaMessage = new JTextArea(10, 35);

        JScrollPane jscrMessage = new JScrollPane (jtaMessage,
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jtaMessage.setEditable(false);
        jtaMessage.setLineWrap(true);
        
        status.add(jscrMessage);
		
		JPanel options = new JPanel();
		frame.getContentPane().add(options, BorderLayout.SOUTH);
		
		JButton copy = new JButton("Copy");
		options.add(copy);
		
		JButton clear = new JButton("Clear");
		options.add(clear);
		
		clear.addActionListener(new ActionListener()
	    {
	    	@Override
	         public void actionPerformed(ActionEvent e) 
	         {
	    		 jtaMessage.setText("");
	         }
	    });
		
		copy.addActionListener(new ActionListener()
	    {
	    	@Override
	         public void actionPerformed(ActionEvent e) 
	         {
	    		String fileName = new String(tFrom.getText());
	    		String toFile = new String(tTo.getText());
	    		jtaMessage.append("File Copy Started " + fileName + "\n");
	    		jtaMessage.paintImmediately(0,0,jtaMessage.getWidth(), jtaMessage.getHeight());
	    		
	    		long startTime = new Date().getTime();
	    		long lines = 0;
	    	    
	    	    String type = (String)cType.getSelectedItem();
	    	    
	    	    if(type.equalsIgnoreCase("binary"))
	    	    {
	    	    	FileInputStream fis=null;
	    	        FileOutputStream fos=null;

	    	          try {
						fis = new FileInputStream (fileName);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
	    	          try {
						fos = new FileOutputStream (toFile);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}    	     
	    	     
	    	          int r;
	    	          try {
						while ((r = fis.read()) != -1)
						  {
							  fos.write((byte) r);
						      fos.flush();
						      lines++;
						  }
					} catch (IOException e1) {
						e1.printStackTrace();
					}
	    	    }
	    	    else if(type.equalsIgnoreCase("text"))
	    	    {
	    	    	BufferedReader br=null;
	    	        PrintWriter pw=null;
	    	     
	    	        try {
						br = new BufferedReader( new FileReader(fileName));
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
	    	        try {
						pw = new PrintWriter (new FileWriter (toFile));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
	    	     
	    	        String line;
	    	        try {
						while ( (line = br.readLine()) != null)
						{
							pw.println(line);
						    pw.flush();
						    lines++;
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
	    	    }
	    	    
	    	    long endTime = new Date().getTime();
	    	    jtaMessage.append("File Copy Complete\n");
	    	    
	    	    double diffTime =(double) (endTime - startTime);
	    	 
	    	    if (diffTime > 0)
	    	    	diffTime = diffTime/1000.0;
	    	    
	    	    jtaMessage.append("Time to complete in seconds: " + diffTime + "\n");
	    	    jtaMessage.append("Lines copied: " + lines + "\n\n");
	         }
	    });
	}
}
