/*
	Programmer: Josh Long
	Assignment: Midterm
	Date: 3/6/16
*/

package atmAlert;

import java.rmi.*;

public interface Alert extends Remote
{
	public void alert() throws RemoteException, NullPointerException;
}
