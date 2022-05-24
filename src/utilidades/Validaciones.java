package utilidades;

import java.util.Scanner;

public final class Validaciones {
	
	private Validaciones() {}
	
	public static void validaRango(int num, int rInf, int rSup) throws Exception {
		
		if(num < rInf || num > rSup) {
			throw new Exception("Valores fuera del rango " + rInf + " - " + rSup + "\n");
		}
	}
	
	
	public static void validaSigno(int num, int signo) throws Exception {
		
		if(signo >= 0) {
			
			if(num != Math.abs(num)) {
				throw new Exception("Debe ser un valor POSITIVO\n");
			}
			
		} else {
			
			if(num == Math.abs(num)) {
				throw new Exception("Debe ser un valor NEGATIVO\n");
			}
		}
	}
	
	
	public static double validaNumero(String mensaje) {
		
		Scanner teclado = new Scanner(System.in);
		double salida = 0;
		
		while (true) {
			try {

				System.out.println(mensaje);
				salida = Double.parseDouble(teclado.nextLine());
				break;

			} catch (NumberFormatException e) {
				System.out.println("Debes introducir un valor numerico");

			} catch (Exception e) {
				System.out.println(e.getMessage());
			} 
		}
		return salida;
		
	}		
	
	public static boolean cancelar(String in) {
		if(in.isEmpty()) {
			System.out.println("Cancelado\n\n");
			return true;
		} else {
			return false;
		}
	}
	
}
