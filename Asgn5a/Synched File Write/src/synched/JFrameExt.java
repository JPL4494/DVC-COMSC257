/*
	Programmer: Josh Long
	Assignment: 5a Sync File Write
	Date: 2/18/16
*/

package synched;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class JFrameExt extends JFrame implements ActionListener
{
	JButton noSync = new JButton("NoSync");
	JButton compSync = new JButton("CompSync");
	JButton coopSync = new JButton("CoopSync");
	JButton display = new JButton("Display");
	JButton clear = new JButton("Clear");
	
	JLabel JM1 = new JLabel("Message1: ");
	JTextField JF1 = new JTextField(8);
	JLabel JM2 = new JLabel("Message2: ");
	JTextField JF2 = new JTextField(8);
	JLabel JM3 = new JLabel("Count: ");
	JTextField JF3 = new JTextField(8);
	JLabel JM4 = new JLabel("FileName: ");
	JTextField JF4 = new JTextField(8);
	
	JPanel top = new JPanel();
	JPanel message = new JPanel();
	JPanel JOps = new JPanel();
	
private JTextArea jta = new JTextArea (11, 50);
	
JScrollPane jscrMessage = new JScrollPane (jta,
		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	
	public JFrameExt()
	{
		super("Sync Runnable");
		JOps.add(noSync);
		JOps.add(compSync);
		JOps.add(coopSync);
		JOps.add(display);
		JOps.add(clear);
		
		top.add(JM1);
		top.add(JF1);
		top.add(JM2);
		top.add(JF2);
		top.add(JM3);
		top.add(JF3);
		top.add(JM4);
		top.add(JF4);
		
		message.add(jscrMessage);
		
		add(top, BorderLayout.NORTH);
		add(message);
		add(JOps, BorderLayout.SOUTH);
		
		setSize(700, 327);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		noSync.addActionListener(this);
		compSync.addActionListener(this);
		coopSync.addActionListener(this);
		display.addActionListener(this);
		clear.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String m1, m2, fn;
		int c;
		Object o = new Object();
		
		m1 = JF1.getText();
		m2 = JF2.getText();
		fn = JF4.getText();
		c = Integer.parseInt(JF3.getText());
		
		if(e.getSource() == noSync)
		{
			NoSynchRunnable n1 = new NoSynchRunnable(m1, c, fn);
			NoSynchRunnable n2 = new NoSynchRunnable(m2, c, fn);
			Thread t1 = new Thread(n1);
			Thread t2 = new Thread(n2);
			t1.start();
			t2.start();
		}
		if(e.getSource() == compSync)
		{
			CompSyncRunnable n1 = new CompSyncRunnable(m1, c, fn, o);
			CompSyncRunnable n2 = new CompSyncRunnable(m2, c, fn, o);
			Thread t1 = new Thread(n1);
			Thread t2 = new Thread(n2);
			t1.start();
			t2.start();
		}
		if(e.getSource() == coopSync)
		{
			CoopSyncRunnable n1 = new CoopSyncRunnable(m1, c, fn, o);
			CoopSyncRunnable n2 = new CoopSyncRunnable(m2, c, fn, o);
			Thread t1 = new Thread(n1);
			Thread t2 = new Thread(n2);
			t1.start();
			t2.start();
		}
		if(e.getSource() == clear)
		{
			jta.setText("");
		}
		if(e.getSource() == display)
		{
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(fn));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			
			String line = null;
			try {
				line = br.readLine();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			while(line != null)
			{
				jta.append(line);
				try {
					line = br.readLine();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			jta.append("\n\n");
		}
	}
}
