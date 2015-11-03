package com.wipro.exe;

import java.util.Scanner;

public class Factorial {

	public static int fact(int i)
	{
		int n = 1;
		int fact = 1;
		for(;n<=i;n++)
		{
			fact = n * fact;
		}
		return fact;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		int j = sc.nextInt();
		
		System.out.println(fact(j));
		
	}

}
