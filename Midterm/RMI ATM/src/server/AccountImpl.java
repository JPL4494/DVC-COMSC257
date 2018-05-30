/*
	Programmer: Josh Long
	Assignment: Midterm
	Date: 3/6/16
*/

package server;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class AccountImpl extends UnicastRemoteObject implements Account
{
	private static final long serialVersionUID = 1L;
	double balance = 0;
	
	public AccountImpl(double a) throws RemoteException
	{
		super();
		balance = a;
	}
	
	public double getBalance() throws RemoteException
	{
		return balance;
	}
	
	public void deposit(double amount) throws RemoteException
	{
		balance += amount;
	}
	public void withdraw (double amount) throws RemoteException
	{
		balance -= amount;
	}
}
