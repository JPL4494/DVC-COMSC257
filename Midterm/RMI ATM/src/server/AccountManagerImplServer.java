package server;

import java.io.IOException;

/*
Programmer: Josh Long
Assignment: Midterm
Date: 3/6/16
*/

import java.net.*;
import java.rmi.*;
import java.rmi.registry.*;

public class AccountManagerImplServer 
{
	public static void main(String[] args) throws IOException
	{
	    try
	    {
	      // Create IMPL object
	      AccountManagerImpl acc = new AccountManagerImpl();
	      // Start RMI registry
	      // RMI registry will run on port 1099
	      LocateRegistry.createRegistry(1099);
	      // Register with RMI registry the IMPL object under the name "ATM server"
	      // RMI registry now knows the object class name
	      // It also knows its stub class name
	      Naming.rebind("ATMserver", acc);
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
