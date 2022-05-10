package bdApp;

import operaciones.*;




public class Principal {

	public static void main(String[] args) {
		
		Listado ls = new Listado();
		//ls.consultaListado();
		
		Altas al = new Altas(/*aqui podria pasar la tabla*/);
		//al.alta("Orbea", "Orca", "M", "Menta", "Zapata", "Carbono", "no", "no", 5, 4999);
		Modificacion md = new Modificacion();
		md.modGrp();
		
		
		
		
		
	}
}
