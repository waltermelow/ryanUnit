

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.TreeSet;

public class UtilsIO {
	
	/******************************************************************/
	/**
	 * Lee el fichero plano de fechas y lo traduce y devuelve un --> Conjunto ordenado de fechas (Calendar)<br><br>
	 * Formato del fichero: Cada linea esta compuesta a) ORIGEN|DESTINO|FECHA
	 *												  b) ORIGEN|DESTINO|FECHA_DESDE|FECHA_HASTA
	 * <br><br>
	 * Formato fechas: yyyy-MM-dd Ej: 2009-02-28
	 * 
	 */
	public static TreeSet<Vuelo> leerFicheroDatosConsultaVuelos(String fichero) {
		File f = new File(fichero);
		TreeSet<Vuelo> tsVuelos = new TreeSet<Vuelo>();
		if (f.exists()) {
			try {
				BufferedReader br = new BufferedReader(new FileReader(f));
				String linea;
				while ((linea = br.readLine()) != null) {
					//Si es un comentario la omitimos
					if (!linea.startsWith("#") && !linea.isEmpty()) {
						//Ej: 2009-02-28
						String[] subLinea= linea.split("\\|");
						//Si es una sola fecha
						if(subLinea.length==3){
							tsVuelos.add( new Vuelo(UtilsFechas.getFecha(subLinea[2]), subLinea[0], subLinea[1]) );
						//Si es un RANGO de fechas
						}else if (subLinea.length==4){
							TreeSet<Calendar> fechasDesdeHasta = UtilsFechas.getFechasDesdeHasta(UtilsFechas.getFecha(subLinea[2]), UtilsFechas.getFecha(subLinea[3]));
							for (Calendar cal : fechasDesdeHasta) {
								tsVuelos.add( new Vuelo(cal, subLinea[0], subLinea[1]) );
							}
						}else System.out.println("ERROR leerFicheroDatosConsultaVuelos(): fechas mal formadas en el fichero");
					}
				}

				//Cerramos el fichero
				br.close();
				//System.out.println(tsFechas.toString());
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}else{
			System.out.println("El fichero de fechas no existe! (" + fichero + ")");
		}

		return tsVuelos;
	}
	
	/******************************************************************/
	/**
	 * Escribe la cadena de caracteres en el fichero y ruta que indiquemos
	 * 
	 */
	public static void stringToFile(String ruta, String texto, boolean escribirAlFinal) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(ruta, escribirAlFinal));
			out.write(texto);
			out.close();
		}
		catch (IOException e){
			System.out.println("Exception " + e);
		}
	}
	

}
