/*
Programmer: Josh Long
Assignment: 6 RMI Calculator
Date:2/19/16
*/

package myServer;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.*;

public class CalculatorImpl extends UnicastRemoteObject implements Calculator 
{
	public CalculatorImpl() throws RemoteException
	{
		// export this object to RMI
	    // RMI will reserve a port number for this object
	    // Skeleton of this object will run on that port
	    super();
	}
	
	public double add(double n1, double n2) throws RemoteException
	{
		return n1 + n2;
	}
	public double sub(double n1, double n2) throws RemoteException
	{
		return n1 - n2;
	}
	public double mult(double n1, double n2) throws RemoteException
	{
		return n1 * n2;
	}
	public double div(double n1, double n2) throws RemoteException
	{
		return n1 / n2;
	}
}
