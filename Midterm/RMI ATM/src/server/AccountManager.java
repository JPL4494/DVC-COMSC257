/*
	Programmer: Josh Long
	Assignment: Midterm
	Date: 3/6/16
*/

package server;

import java.rmi.*;
import atmAlert.*;

public interface AccountManager extends Remote
{
	public Account findAccount(int accountNumber, Alert check) throws RemoteException;
	public void addAccount(int accountNumber, Alert check) throws RemoteException;
	public void removeAccount(int accountNumber) throws RemoteException;
	public void deposit(int a, double d, AccountManagerCharges c, Alert check) throws RemoteException;
	public void withdraw(int a, double w, AccountManagerCharges c, Alert check) throws RemoteException;
}
