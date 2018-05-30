/*
	Programmer: Josh Long
	Assignment: 4d Ticker Synch
	Date: 2/5/16
 */

package ticker;

import java.awt.*;
import java.util.concurrent.*;

import javax.swing.*;

public class Ticker extends JPanel implements Runnable
{
	private Boolean moving;
	private String symbol = "DVC 32.0";
	private int symbolWidth;
	private int x = 0;
	private int y = 10;
	
	public Ticker()
	{
		moving = false;
		Thread thread = new Thread(this);
		thread.start();
	}
	
	
	public synchronized void setMoving(boolean moving)
	{
	    this.moving = moving;
	    notify();
	}
	
	public Boolean isMoving()
	{
		return moving;
	}
	
	public void run()
	{
	    while(true)
	    {
	      myRun();
	      try
	      {
	          Thread.sleep(100);
	      }
	      catch (InterruptedException ex)
	      {
	      }
	    }
	 }
	
	public synchronized void myRun ()
	  {
	    if(!moving)
	    {
	      try
	      {
	          wait();
	      }
	      catch (InterruptedException ex)
	      {
	      }
	    }
	    else
	    {
	      if (x >= this.getWidth())
	      {
	          x = 0;
	          if(y + 15 < 250)
	        	  y += 15;
	          else
	        	  y = 10;
	      }
	      else
	          x += 10;
	 
	      this.repaint();
	    }
	 
	  }
	
	public void paintComponent (Graphics g)
	{
		//Call parent constructor to clear drawing surface.
		super.paintComponent (g);
		 
		//Determine the width of String symbol
		//You will need this width value in the run method
		//to determine when to set the x value back to 0
		FontMetrics fm = g.getFontMetrics();
		symbolWidth = fm.stringWidth(symbol);
		 
		//Draw the string starting at coordinates x, y.
		g.drawString (symbol, x, y);
	}
}
