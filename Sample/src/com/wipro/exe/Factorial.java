package com.wipro.exe;

import java.util.Scanner;

public class Factorial {

	public static int fact(int i)
	{
		int k = 1;
		int n = 0;
		for(;i>1;i--)
		{
			System.out.println(k++);
			n = i * fact(i-1);
			System.out.println("value of n is " + n);
		}
		return n;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		int j = sc.nextInt();
		
		System.out.println(fact(j));
		
	}

}
