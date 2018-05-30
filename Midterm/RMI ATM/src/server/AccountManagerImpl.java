/*
	Programmer: Josh Long
	Assignment: Midterm
	Date: 3/6/16
*/

package server;

import java.io.*;
import java.rmi.*;
import java.rmi.server.*;
import java.util.*;
import atmAlert.*;

public class AccountManagerImpl extends UnicastRemoteObject implements AccountManager
{
	private static final long serialVersionUID = 1L;
	Hashtable<Integer, AccountImpl> accounts = new Hashtable<Integer, AccountImpl>();
	Hashtable<Integer, Boolean> isActive = new Hashtable<Integer, Boolean>();
	
	public AccountManagerImpl() throws IOException
	{
		super();
		BufferedReader data = null;
		try {
			data = new BufferedReader(new FileReader("database.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String line = null;
		try {
			line = data.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while(line != null)
		{
			String[] temp;
		    String delimeter = " ";
		    temp = line.split(delimeter);
		    int ID = Integer.parseInt(temp[0]);
		    double amount = Double.parseDouble(temp[1]);
		    
		    accounts.put(ID, new AccountImpl(amount));
		    isActive.put(ID, true);
				
			try {
				line = data.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		data.close();
	}
	
	public Account findAccount(int accountNumber, Alert check) throws RemoteException
	{
		AccountImpl ret = new AccountImpl(0);
		if(isActive.get(accountNumber) == true)
			ret = (AccountImpl)accounts.get(accountNumber);
		else
			check.alert();
		
		return ret;
	}
	
	public void addAccount(int accountNumber, Alert check) throws RemoteException
	{
		try
		{
			if(isActive.get(accountNumber) == false)
			{
				accounts.put(accountNumber, new AccountImpl(0));
				isActive.put(accountNumber, true);
			}
			else
			{
				try
				{
					check.alert();
					isActive.put(accountNumber, false);
				}
				catch(NullPointerException e)
				{
					System.out.println("INTRUDER ALERT! ACCOUNT FROZEN\n");
				}
			}
		}
		catch(NullPointerException e)
		{
			accounts.put(accountNumber, new AccountImpl(0));
			isActive.put(accountNumber, true);
		}
		PrintWriter out = null;
		try {
			out = new PrintWriter("database.txt");
			Set<Integer> e = accounts.keySet();
			Iterator<Integer> itr = e.iterator();
			while(itr.hasNext())
			{
				int key = itr.next();
				out.println(key + " " + accounts.get(key).getBalance());
			}
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void removeAccount(int accountNumber) throws RemoteException
	{
		if(isActive.get(accountNumber) == true)
			isActive.replace(accountNumber, false);
	}
	public void deposit(int a, double d, AccountManagerCharges c, Alert check) throws RemoteException
	{
		double temp = c.getCharge();
		AccountImpl aD = (AccountImpl)findAccount(a, check);
		aD.deposit(d - temp);
		accounts.replace(a, aD);
		
		PrintWriter out = null;
		try {
			out = new PrintWriter("database.txt");
			Set<Integer> e = accounts.keySet();
			Iterator<Integer> itr = e.iterator();
			while(itr.hasNext())
			{
				int key = itr.next();
				out.println(key + " " + accounts.get(key).getBalance());
			}
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void withdraw(int a, double w, AccountManagerCharges c, Alert check) throws RemoteException
	{
		double temp = c.getCharge();
		AccountImpl aW = (AccountImpl)findAccount(a, check);
		aW.withdraw(w + temp);
		accounts.replace(a, aW);
		
		PrintWriter out = null;
		try {
			out = new PrintWriter("database.txt");
			Set<Integer> e = accounts.keySet();
			Iterator<Integer> itr = e.iterator();
			while(itr.hasNext())
			{
				int key = itr.next();
				out.println(key + " " + accounts.get(key).getBalance());
			}
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
