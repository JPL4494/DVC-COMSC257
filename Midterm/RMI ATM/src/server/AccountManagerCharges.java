/*
	Programmer: Josh Long
	Assignment: Midterm
	Date: 3/6/16
*/

package server;

import java.io.*;

public class AccountManagerCharges implements Serializable
{
	private static final long serialVersionUID = 1L;
	String bankName;
	double charges;
	
	public AccountManagerCharges(String a, double b)
	{
		bankName = a;
		charges = b;
	}
	
	public String getBank()
	{
		return bankName;
	}
	public double getCharge()
	{
		return charges;
	}
}
