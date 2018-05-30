/*
	Programmer: Josh Long
	Assignment: 4b Simple Ticker
	Date: 2/5/16
 */

package ticker;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

public class JFrameExt extends JFrame implements ActionListener
{
	JButton start = new JButton("Start");
	JButton stop = new JButton("Stop");
	
	JPanel temp = new JPanel();
	Ticker JCounter = new Ticker();
	JPanel JOps = new JPanel();
	
	public JFrameExt()
	{
		super("Simple Ticker");
		JOps.add(start);
		JOps.add(stop);
		JOps.setBackground(Color.BLUE);
		
		super.add(JCounter, BorderLayout.CENTER);
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
			JCounter.setMoving(true);
		
		if(e.getSource() == stop)
			JCounter.setMoving(false);
	}
	
	public static void main(String[] args) 
	{
		JFrameExt j = new JFrameExt();
	}
}