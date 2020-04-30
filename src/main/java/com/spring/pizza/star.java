package com.spring.pizza;

public class star {
	
	public static void main(String[] args) {
		int i;
		int j;
		
		for (i=0; i<10; i++) {
			for(j=0; j<10-i; j++) {
				System.out.print(" *");
			}
			System.out.println("");
		}
	}
}
