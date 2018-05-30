/*
Programmer: Josh Long
Assignment: 6 RMI Calculator
Date:2/19/16
*/

package myClient;

import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.rmi.*;
import javax.swing.*;
import myServer.*;

public class JFrameExt  extends JFrame implements ActionListener
{
JPanel one = new JPanel();
JPanel two = new JPanel();
JPanel three = new JPanel();
JPanel JOps = new JPanel();

JButton add = new JButton("Add");
JButton sub = new JButton("Sub");
JButton div = new JButton("Div");
JButton mult = new JButton("Mult");
JButton clear = new JButton("Clear");

JLabel lNum1 = new JLabel("Number 1: ");
JTextField num1 = new JTextField(8);
JLabel lNum2 = new JLabel("Number 2: ");
JTextField num2 = new JTextField(8);
JLabel lResult = new JLabel("Result: ");
JTextField result = new JTextField(8);

Calculator calc;

public JFrameExt()
{
	super("Remote Calculator");
	
	one.add(lNum1);
	one.add(num1);
	two.add(lNum2);
	two.add(num2);
	three.add(lResult);
	three.add(result);
	JOps.add(add);
	JOps.add(sub);
	JOps.add(div);
	JOps.add(mult);
	JOps.add(div);
	JOps.add(clear);
	
	setLayout(new GridLayout(4,1));
	
	add(one);
	add(two);
	add(three);
	add(JOps);
	
	setSize(350, 250);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setVisible(true);
	
	add.addActionListener(this);
	sub.addActionListener(this);
	div.addActionListener(this);
	mult.addActionListener(this);
	clear.addActionListener(this);
	
	//Call Naming.lookup to obtain the reference of the local stub object.	

	try {
		calc = (Calculator) Naming.lookup ("rmi://localhost/CalcServer");
	} catch (MalformedURLException | RemoteException | NotBoundException e) {
		e.printStackTrace();
	}
	//Provide lookup with a url containing the name of the host running RMIRegistery
	//and the name under which the target remote server object is registered.
	 
	//(lookup will communicate with RMIRegistry to get the name of the stub class
	//for the target object. It will search for the file containing the stub class locally,
	//create an object of the stub class and will return its reference.)
	
}

@Override
public void actionPerformed(ActionEvent e)
{
	String n1 = num1.getText();
	String n2 = num2.getText();
	Double a = Double.parseDouble(n1);
	Double b = Double.parseDouble(n2);
	
	if(e.getSource() == add)
	{
		Double res = 0.0;
		try {
			res = Calculator.add(a, b);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		String ret = Double.toString(res);
		result.setText(ret);
	}
	if(e.getSource() == sub)
	{
		Double res = 0.0;
		try {
			res = Calculator.sub(a, b);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		String ret = Double.toString(res);
		result.setText(ret);
	}
	if(e.getSource() == mult)
	{
		Double res = 0.0;
		try {
			res = Calculator.mult(a, b);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		String ret = Double.toString(res);
		result.setText(ret);
	}
	if(e.getSource() == div)
	{
		Double res = 0.0;
		try {
			res = Calculator.div(a, b);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		String ret = Double.toString(res);
		result.setText(ret);
	}
	if(e.getSource() == clear)
	{
		num1.setText("");
		num2.setText("");
		result.setText("");
	}
}
}
