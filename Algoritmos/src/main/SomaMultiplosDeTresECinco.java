package main;

import java.util.Scanner;

public class SomaMultiplosDeTresECinco {

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		System.out.println("-------------------------------------------");
		System.out.println("--------Soma dos múltiplos de 3 e 5--------");
		System.out.println("Informe um número e tecle Enter: ");
		int n = s.nextInt();
		int soma = 0;

			for (int i = 1; i < n; i++) {
				soma += ehMutiploDeTresOuDeCinco(i);
			}

		System.out.println("O valor da soma dos múltiplos de 3 ou 5 é: " + soma);
	}

	private static int ehMutiploDeTresOuDeCinco(int n) {
		if(n % 3 == 0 || n % 5 == 0) {
			return n;
		}
		return 0;
	}

}
