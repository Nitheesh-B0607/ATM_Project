package com.bank;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.io.IOException;
import java.security.DrbgParameters.NextBytes;
import java.text.DecimalFormat;
import java.util.Scanner;

public class OptionMenu {
	Scanner menuInput = new Scanner(System.in);
	//each # represents a number 0-9.
	DecimalFormat moneyFormat = new DecimalFormat("'₹' ######0.00#"); 
	HashMap<Integer, Account> data = new HashMap<Integer, Account>();
	
	public void getLogin() throws IOException
	{
			boolean end = false;
			int customerNumber = 0;
			int pinNumber= 0;
			while(!end)
			{
				try
				{
					System.out.println("\nEnter your Account Number: ");
					customerNumber = menuInput.nextInt();
					System.out.println("\nEnter your PIN Number: ");
					pinNumber = menuInput.nextInt();
					Iterator it = data.entrySet().iterator();
					while(it.hasNext())
					{
						Map.Entry pair =  (Map.Entry) it.next();
						Account acc = (Account) pair.getValue();
						if (data.containsKey(customerNumber) && pinNumber == acc.getPinNumber())
						{
							getAccountType(acc);
							end = true;
							break;
						}
					}
					if(!end)
					{
						System.out.println("\nIncorrect Customer Name or PIN Number.");
					}
					
				}catch(InputMismatchException ex)
				{
					System.out.println("\nInvali character(s). Only Numbers are allowed.");
					menuInput.next();
				}
			}
}
	
	public void getAccountType(Account acc)
	{
		boolean end = false;
		while(!end)
		{
			try
			{
				System.out.println("\nSelect the Account you want to access: ");
				System.out.println("Type 1 - Checkings Account.\nType 2 - Savings Account.\nType 3 - Exit.\nChoice: ");
				int selection = menuInput.nextInt();
				
				switch(selection)
				{
				case 1: 
					getChecking(acc);
					break;
				
				case 2:
					getSaving(acc);
					break;
					
				case 3:
					end = true;
					default:
						System.out.println("\nInvalid Choice");
				}
			}catch(InputMismatchException e)
			{
				System.out.println("\nInvalid Choice.");
				menuInput.next();
			}
		}
	}
	
	public void getChecking(Account acc)
	{
		boolean end = false;
		while(!end)
		{
			try
			{
				System.out.println("\nChecking Account: ");
				System.out.println("Type 1 - View Balance.");
				System.out.println("Type 2 - Withdraw Funds.");
				System.out.println("Type 3 - Deposit Funds.");
				System.out.println("Type 4 - Transfer Funds.");
				System.out.println("Type 5 - Exit.");
				System.out.println("Choice: ");
				
				int selection = menuInput.nextInt();
				switch(selection)
				{
				case 1:
					System.out.println("\nChecking Account Balance: "+moneyFormat.format(acc.getCheckingBalance()));
					break;
				case 2: 
					acc.getCheckingWithdrawInput();
					break;
				case 3: 
					acc.getCheckingDepositInput();
					break;
				case 4:
					acc.getTransferInput("Checkings");
					break;
				case 5:
					end = true;
					
				default : 
						System.out.println("\nInvalid Choice.");
				}
			}catch(InputMismatchException ex)
			{
				System.out.println("\nInvalid Choice.");
				menuInput.next();
			}
		}
	}
	
	
	public void getSaving(Account acc)
	{
		boolean end = false;
		while(!end)
		{

			try
			{
				System.out.println("\nSavings Account: ");
				System.out.println("Type 1 - View Balance.");
				System.out.println("Type 2 - Withdraw Funds.");
				System.out.println("Type 3 - Deposit Funds.");
				System.out.println("Type 4 - Transfer Funds.");
				System.out.println("Type 5 - Exit.");
				System.out.println("Choice: ");
				
				int selection = menuInput.nextInt();
				switch(selection)
				{
				case 1:
					System.out.println("\nSavings Account Balance: "+moneyFormat.format(acc.getSavingBalance()));
					break;
				case 2: 
					acc.getSavingWithdrawInput();
					break;
				case 3: 
					acc.getSavingDepositInput();
					break;
				case 4:
					acc.getTransferInput("Savings");
					break;
				case 5:
					end = true;
					default : 
						System.out.println("\nInvalid Choice.");
				}
			
			}catch(InputMismatchException ex)
			{
				System.out.println("\nInvalid Choice.");
				menuInput.next();
			}
		}
	}
	
	public void createAccount() throws IOException
	{
		int cst_no = 0;
		boolean end = false;
		while(!end)
		{
			try
			{
				System.out.println("\nEnter your Customer Number: ");
				cst_no = menuInput.nextInt();
				Iterator it = data.entrySet().iterator();
				while(it.hasNext())
				{
					Map.Entry pair = (Map.Entry) it.next();
					if (!data.containsKey(cst_no))
					{
						end= true;
					}
					
				}
				if(!end)
				{
					System.out.println("This Customer number already registered.");
				}
			}
			catch(InputMismatchException ex)
			{
				System.out.println("\nInvalid Input");
				menuInput.next();
			}
		}
		System.out.println("\nEnter PIN to be regitered");
		int pin = menuInput.nextInt();
		data.put(cst_no, new Account(cst_no,pin));
		System.out.println("\nYour Accout is successfully created. ");
		System.out.println("\n..............................Redircting to Login..............................");
		getLogin();
	}
	
	public void mainMenu() throws IOException
	{
		//Key and Value Pair, one entry object. 
		data.put(952141, new Account(952141, 191904, 1000, 5000));
		data.put(123, new Account(123, 123, 20000, 50000));
		boolean end = false;
		while(!end)
		{
			try
			{
				System.out.println("\nType 1 - Login");
				System.out.println("Type 2 - Create Account");
				System.out.println("Choice: ");
				int choice = menuInput.nextInt();
				switch(choice)
				{
				case 1:
					getLogin();
					end = true;
					break;
				case 2:
					createAccount();
					end = true;
					break;
				default: 
					System.out.println("Invalid Choice.");
				}
			}catch(InputMismatchException ex)
			{
				System.out.println("Invalid Input.");
				menuInput.next();
			}
		}
		System.out.println("\nThank you for using this ATM.\n");
		menuInput.close();
		System.exit(0);
		
	}
	
	
	
	
	
	
	
	
}
