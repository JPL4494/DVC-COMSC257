/*
	Programmer: Josh Long
	Assignment: Midterm
	Date: 3/6/16
*/

package client;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.rmi.*;
import javax.swing.*;
import server.*;
import atmAlert.*;

public class JFrameExt extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	JPanel input = new JPanel();
	JPanel output = new JPanel();
	
	JMenuBar menu = new JMenuBar();
	JMenu operators = new JMenu("Operators");
	JMenuItem open = new JMenuItem("Open");
	JMenuItem close = new JMenuItem("Close");
	JMenuItem balance = new JMenuItem("Balance");
	JMenuItem deposit = new JMenuItem("Deposit");
	JMenuItem withdraw = new JMenuItem("Withdraw");
	JMenuItem exit = new JMenuItem("Exit");
	
	JLabel bankName = new JLabel("Bank Name: ");
	JTextField bName = new JTextField(7);
	JLabel accNum = new JLabel("Enter Account: ");
	JTextField acc = new JTextField(5);
	JLabel operator = new JLabel();
	JTextField operInput = new JTextField(5);
	JLabel charge = new JLabel("Fee: ");
	JComboBox<String> fees = new JComboBox<String>();
	
	public static JTextArea jta = new JTextArea (11, 30);
	
	JScrollPane jscrMessage = new JScrollPane (jta,
			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	
	AccountManager account;
	int choice = 0;
	AlertImpl check;
	
	public JFrameExt()
	{
		super("ATM");
		
		operators.add(open);
		operators.add(close);
		operators.add(balance);
		operators.add(deposit);
		operators.add(withdraw);
		operators.add(exit);
		
		menu.add(operators);
		setJMenuBar(menu);
		
		input.add(bankName);
		input.add(bName);
		input.add(accNum);
		input.add(acc);
		
		fees.addItem("0.00");
		fees.addItem("1.00");
		fees.addItem("1.50");
		fees.addItem("2.00");
		
		add(input, BorderLayout.NORTH);
		add(jscrMessage);
		
		setSize(600, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		try {
			account = (AccountManager) Naming.lookup ("rmi://localhost/ATMserver");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
		
		exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
		open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                input.remove(operator);
                input.remove(operInput);
                input.remove(charge);
                input.remove(fees);
                revalidate();
                repaint();
                
                choice = 1;
            }
        });
		close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	input.remove(operator);
                input.remove(operInput);
                input.remove(fees);
                input.remove(charge);
                revalidate();
                repaint();
                
                choice = 2;
            }
        });
		balance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	input.remove(operator);
                input.remove(operInput);
                input.remove(fees);
                input.remove(charge);
                revalidate();
                repaint();
                
                choice = 3;
            }
        });
		deposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                operator.setText("Deposit Amount: ");
                input.add(operator);
                input.add(operInput);
                input.add(charge);
                input.add(fees);
                revalidate();
                repaint();
                
                choice = 4;
            }
        });
		withdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                operator.setText("Withdraw Amount: ");
                input.add(operator);
                input.add(operInput);
                input.add(charge);
                input.add(fees);
                revalidate();
                repaint();
                
                choice = 5;
            }
        });
		acc.addActionListener(this);
		operInput.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String n1 = acc.getText();
    	String n2 = operInput.getText();
    	String bankName = bName.getText();
    	if(n2.isEmpty())
    		n2 = "0";
    	int a = Integer.parseInt(n1);
    	int b = Integer.parseInt(n2);
    	
		if(choice == 1)
		{
			jta.append("Adding account with account number " + a + " from bank " + bankName + "\n");
			try {
				account.addAccount(a, check);
				jta.append("Account added successfully\n\n");
			} catch (RemoteException e1) {
				e1.printStackTrace();
				jta.append("Error, account not added\n\n");
			}
		}
		if(choice == 2)
		{
			jta.append("Removing account with account number " + a + " from bank " + bankName + "\n");
			try {
				account.removeAccount(a);
				jta.append("Account removed successfully\n\n");
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}
		if(choice == 3)
		{
			Account temp;
			jta.append("Checking balance of account " + a + " from bank " + bankName + "\n");
			try {
				temp = account.findAccount(a, check);
				double bal = temp.getBalance();
				jta.append("Balance of " + a + " is $" + bal + "\n\n");
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}
		if(choice == 4)
		{
			int fee = fees.getSelectedIndex();
			double cost = 0;
			if(fee == 0)
				cost = 0;
			if(fee == 1)
				cost = 1.00;
			if(fee == 2)
				cost = 1.50;
			if(fee == 3)
				cost = 2.00;
			AccountManagerCharges c = new AccountManagerCharges(bankName, cost);
			
			jta.append("Depositing $" + b + " into account " + a + " from bank " + bankName + "\n");
			try {
				account.deposit(a, b, c, check);
				jta.append("Money deposited successfully\n\n");
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}
		if(choice == 5)
		{
			int fee = fees.getSelectedIndex();
			double cost = 0;
			if(fee == 0)
				cost = 0;
			if(fee == 1)
				cost = 1.00;
			if(fee == 2)
				cost = 1.50;
			if(fee == 3)
				cost = 2.00;
			AccountManagerCharges c = new AccountManagerCharges(bankName, cost);
			
			jta.append("Withdrawing $" + b + " from account " + a + " from bank " + bankName + "\n");
			try {
				account.withdraw(a, b, c, check);
				jta.append("Money withdrawed successfully\n\n");
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}
	}
}
