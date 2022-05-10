package utilidades;

public class Tabla {
	
	private String marca = "";
	private String modelo = "";
	private String talla = "";
	private String color = "";
	private String freno = "";
	private String material = "";
	private boolean susp_del = false;
	private boolean susp_tras = false;
	private int stock = 0;
	private double pvp = 0;
	private int longitud = 10;
	
	public Tabla (String marca, String modelo, String talla, String color, String freno, String material, String susp_del, String susp_tras, int stock, double pvp) {
		
		this.marca = marca;
		this.modelo = modelo;
		this.talla = talla;
		this.color = color;
		this.freno = freno;
		this.material = material;
		this.susp_del = (susp_del.toLowerCase().trim().charAt(0) == 's');
		this.susp_tras = (susp_tras.toLowerCase().trim().charAt(0) == 's');
		this.pvp = pvp;
		
	}

	public String getMarca() {
		return marca;
	}

	public String getModelo() {
		return modelo;
	}

	public String getTalla() {
		return talla;
	}

	public String getColor() {
		return color;
	}

	public String getFreno() {
		return freno;
	}

	public String getMaterial() {
		return material;
	}

	public boolean isSusp_del() {
		return susp_del;
	}

	public boolean isSusp_tras() {
		return susp_tras;
	}

	public int getStock() {
		return stock;
	}

	public double getPvp() {
		return pvp;
	}
	
	public int getLongitud() {
		return longitud;
	}
	

}
