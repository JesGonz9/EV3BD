package utilidades;

import java.util.Scanner;

public class Validaciones {
	
	public void validaRango(int num, int rInf, int rSup) throws Exception {
		
		if(num < rInf || num > rSup) {
			throw new Exception("Valores fuera del rango " + rInf + " - " + rSup + "\n");
		}
	}
	
	public void validaSigno(int num, int signo) throws Exception {
		
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
	
	public static String validaNumero(String mensaje) {
		
		Scanner teclado = new Scanner(System.in);
		
		while(true) {
			try {
				System.out.print("\n" + mensaje );
				String num = teclado.nextLine();
				if(num.contains(".")) {
					Double.parseDouble(num);
					
				} else {
					Integer.parseInt(num);
				}
				
				return num;
				
			} catch(NumberFormatException e) {
				System.out.println("Introduce un valor numerico\n");
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
		}

	}
	
	
	
	
}
