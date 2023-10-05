package main;

public class Votos {

	public static void main(String[] args) {
		System.out.println("Precentual de votos válidos:  " + CalculaVotos.percentualVotosValidos() +"%");
		System.out.println("Precentual de votos brancos: " + CalculaVotos.percentualVotosBrancos() +"%");
		System.out.println("Precentual de votos nulos: " + CalculaVotos.percentualVotosNulos() +"%");
	}

}
