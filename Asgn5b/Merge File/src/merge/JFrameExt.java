/*
	Programmer: Josh Long
	Assignment: 5b File Merger
	Date: 2/18/16
*/

package merge;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;

public class JFrameExt extends JFrame implements ActionListener
{
	JPanel north = new JPanel();
	JPanel center = new JPanel();
	JPanel south = new JPanel();
	
	JButton mD = new JButton("Merge (DeadLock)");
	JButton mDF = new JButton("Merge (DeadLock-Free)");
	
	JLabel f1 = new JLabel("File A: ");
	JTextField jt1 = new JTextField(7);
	JLabel f2 = new JLabel("File B: ");
	JTextField jt2 = new JTextField(7);
	
	private JTextArea jta = new JTextArea (11, 30);
	
	JScrollPane jscrMessage = new JScrollPane (jta,
			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	
	public JFrameExt()
	{
		super("File Merger");
		
		north.add(f1);
		north.add(jt1);
		north.add(f2);
		north.add(jt2);
		center.add(jscrMessage);
		south.add(mD);
		south.add(mDF);
		
		add(north, BorderLayout.NORTH);
		add(center);
		add(south, BorderLayout.SOUTH);
		
		setSize(400, 327);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		mD.addActionListener(this);
		mDF.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String s1 = jt1.getText();
		String s2 = jt2.getText();
		String e1 = "FileAB.txt";
		String e2 = "FileBA.txt";
		Object objA, objB, objAB;
		objA = "";
		objB = "";
		objAB = "";
		
		if(e.getSource() == mD)
		{
			MergeRunnable m1 = new MergeRunnable(s1, s2, e1, objA, objB);
			MergeRunnable m2 = new MergeRunnable(s2, s1, e2, objA, objB);
			
			Thread t1 = new Thread(m1);
			Thread t2 = new Thread(m2);
			t1.start();
			t2.start();
			
			BufferedReader f1 = null;
			BufferedReader f2 = null;
			try {
				f1 = new BufferedReader(new FileReader(e1));
			} catch (FileNotFoundException q) {
				q.printStackTrace();
			}
            try {
				f2 = new BufferedReader(new FileReader(e2));
			} catch (FileNotFoundException q) {
				q.printStackTrace();
			}
            String line = null;
			try {
				line = f1.readLine();
			} catch (IOException e3) {
				e3.printStackTrace();
			}
            while(line != null)
            {
            	jta.append(line);
            	try {
					line = f1.readLine();
				} catch (IOException e3) {
					e3.printStackTrace();
				}
            }
            jta.append("\n\n");
            line = null;
			try {
				line = f2.readLine();
			} catch (IOException e3) {
				e3.printStackTrace();
			}
            while(line != null)
            {
            	jta.append(line);
            	try {
					line = f2.readLine();
				} catch (IOException e3) {
					e3.printStackTrace();
				}
            }
            jta.append("\n\n");
		}
		if(e.getSource() == mDF)
		{
			MergeRunnable2 m1 = new MergeRunnable2(s1, s2, e1, objA, objB, objAB);
			MergeRunnable2 m2 = new MergeRunnable2(s2, s1, e2, objA, objB, objAB);
			
			Thread t1 = new Thread(m1);
			Thread t2 = new Thread(m2);
			t1.start();
			t2.start();
			
			BufferedReader f1 = null;
			BufferedReader f2 = null;
			try {
				f1 = new BufferedReader(new FileReader(e1));
			} catch (FileNotFoundException q) {
				q.printStackTrace();
			}
            try {
				f2 = new BufferedReader(new FileReader(e2));
			} catch (FileNotFoundException q) {
				q.printStackTrace();
			}
            String line = null;
			try {
				line = f1.readLine();
			} catch (IOException e3) {
				e3.printStackTrace();
			}
            while(line != null)
            {
            	jta.append(line);
            	try {
					line = f1.readLine();
				} catch (IOException e3) {
					e3.printStackTrace();
				}
            }
            jta.append("\n\n");
            line = null;
			try {
				line = f2.readLine();
			} catch (IOException e3) {
				e3.printStackTrace();
			}
            while(line != null)
            {
            	jta.append(line);
            	try {
					line = f2.readLine();
				} catch (IOException e3) {
					e3.printStackTrace();
				}
            }
            jta.append("\n\n");
		}
	}
}
