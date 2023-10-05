package main;

import java.util.Scanner;

public class Fatorial {

	public static void main(String[] args) {
		
		 Scanner s = new Scanner(System.in);
		 System.out.println("Informe um número inteiro para calcular so seu fatorial e tecle Enter: ");
		 int n = s.nextInt();
		 int fatorial = 1;
		 
		 if(n != 0 && n != 1) {
			 for (int i = 2; i <= n; i++) {
				fatorial *= i;
			}
		 }
		 
		 System.out.println("O valor de " + n + "! é: "  + fatorial);
	}

}
