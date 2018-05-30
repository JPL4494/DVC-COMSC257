/*
	Programmer: Josh Long
	Assignment: 5a Sync File Write
	Date: 2/18/16
*/

package synched;

import java.io.*;

public class NoSynchRunnable implements Runnable
{
	String message;
	int count;
	String fileName;
	
	public NoSynchRunnable(String a, int b, String c)
	{
		message = a;
		count = b;
		fileName = c;
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
		
		for (int i=0; i<count; i++) 
		{
			pw.println (message);
			try {
				Thread.sleep (100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		pw.close();
	}
}
