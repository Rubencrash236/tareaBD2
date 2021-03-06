package LOGICO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.io.*;

import SQLCONNECTION.Conexion;
public class Main {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<Tabla> misTablas = new ArrayList<Tabla>();
		final String query = "Select TABLE_NAME FROM INFORMATION_SCHEMA.TABLES";
		final String query2 = "Select TABLE_NAME , COLUMN_NAME, DATA_TYPE, CHARACTER_MAXIMUM_LENGTH FROM INFORMATION_SCHEMA.COLUMNS";
		Connection connect;
		connect = Conexion.getConnection();
		PreparedStatement sql = connect.prepareStatement(query);
		PreparedStatement sql2 = connect.prepareStatement(query2);
		ResultSet rst = sql.executeQuery();
		ResultSet rsc = sql2.executeQuery();
		while(rst.next()) {
			misTablas.add(new Tabla(rst.getString(1)));
		}
		
		while(rsc.next()) {
			misTablas.get(find(rsc.getString(1),misTablas)).Columna.add(new Atributo(rsc.getString(2),rsc.getString(3),
					Integer.toString(rsc.getInt(4))));
		}
		
		FileWriter fichero = null;
		PrintWriter pw = null;
		
		try {
			fichero = new FileWriter("data.txt");
			pw = new PrintWriter(fichero);
			for (Tabla tabla : misTablas) {
				pw.println("CREATE TABLE " + tabla.nombre + "(");
				for (Atributo atributo : tabla.Columna) {
					if (atributo.size.equalsIgnoreCase("0")) {
						pw.println(atributo.nombre +" "+ atributo.tipo+",");
						
					}
					else {
						pw.println(atributo.nombre + " nvarchar(" + atributo.size + "),");
					}
				}
				pw.println(");\n\n");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (null != fichero)
					fichero.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}

		for(int i =0; i < misTablas.size();i++) {
			System.out.println(misTablas.get(i).nombre);
			for(int j =0; j < misTablas.get(i).Columna.size(); j ++) {
				System.out.println(misTablas.get(i).Columna.get(j).nombre + ","+ misTablas.get(i).Columna.get(j).tipo + "," + 
			misTablas.get(i).Columna.get(j).size);
			}
			System.out.println("\n\n");
		}
		

		
	}
	
	private static int find(String name, ArrayList<Tabla> tables) {
		int idx=-1;
		for(int i =0; i < tables.size(); i++) {
			if(tables.get(i).nombre.equalsIgnoreCase(name)) {
				idx = i;
				return idx;
			}
		}
		return idx;
	}

}
