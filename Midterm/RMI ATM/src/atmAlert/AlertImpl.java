/*
	Programmer: Josh Long
	Assignment: Midterm
	Date: 3/6/16
*/

package atmAlert;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AlertImpl extends UnicastRemoteObject implements Alert
{
    public AlertImpl() throws RemoteException, NullPointerException
    {
		super();
	}

	@Override
    public void alert() throws RemoteException, NullPointerException
    {
    	System.out.println ("Intruder Suspected");
    }
}