

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TreeSet;

public class UtilsFechas {
	
	/******************************************************************/
	public static Calendar getFecha(String annoMes, String dia){
		return getFecha(annoMes+"-"+dia);
	}
	
	/******************************************************************/
	/** 
	 * Convierte Calendar fecha  a --> String tipo "E, dd/MM/yyyy" 
	 */
	public static String getFechaEDDMMYYYY(Calendar fecha){
		DateFormat df = new SimpleDateFormat("E, dd/MM/yyyy");
		return df.format(fecha.getTime());
		
	}
	
	/******************************************************************/
	/** 
	 * Convierte Calendar fecha  a --> String tipo "yyyy-MM-dd"
	 */	
	public static String getFecha(Calendar fecha){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(fecha.getTime());
		
	}
	
	/******************************************************************/
	/** 
	 * Convierte String fechas tipo "yyyy-MM-dd" a --> Calendar
	 */		
	public static Calendar getFecha(String annoMesDia){
		
		//http://www.java2s.com/Code/JavaAPI/java.text/DateFormatparseStringdateString.htm
		//http://www.exampledepot.com/egs/java.text/FormatDate.html?l=rel
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date d= null;
		try {
			d = df.parse(annoMesDia);
		} catch (ParseException e) {
			System.out.println(e.getMessage()); //e.printStackTrace();
		}
		Calendar c= Calendar.getInstance();
		c.setTime(d);
		return c;
		
		
	}
	/******************************************************************/
	public static TreeSet<Calendar> getFechasDesdeHasta(Calendar fDesde, Calendar fHasta){
		TreeSet<Calendar> ts= new TreeSet<Calendar>();
		Calendar fDesdeAux= (Calendar)fDesde.clone();
		
		//Incrementamos la fechaDesde hasta fechaHasta y las guardamos
		while(fDesdeAux.before(fHasta) || fDesdeAux.equals(fHasta)){
			ts.add((Calendar)fDesdeAux.clone());
			fDesdeAux.add(Calendar.DATE, 1);
		}
		return ts;
	}
	/******************************************************************/
	

}
