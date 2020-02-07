package LOGICO;

import java.util.ArrayList;

public class Tabla {
	String nombre;
	ArrayList<Atributo> Columna;
	
	public Tabla(String nombre) {
		super();
		this.nombre = nombre;
		Columna = new ArrayList<Atributo>();
	}

}
