package com.Servlets;
public class apptitudeTests {
	public static void main(String args[]) {
		int i=1234,r = 0;
		int j=i;
		while(i > 0)
		{
			r=i%10;
			i=i/10;
		//	r=r*10;
			System.out.println(r);
		}
		
	}
	
}
