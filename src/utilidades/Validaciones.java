package utilidades;

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
	
	
	
	
}
