/*
	Programmer: Josh Long
	Assignment: 4c Multi Ticker
	Date: 2/5/16
 */

package ticker;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

public class JFrameExt extends JFrame implements ActionListener
{
	JButton start = new JButton("Start All");
	JButton stop = new JButton("Stop All");
	
	JPanel temp = new JPanel();
	Ticker J1 = new Ticker("DVC 32.0");
	Ticker J2 = new Ticker("LMC 25.0");
	Ticker J3 = new Ticker("CCC 45.1");
	JPanel JOps = new JPanel();
	
	public JFrameExt()
	{
		super("Simple Ticker");
		super.setLayout(new GridLayout(4,1));
		JOps.add(start);
		JOps.add(stop);
		JOps.setBackground(Color.BLUE);
		J1.setBackground(Color.RED);
		J2.setBackground(Color.WHITE);
		J3.setBackground(Color.GREEN);
		
		super.add(J1);
		super.add(J2);
		super.add(J3);
		super.add(JOps, BorderLayout.SOUTH);
		
		start.addActionListener(this);
		stop.addActionListener(this);
		
		super.setSize(320, 320);
		super.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == start)
		{
			J1.setMoving(true);
			J2.setMoving(true);
			J3.setMoving(true);
		}
		
		if(e.getSource() == stop)
		{
			J1.setMoving(false);
			J2.setMoving(false);
			J3.setMoving(false);
		}
	}
	
	public static void main(String[] args) 
	{
		JFrameExt j = new JFrameExt();
	}
}