/*
	Programmer: Josh Long
	Assignment: 5a Sync File Write
	Date: 2/18/16
*/

package synched;

import java.io.*;

public class CoopSyncRunnable implements Runnable
{
	String message;
	int count;
	String fileName;
	Object obj;
	
	
	public CoopSyncRunnable(String a, int b, String c, Object d)
	{
		message = a;
		count = b;
		fileName = c;
		obj = d;
	}
	
	public void run()
	{
		PrintWriter pw = null;
		try {
			pw = new PrintWriter (new FileWriter (fileName), true);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			Thread.sleep (100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for (int i=0; i<count; i++) 
		{
			synchronized (obj) 
			{
		        //A single write within the loop will be here
		        pw.println (message);
		        
		        try {
					Thread.sleep (100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		        obj.notify ( );
		        try {
					obj.wait ();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep (100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		synchronized (obj) 
		{
			obj.notify ( );
		}
		
		pw.close();
	}
}
