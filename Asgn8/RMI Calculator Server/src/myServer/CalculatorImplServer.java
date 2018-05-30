/*
Programmer: Josh Long
Assignment: 6 RMI Calculator
Date:2/19/16
*/

package myServer;

import java.net.*;
import java.rmi.*;
import java.rmi.registry.*;

public class CalculatorImplServer 
{
	public static void main(String[] args) throws RemoteException
	{
	    try
	    {
	      // Create IMPL object
	      CalculatorImpl calc = new CalculatorImpl();
	      // Start RMI registry
	      // RMI registry will run on port 1099
	      LocateRegistry.createRegistry(1099);
	      // Register with RMI registry the IMPL object under the name "CalcServer"
	      // RMI registry now knows the object class name
	      // It also knows its stub class name
	      Naming.rebind("CalcServer", calc);
	      System.out.println("Server started");
	    }
	    catch (MalformedURLException ex)
	    {
	      ex.printStackTrace();
	    }
	    catch (RemoteException ex)
	    {
	      ex.printStackTrace();
	      throw ex;
	    }
	}
}
