package main;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class CalculaVotos {

	final static float totalEleitores = 1000;
	final static float validos = 800;
	final static float brancos = 150;
	final static float nulos = 50;
	
	public static float percentualVotosValidos() {
		return calculaPercentual(validos, totalEleitores);
	}
	
	public static float percentualVotosBrancos() {
		return calculaPercentual(brancos, totalEleitores);
	}
	
	public static float percentualVotosNulos() {
		return calculaPercentual(nulos, totalEleitores);
	}

	private static float calculaPercentual(float votos, float totalEleitores) {
		double percentual = (votos / totalEleitores) * 100;
		NumberFormat formatter = new DecimalFormat("#,##");
		return  Float.parseFloat(String.valueOf(formatter.format(percentual)));
	}
	
}
