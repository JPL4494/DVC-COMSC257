/*
	Programmer: Josh Long
	Assignment: 5a Sync File Write
	Date: 2/18/16
*/

package synched;

import java.io.*;

public class CompSyncRunnable implements Runnable
{
	String message;
	int count;
	String fileName;
	Object obj;
	
	public CompSyncRunnable(String a, int b, String c, Object d)
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
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Thread.sleep (100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		synchronized (obj) 
		{
			//The whole write loop is here
			for (int i=0; i<count; i++) 
			{
				pw.println (message);     
				try {
					Thread.sleep (100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		pw.close();
	}
}
