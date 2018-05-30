/*
	Programmer: Josh Long
	Assignment: 1 GUI Calculator
	Date: 1/23/16
 */

package calc;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Asgn1 {

	private JFrame frame;
	private JTextField tNum1;
	private JTextField tNum2;
	private JTextField tRes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Asgn1 window = new Asgn1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Asgn1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{Double.MIN_VALUE};
		GridLayout g1 = new GridLayout(4,1);
		frame.getContentPane().setLayout(g1);
		
		JPanel jnum1 = new JPanel();
		frame.getContentPane().add(jnum1);
		jnum1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNumber = new JLabel("Number 1: ");
		jnum1.add(lblNumber);
		
		tNum1 = new JTextField();
		jnum1.add(tNum1);
		tNum1.setColumns(10);
		
		JPanel jnum2 = new JPanel();
		frame.getContentPane().add(jnum2);
		
		JLabel lblNumber_1 = new JLabel("Number 2: ");
		jnum2.add(lblNumber_1);
		
		tNum2 = new JTextField();
		jnum2.add(tNum2);
		tNum2.setColumns(10);
		
		JPanel jresult = new JPanel();
		frame.getContentPane().add(jresult);
		
		JLabel lblResults = new JLabel("Results: ");
		jresult.add(lblResults);
		
		tRes = new JTextField();
		jresult.add(tRes);
		tRes.setColumns(10);
		
		JPanel jops = new JPanel();
		frame.getContentPane().add(jops);
		
		JButton btnAdd = new JButton("Add");
		jops.add(btnAdd);
		
		JButton btnSub = new JButton("Sub");
		jops.add(btnSub);
		
		JButton btnMult = new JButton("Mult");
		jops.add(btnMult);
		
		JButton btnDiv = new JButton("Div");
		jops.add(btnDiv);
		
		JButton btnClear = new JButton("Clear");
		jops.add(btnClear);
		
		btnClear.addActionListener(new ActionListener()
	    {
	    	@Override
	         public void actionPerformed(ActionEvent e) 
	         {
	    		tNum1.setText("");
	    		tNum2.setText("");
	    		tRes.setText("");
	         }
	    });
		
		btnAdd.addActionListener(new ActionListener()
	    {
	    	@Override
	         public void actionPerformed(ActionEvent e) 
	         {
	    		double num1, num2, res;
	    		num1 = Double.parseDouble(tNum1.getText());
	    		num2 = Double.parseDouble(tNum2.getText());
	    		res = num1 + num2;
	    		String temp = String.valueOf(res);
	    		tRes.setText(temp);
	         }
	    });
		
		btnSub.addActionListener(new ActionListener()
	    {
	    	@Override
	         public void actionPerformed(ActionEvent e) 
	         {
	    		double num1, num2, res;
	    		num1 = Double.parseDouble(tNum1.getText());
	    		num2 = Double.parseDouble(tNum2.getText());
	    		res = num1 - num2;
	    		String temp = String.valueOf(res);
	    		tRes.setText(temp);
	         }
	    });
		
		btnMult.addActionListener(new ActionListener()
	    {
	    	@Override
	         public void actionPerformed(ActionEvent e) 
	         {
	    		double num1, num2, res;
	    		num1 = Double.parseDouble(tNum1.getText());
	    		num2 = Double.parseDouble(tNum2.getText());
	    		res = num1 * num2;
	    		String temp = String.valueOf(res);
	    		tRes.setText(temp);
	         }
	    });
		
		btnDiv.addActionListener(new ActionListener()
	    {
	    	@Override
	         public void actionPerformed(ActionEvent e) 
	         {
	    		double num1, num2, res;
	    		num1 = Double.parseDouble(tNum1.getText());
	    		num2 = Double.parseDouble(tNum2.getText());
	    		String temp;
	    		if(num2 != 0)
	    		{
	    			res = num1 / num2;
	    			temp = String.valueOf(res);
	    		}
	    		else
	    			temp = "Error, divide by zero";
	    		tRes.setText(temp);
	         }
	    });
	}
}
