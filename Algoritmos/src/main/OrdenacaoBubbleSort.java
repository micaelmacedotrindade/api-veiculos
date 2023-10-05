package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OrdenacaoBubbleSort {

	public static void main(String[] args) {
		
		List<Integer> vetordeSordenado = new ArrayList<Integer>(Arrays.asList(5,3,2,4,7,1,0,6));
		List<Integer> vetordeOrdenado = new ArrayList<Integer>(Arrays.asList(5,3,2,4,7,1,0,6));
		
		int iteracoes = vetordeSordenado.size() - 1;
		
		while (iteracoes > 0) {
			for (int i = 0; i < iteracoes; i++) {
				Integer n1 = vetordeOrdenado.get(i);
				Integer n2 = vetordeOrdenado.get(i+1);
				if(n1 > n2) {
					Collections.swap(vetordeOrdenado, i, i+1);
				}
				if(i == iteracoes-1 && vetordeOrdenado.get(i) < vetordeOrdenado.get(i+1)) {
					iteracoes --;
					System.out.println(iteracoes);
				}
			}
		};
		
		System.out.println("Vetor antes da ordenação Bubble Sort: " + vetordeSordenado);
		System.out.println("Vetor DEPOIS da ordenação Bubble Sort: " + vetordeOrdenado);
	}

}
