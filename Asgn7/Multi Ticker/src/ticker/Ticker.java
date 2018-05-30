/*
	Programmer: Josh Long
	Assignment: 4c Multi Ticker
	Date: 2/5/16
 */

package ticker;

import java.awt.*;
import java.util.concurrent.*;

import javax.swing.*;

public class Ticker extends JPanel implements Runnable
{
	private Boolean moving;
	private String symbol;
	private int symbolWidth;
	private int x = 0;
	private int y = 10;
	
	public Ticker(String s)
	{
		symbol = s;
		moving = false;
		Thread thread = new Thread(this);
		thread.start();
	}
	
	
	public void setMoving(Boolean newMoving)
	{
		moving = newMoving;
	}
	
	public Boolean isMoving()
	{
		return moving;
	}
	
	public void run()
	{
		while(true)
		{
			if (moving)
		    {       
				///Update stock Ticker’s draw co-ordinates.
				if(x + 1 < 300)
					x++;
				if(x + 1 == 300)
				{
					x = 0;
					if(y + 15 < 75)
						y += 15;
					else
						y = 10;
				}
		        ///Call Ticker’s repaint method.
				super.repaint();
		    }
		///Sleep for a fixed time.
		try 
		{
			TimeUnit.MILLISECONDS.sleep(5);
		} catch (InterruptedException e) 
		{

			e.printStackTrace();
		}
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
