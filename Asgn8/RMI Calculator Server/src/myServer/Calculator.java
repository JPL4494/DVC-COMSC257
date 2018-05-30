/*
Programmer: Josh Long
Assignment: 6 RMI Calculator
Date:2/19/16
*/

package myServer;

import java.rmi.*;

public interface Calculator extends Remote
{
	public static double add(double n1, double n2) throws RemoteException
	{
		return n1 + n2;
	}
	public static double sub(double n1, double n2) throws RemoteException
	{
		return n1 - n2;
	}
	public static double mult(double n1, double n2) throws RemoteException
	{
		return n1 * n2;
	}
	public static double div(double n1, double n2) throws RemoteException
	{
		return n1 / n2;
	}
}
