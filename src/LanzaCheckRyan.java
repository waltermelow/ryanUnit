import java.util.TreeSet;


public class LanzaCheckRyan {

	
	public static void main(String[] args) throws Exception {
		final int NC = 10;
		
		final String rutaActual= System.getProperty("user.dir")+"\\src\\";
		final String nomFichConsultas= "dataConsultas.txt";
		TreeSet<Vuelo> vuelos;
		Buffer bufferVuelos = new Buffer(new CircularQueue());
		
		System.out.println(rutaActual);
		System.out.println("-- Empieza lectura fichero --");
		vuelos = UtilsIO.leerFicheroDatosConsultaVuelos(rutaActual + nomFichConsultas);
		System.out.println("- - Tam:"+vuelos.size()+"- - ");
		for (Vuelo v : vuelos) {
			System.out.println(v.toString() + "  " + UtilsFechas.getFechadd(v.getFecha()) + " " + UtilsFechas.getFechayyyyMM(v.getFecha()));
			//lanzarCheckThreaded(v.getOrigen(), v.getDestino(), UtilsFechas.getFechadd(v.getFecha()), UtilsFechas.getFechayyyyMM(v.getFecha()));
			bufferVuelos.Put(v);
		}
		System.out.println("-- Termina lectura fichero --");
		
	    for (int i=0; i < NC; i++){
	    	new Consumer(bufferVuelos, i).start();
	    }
		
		
		/*
		Thread t1= new Thread() {
			public void run() {
				try {
					new checkYahooMail("VLL", "BGY", "03", "2010-04");
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		t1.start();
		Thread t2= new Thread() {
			public void run() {
				try {
					new checkYahooMail("VLL", "BGY", "04", "2010-04");
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		t2.start();*/
		
		/*
		lanzarCheckThreaded("VLL", "BGY", "03", "2010-04");
		lanzarCheckThreaded("VLL", "BGY", "04", "2010-04");
		lanzarCheckThreaded("VLL", "BGY", "05", "2010-04");
		*/
	}
	
	
	public static void lanzaCheck(Object objeto) {

		CheckYahooMail cym= null;
		String precio= "";
		Vuelo vuelo= (Vuelo) objeto;
		try {
			cym = new CheckYahooMail(vuelo.getOrigen(), vuelo.getDestino(), UtilsFechas.getFechadd(vuelo.getFecha()), UtilsFechas.getFechayyyyMM(vuelo.getFecha()));
			precio= cym.getPrecio();
		} catch (ImporteNoEncontradoException e) {
			precio= "Importe no encontrado";
		} catch (NoVueloException e) {
			precio= "NO_VUELO";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		UtilsIO.stringToFile("C:\\@resRyan\\precios.txt", vuelo.getOrigen() +" to "+ vuelo.getDestino() + " " + UtilsFechas.getFechayyyyMM(vuelo.getFecha())+"-"+UtilsFechas.getFechadd(vuelo.getFecha())+"\t->\t" + precio +"\r\n", true);
	    
	}

}
