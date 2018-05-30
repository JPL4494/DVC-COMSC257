/*
	Programmer: Josh Long
	Assignment: 5b File Merger
	Date: 2/18/16
*/

package merge;

import java.io.*;

public class MergeRunnable implements Runnable
{
	String file1, file2, filem;
	Object obj1, obj2;
	
	public MergeRunnable(String a, String b, String c, Object d, Object e)
	{
		file1 = a;
		file2 = b;
		filem = c;
		obj1 = d;
		obj2 = e;
	}
	
	public void run()
	{
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
					f1 = new BufferedReader(new FileReader(file1));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
	            try {
					f2 = new BufferedReader(new FileReader(file2));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
	            try {
					pw = new PrintWriter (new FileWriter(filem));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
	            
	            String line = null;
	            try {
					line = f1.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
	            
	            pw.write("");
	            while(line != null)
	            {
	        		pw.append(line);
	        		try {
						line = f1.readLine();
					} catch (IOException e) {
						e.printStackTrace();
					}
	            }
	            
	            try {
					line = f2.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
	            while(line != null)
	            {
	        		pw.append(line);
	        		try {
						line = f2.readLine();
					} catch (IOException e) {
						e.printStackTrace();
					}
	            }
	            pw.close();
	        }
		}
	}
}
