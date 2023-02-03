package com.bank;

import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Account
{
	
	@Override
	public String toString() {
		return "Account [customerNumber=" + customerNumber + ", pinNumber=" + pinNumber + ", checkingBalance="
				+ checkingBalance + ", savingBalance=" + savingBalance + ", input=" + input + ", moneyFormat="
				+ moneyFormat + "]";
	}

	//Variables
	private int  customerNumber;
	private int pinNumber;
	private double checkingBalance = 0.0;
	private double savingBalance = 0.0;
	
	Scanner input = new Scanner(System.in);
	DecimalFormat moneyFormat = new DecimalFormat("'₹' ###,##0.00");
	public Account()
	{
		
	}
	public Account(int customerNumber, int pinNumber)
	{
		this.customerNumber = customerNumber;
		this.pinNumber = pinNumber;
	}
	public Account (int customerNumber, int pinNumber, double checkingBalance, double savingBalance)
	{
		this.customerNumber = customerNumber;
		this.pinNumber = pinNumber;
		this.checkingBalance = checkingBalance;
		this.savingBalance = savingBalance;
	}
	public int setCustomerNumber(int customerNumber)
	{
		this.customerNumber = customerNumber;
		return customerNumber; 
	}
	public int getCustomerNumber()
	{
		return customerNumber;
	}
	public int getPinNumber()
	{
		return pinNumber;
	}
	public void setPinNumber(int pinNumber) 
	{
		this.pinNumber = pinNumber;
	}
	public double getCheckingBalance() 
	{
		return checkingBalance;
	}
	public double getSavingBalance() 
	{
		return savingBalance;
	}
	
	public double calcCheckingWithdraw(double amount)
	{
		checkingBalance = (checkingBalance - amount);
		return checkingBalance;
	}
	
	public double calcSavingWithdraw(double amount)
	{
		savingBalance = (savingBalance - amount);
		return savingBalance; 
	}
	
	public double calcCheckingDeposit(double amount)
	{
		checkingBalance = (checkingBalance + amount);
		return checkingBalance;
	}
	
	public double calcSavingDeposit(double amount)
	{
		savingBalance = (savingBalance + amount);
		return savingBalance;
	}
	
	//Transferring money from checking acc to savings acc.
	public void calcCheckingTransfer(double amount)
	{
		checkingBalance = (checkingBalance - amount);
		savingBalance = (savingBalance + amount);
	}
	
	//Transferring money from savings acc to checking acc.
	public void calcSavingTransfer(double amount)
	{
		savingBalance = (savingBalance - amount);
		checkingBalance = (checkingBalance + amount);
	}
	//Withdraw
	public void getCheckingWithdrawInput()
	{
		boolean end = false;
		while(!end)
		{
			try
			{
				System.out.println("\nCurrent Checking Account Balance: "+moneyFormat.format(checkingBalance));
				System.out.println("\nAmount you want to withdraw from Checking Account: ");
				double amount = input.nextDouble();
				
				if ((checkingBalance - amount) >= 0 && (amount >= 0))
				{
					calcCheckingWithdraw(amount);
					System.out.println("\nCurrent Checking Account Balance: "+moneyFormat.format(checkingBalance));
					end = true;
				}
				else
				{
					System.out.println("Insufficient Balance.");
				}
			}catch(InputMismatchException ex)
			{
				System.out.println("Invalid Choice");
				input.next();
			}
		}
	}
		public void getSavingWithdrawInput()
		{
			boolean end = false;
			while(!end)
			{
				try
				{
					System.out.println("\nCurrent Saving Account Balance: "+moneyFormat.format(savingBalance));
					System.out.println("\nAmount you want to withdraw from Saving Account: ");
					double amount = input.nextDouble();
					
					if ((savingBalance - amount) >= 0 && (amount >= 0))
					{
						calcSavingWithdraw(amount);
						System.out.println("\nCurrent Saving Account Balance: "+moneyFormat.format(savingBalance));
						end = true;
					}
					else
					{
						System.out.println("Insufficient Balance.");
					}
				}catch(InputMismatchException ex)
				{
					System.out.println("Invalid Choice");
					input.next();
				}
				
			}
	}
	//End of withdraw
   
	//Deposit
		public void getCheckingDepositInput()
		{
			boolean end = false;
			while(!end)
			{
				try
				{
					System.out.println("\nCurrent Checking Account Balance: "+moneyFormat.format(checkingBalance));
					System.out.println("\nAmount you want to deposit to Checking Account: ");
					double amount = input.nextDouble();
					
					if (!(amount <= 0) || (checkingBalance + amount) > 0 && (amount > 0))
					{
						calcCheckingDeposit(amount);
						System.out.println("\nCurrent Checking Account Balance: "+moneyFormat.format(checkingBalance));
						end = true;
					}
					else
					{
						System.out.println("Cannot deposit negative amount.");
					}
				}catch(InputMismatchException ex)
				{
					System.out.println("Invalid Choice");
					input.next();
				}
			}
		}
		public void getSavingDepositInput()
		{
			boolean end = false;
			while(!end)
			{
				try
				{
					System.out.println("\nCurrent Saving Account Balance: "+moneyFormat.format(savingBalance));
					System.out.println("\nAmount you want to deposit to Saving Account: ");
					double amount = input.nextDouble();
					
					if (!(amount <=0) || (savingBalance + amount) > 0 && (amount > 0))
					{
						calcSavingDeposit(amount);
						System.out.println("\nCurrent Saving Account Balance: "+moneyFormat.format(savingBalance));
						end = true;
					}
					else
					{
						System.out.println("Cannot deposit negative amount.");
					}
				}catch(InputMismatchException ex)
				{
					System.out.println("Invalid Choice");
					input.next();
				}
				
			}
	}
		
		public void getTransferInput(String accType)
		{
			boolean end = false;
			while(!end)
			{
				try
				{
				if (accType.equals("Checkings"))
				{
					System.out.println("Select an account you wish to transfer funds to:  ");
					System.out.println("1. Savings\n2. Exit\n\nChoice : ");
					int choice = input.nextInt();
					switch (choice) 
					{
					case 1: System.out.println("\nCurrent Checking Account Balance: " + moneyFormat.format(checkingBalance));
							System.out.println("\nAmount you want to deposit in Savings Account: ");
							double amount = input.nextDouble();
							if(!(amount < 0) && (savingBalance + amount) >= 0 && (checkingBalance - amount)>=0 && amount >= 0)
							{
								calcCheckingTransfer(amount);
								System.out.println("\nCurrent Savings Account Balance: "+moneyFormat.format(savingBalance));
								System.out.println("\nCurrent Checking Account Balnce: "+moneyFormat.format(checkingBalance));
								end = true;
							}
							else 
							{
								System.out.println("Cannont be negative or 0 amount");
							}
							break;
					case 2:
						return;
						default:
							System.out.println("Invalid Choice.");
							break;
					}
					}
					else if (accType.equals("Savings"))
					{
						System.out.println("Select an account you wish to transfer funds to:  ");
						System.out.println("1. Checking\n2. Exit\n\nChoice : ");
						int choice = input.nextInt();
						switch (choice)
						{
						case 1: System.out.println("\nCurrent Savings Account Balance: " + moneyFormat.format(savingBalance));
						System.out.println("\nAmount you want to deposit in Checking Account: ");
						double amount = input.nextDouble();
						if(!(amount < 0) && (checkingBalance + amount)>0 && (savingBalance - amount)>0 && amount>=0)
						{
							calcSavingTransfer(amount);
							System.out.println("\nCurrent Checking Account Balnce: "+moneyFormat.format(checkingBalance));
							System.out.println("\nCurrent Savings Account Balance: "+moneyFormat.format(savingBalance));
							end = true;
						}
						else 
						{
							System.out.println("Cannont be negative or 0 amount");
						}
						break;
				case 2:
					return;
					default:
						System.out.println("Invalid Choice.");
						break;
				}
				}
				}catch (InputMismatchException e)
				{
					System.out.println("Invalid Choice.");
					input.next();
				}
			}
		}
	
		

	
	
	
	
	
	
	
	
	
	

}
