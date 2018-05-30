/*
	Programmer: Josh Long
	Assignment: Midterm
	Date: 3/6/16
*/

package server;

import java.rmi.*;

public interface Account extends Remote
{
	public double getBalance() throws RemoteException;
	
	public void deposit(double amount) throws RemoteException;
	
	public void withdraw (double amount) throws RemoteException;
}
