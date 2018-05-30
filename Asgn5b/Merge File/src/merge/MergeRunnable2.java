/*
	Programmer: Josh Long
	Assignment: 5b File Merger
	Date: 2/18/16
*/

package merge;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MergeRunnable2 implements Runnable
{
	String file1, file2, filem;
	Object obj1, obj2, obj12;
	
	public MergeRunnable2(String a, String b, String c, Object d, Object e, Object f)
	{
		file1 = a;
		file2 = b;
		filem = c;
		obj1 = d;
		obj2 = e;
		obj12 = f;
	}
	
	public void run()
	{
		synchronized (obj12) 
		{
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (obj1) 
			{
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (obj2) 
				{
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
		        	BufferedReader f1 = null;
		        	BufferedReader f2 = null;
		        	PrintWriter pw = null; 
		        	try {
						pw = new PrintWriter (new FileWriter(filem));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
		            try {
						f1 = new BufferedReader(new FileReader(file1));
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
		            try {
						f2 = new BufferedReader(new FileReader(file2));
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
		            
		            String line = null;
		            pw.write("");
		            while(f1 != null)
		            {
		            	try {
							line = f1.readLine();
						} catch (IOException e) {
							e.printStackTrace();
						}
		        		pw.append(line);
		            }
		            while(f2 != null)
		            {
		            	try {
							line = f2.readLine();
						} catch (IOException e) {
							e.printStackTrace();
						}
		        		pw.append(line);
		            }
		            pw.close();
				}
			}
		}
	}
}
