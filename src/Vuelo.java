

import java.util.Calendar;

class Vuelo implements Comparable<Vuelo>{
	static final int SIN_EMPEZAR = 0;
	static final int EN_PROGRESO = 1;
	static final int FINALIZADO  = 2;
	
	Calendar fecha;
	private String origen;
	private String destino;
	int progreso= SIN_EMPEZAR; //Progreso en el marco de los pasos del browser
	
	public Vuelo(Calendar fecha, String origen, String destino){
		this.origen		= origen;
		this.destino	= destino;
		this.fecha		= fecha;
		this.progreso	= SIN_EMPEZAR;
	}
	
	public Calendar getFecha() {
		return fecha;
	}
	public String getOrigen() {
		return origen;
	}
	public String getDestino() {
		return destino;
	}
	public int getProgreso() {
		return progreso;
	}
	public boolean isSinEmpezar() {
		return (this.progreso == SIN_EMPEZAR);
	}
	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public void setProgreso(int progreso) {
		this.progreso = progreso;
	}
		
	@Override
	public int compareTo(Vuelo v) {
		/*int resultado = 0;
		if(this.origen.equals(v.getOrigen())){
			if(this.destino.equals(v.getDestino())){
				if(this.fecha.equals(v.getFecha())){
					resultado=0;
				}else resultado= this.fecha.compareTo(v.getFecha());
			}else resultado= this.destino.compareTo(v.getDestino());
		}else resultado= this.origen.compareTo(v.getOrigen());
		
		return resultado;*/
		//TODO Orden Vuelos
		return 1; //Asi lo dejamos sin orden, el orden es segun se van 'add'...
	}
	@Override
	public String toString() {
		return  origen + "  -->  " + destino + "  ::  " + fecha.getTime();
	}
	@Override
	public boolean equals(Object o) {
		Vuelo v= (Vuelo)o;
		return this.fecha.equals(v.getFecha()) && this.origen.equals(v.getOrigen())  && this.destino.equals(v.getDestino()) ;
	}
	
}



//PRUEBA ORDENACION DE LA CLASE Vuelo
/*
	Calendar c= Calendar.getInstance();
	c.set(2009, 6-1, 15);
	Calendar c2= Calendar.getInstance();
	c2.set(2009, 4-1, 1);
	Vuelo v1 = new Vuelo(c2, "Valladolid", "Londres");
	Vuelo v2 = new Vuelo(c, "Pucela", "Londres");
	Vuelo v3 = new Vuelo(c, "A", "Londres");
	Vuelo v4 = new Vuelo(c, "Pucela", "Dinamearca");
	Vuelo v5 = new Vuelo(c, "Pucela", "Dinamearca");
	TreeSet<Vuelo> tsv = new TreeSet<Vuelo>();
	tsv.add(v1);
	tsv.add(v2);
	tsv.add(v3);
	tsv.add(v4);
	System.out.println("Son iguales:" + v4.equals((Vuelo)v5));
	
	for (Vuelo vuelo : tsv) {
		System.out.println(vuelo.toString());
	}
*/




