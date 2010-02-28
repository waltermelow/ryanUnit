import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.NoInitialContextException;

import org.w3c.dom.html.HTMLElement;

import sun.rmi.runtime.NewThreadAction;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.RefreshHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlCheckBoxInput;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlRadioButtonInput;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.gargoylesoftware.htmlunit.javascript.host.html.HTMLInputElement;
import com.gargoylesoftware.htmlunit.javascript.host.html.HTMLSelectElement;
import com.gargoylesoftware.htmlunit.javascript.host.html.HTMLTableElement;

public class CheckYahooMail {
	
	String origen;
	String destino;
	String dia;
	String mesAno;
	

/*
	public static String lanzarCheckThreaded(String origen, String destino, String dia, String mesAno) throws Exception {
		final String o= origen;
		final String d= destino;
		final String di= dia;
		final String ma= mesAno;
		
		final String retorno= "";
		Thread t1= new Thread() {
			public void run() {
				CheckYahooMail cym= null;
				String precio= "";
				try {
					cym = new CheckYahooMail(o, d, di, ma);
					precio= cym.getPrecio();
				} catch (ImporteNoEncontradoException e) {
					precio= "Importe no encontrado";
				} catch (NoVueloException e) {
					precio= "NO_VUELO";
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
				}
				UtilsIO.stringToFile("C:\\precios.txt", o +" to "+ma+"-"+di+"\t->\t" + precio +"\r\n", true);
			}
		};
		t1.start();
		
		return retorno;
	}
*/	
	
	public CheckYahooMail(String origen, String destino, String dia, String mesAno) throws Exception {
		this.origen= origen;
		this.destino =destino;
		this.dia= dia;
		this.mesAno= mesAno;
	}
	
	public String getPrecio(/*String origen, String destino, String dia, String mesAno*/) throws Exception {
		
		// Create and initialize WebClient object
		WebClient webClient = new WebClient(BrowserVersion.FIREFOX_3);
		webClient.setJavaScriptEnabled(true);
		
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		
	    webClient.setThrowExceptionOnScriptError(false);
	    webClient.setRefreshHandler(new RefreshHandler() {
			public void handleRefresh(Page page, URL url, int arg) throws IOException {
				System.out.println("handleRefresh");
			}

	    });
	    
	    // visit Yahoo Mail login page and get the Form object
	    //HtmlPage page = (HtmlPage) webClient.getPage("https://login.yahoo.com/config/login_verify2?.intl=us&.src=ym");
	    HtmlPage page = (HtmlPage) webClient.getPage("http://www.bookryanair.com/SkySales/FRSearch.aspx?culture=es-es&lc=es-es");
	    System.out.println("CODIFICACION: "+ page.getPageEncoding());	    
	    /* POR PAGE
	     */
	    //((HtmlSelect) page.getElementById("AvailabilitySearchInputFRSearchView_DropDownListMarketOrigin1")).getOptionByValue("VLL").setSelected(true); 
	    //((HtmlSelect) page.getElementById("AvailabilitySearchInputFRSearchView_DropDownListMarketDestination1")).getOptionByValue("BGY").setSelected(true);

	    /*
	    System.out.println("\n\n\n\n\n");
	    System.out.println(page.executeJavaScript("" +
	    		"var aeropuertoAcomprobar= 'VLL';var total='';" +
	    		"for(var clave in Stations){" +
	    		"	/*if(clave == aeropuertoAcomprobar)*/ /*total+= Stations[clave].name + '<|>' + Stations[clave].code + '<|>' + Stations[clave].mkts + '\\n';" +
	    		"} total;" ).getJavaScriptResult()  );
	    System.out.println("\n\n\n\n\n");
	    */
	    
	    page= setRadioCheck(page, "AvailabilitySearchInputFRSearchView_OneWay", true);
	    
	    setComboValue(page, "AvailabilitySearchInputFRSearchView_DropDownListMarketOrigin1", origen);
	    setComboValue(page, "AvailabilitySearchInputFRSearchView_DropDownListMarketDestination1", destino);

	    setComboValue(page, "AvailabilitySearchInputFRSearchView_DropDownListMarketDay1", dia);
	    System.out.println("mesAno= "+mesAno);
	    setComboValue(page, "AvailabilitySearchInputFRSearchView_DropDownListMarketMonth1", mesAno);
	    //setComboValue(page, "AvailabilitySearchInputFRSearchView_DropDownListMarketDay2", "08");
	    //setComboValue(page, "AvailabilitySearchInputFRSearchView_DropDownListMarketMonth2", "2010-04");

	    
	    setCheckboxCheck(page, "AvailabilitySearchInputFRSearchView_DropDownListSearchBy", false);
	    
	    //Print
	    HtmlForm form = (HtmlForm) page.getElementById("SkySales");
	    System.out.println(((HtmlSelect) form.getElementById("AvailabilitySearchInputFRSearchView_DropDownListMarketOrigin1")).asText());
	    System.out.println(((HtmlSelect) form.getElementById("AvailabilitySearchInputFRSearchView_DropDownListMarketDestination1")).asText());
	    System.out.println(((HtmlSubmitInput) form.getElementById("AvailabilitySearchInputFRSearchView_ButtonSubmit")).asText());
	    
	    //Submit
	    page= (HtmlPage) ((HtmlSubmitInput) page.getElementById("AvailabilitySearchInputFRSearchView_ButtonSubmit")).click();
	    
	    
	    /* POR JAVASCRIPT
	    page.executeJavaScript("document.getElementById('AvailabilitySearchInputFRSearchView_DropDownListMarketOrigin1').value= 'VLL';" +
	    						"document.getElementById('AvailabilitySearchInputFRSearchView_DropDownListMarketDestination1').value= 'BGY';" +
	    						"document.getElementById('AvailabilitySearchInputFRSearchView_ButtonSubmit').click();"
	    						);
	     */
	     
	    
	    /*
	    HtmlForm form = (HtmlForm) page.getElementById("SkySales");
	    System.out.println(((HtmlSelect) form.getElementById("AvailabilitySearchInputFRSearchView_DropDownListMarketOrigin1")).asText());
	    System.out.println(((HtmlSubmitInput) form.getElementById("AvailabilitySearchInputFRSearchView_ButtonSubmit")).asText());
	    //((HtmlSelect) form.getElementById("AvailabilitySearchInputFRSearchView_DropDownListMarketOrigin1")).setValueAttribute("VLL");
	    //((HtmlSelect) form.getElementById("AvailabilitySearchInputFRSearchView_DropDownListMarketDestination1")).setValueAttribute("BGY");
	    page = (HtmlPage) form.getInputByName("AvailabilitySearchInputFRSearchView_ButtonSubmit").click();
	    */
	    /*
	    HtmlForm form = page.getFormByName("login_form");
	    
	    // Enter login and passwd
	    form.getInputByName("login").setValueAttribute("@@@@");
	    form.getInputByName("passwd").setValueAttribute("@@@@");

	    // Click "Sign In" button/link
	    page = (HtmlPage) form.getInputByValue("Sign In").click();
	    // Click "Inbox" link
	    HtmlAnchor anchor = (HtmlAnchor) page.getHtmlElementById("WelcomeInboxFolderLink");
	    page = (HtmlPage) anchor.click();

	    // Get the table object containing the mails
	    HtmlTable dataTable = (HtmlTable) page.getHtmlElementById("datatable");

	    // Go through each row and count the row with class=msgnew
	    int newMessageCount = 0;
	    
	    List<HtmlTableRow> rows = (List<HtmlTableRow>) dataTable.getHtmlElementsByTagName("tr");
	    for (HtmlTableRow row: rows) {
	    	if (row.getAttribute("class").equals("msgnew")) {
	    		newMessageCount++;
	    	}
	    }	    
	    // Print the newMessageCount to screen
	    System.out.println("newMessageCount = " + newMessageCount);
	    System.out.println(dataTable.asXml());
 */

	    //System.out.println("\n" + page.asText());
	    
	    String precio= "";
	    try {
	    	precio= getPrecioFromHtml(page.asText());
		} catch (ImporteNoEncontradoException e) {
			System.out.println( e );
		} catch (NoVueloException e) {
			System.out.println( e );
		}
		System.out.println(precio);
	    
	    Calendar cal = Calendar.getInstance();
	    String ruta= "C:\\@resRyan\\paginaHTML_"+cal.get(Calendar.HOUR_OF_DAY)+"."+cal.get(Calendar.MINUTE)+"."+cal.get(Calendar.SECOND)+".html";
	    UtilsIO.stringToFile(ruta+".txt", page.asText(), false);
	    File f= new File(ruta);
	    page.save(f);
	    
	    try {
	    	precio= getPrecioFromHtml(page.asText());
		} catch (ImporteNoEncontradoException e) {
			System.out.println( e );
			throw new ImporteNoEncontradoException(e.getNumeroImportes());
		} catch (NoVueloException e) {
			System.out.println( e );
			throw new NoVueloException();
		}
		System.out.println(precio);
		return precio;
	    
	    //new LeerArchivoServidor("file://" + ruta);

	}

	private static void setComboValue(HtmlPage pagina, String combo, String value) {
		((HtmlSelect) pagina.getElementById(combo)).getOptionByValue(value).setSelected(true);
	}
	
	private static void setCheckboxCheck(HtmlPage pagina, String checkbox, boolean value) {
		((HtmlCheckBoxInput) pagina.getElementById(checkbox)).setChecked(value);
	}
	
	private static HtmlPage setRadioCheck(HtmlPage pagina, String radio, boolean value) {
		return (HtmlPage) ((HtmlRadioButtonInput) pagina.getElementById(radio)).setChecked(value);
	}
	
	
	private static String getPrecioFromHtml(String html) throws ImporteNoEncontradoException, NoVueloException
	{
		
		Pattern patVuelo= Pattern.compile("(No hay vuelos|there are no available flights)");
		Matcher mVuelo= patVuelo.matcher(html.replaceAll("[\r\n]", "")); //Le quitamos los retornos de carro, para que realice bien la busqueda
		if(mVuelo.find()){
			throw new NoVueloException();
		}
		
		
		//Pattern pat= Pattern.compile("Importe total del vuelo.*[0-9]*(,|\\.)[0-9]*(| )(EUR|L|€|GBP|SEK|NOK|LTL|LVL|PLN|CZK|HUF|DKK)");
		//Precio total: 	50,38	EUR
		Pattern pat= Pattern.compile("Precio total:.*[0-9]*(,|\\.)[0-9]*.*(EUR|L|€|GBP|SEK|NOK|LTL|LVL|PLN|CZK|HUF|DKK)");
		//Matcher m= pat.matcher(html.replaceAll("[\r\n]", "")); //Le quitamos los retornos de carro, para que realice bien la busqueda
		Matcher m= pat.matcher(html);
		
		String precio="";
		//Buscamos cadenas coincidentes con la RegEx
		int contador= 0;
		while (m.find()){
			String primerRes = m.group();
			//System.out.println("Primer bucle: "+ primerRes);
			//Si hay siguiente patron encontrado
			contador++;
			//Pattern pat2= Pattern.compile("[0-9]*(,|\\.)[0-9]*(| )(EUR|L|€|GBP|SEK|NOK|LTL|LVL|PLN|CZK|HUF|DKK)");
			Pattern pat2= Pattern.compile("[0-9]*(,|\\.)[0-9]*");
			Matcher m2= pat2.matcher(primerRes);
			while (m2.find()) {
				contador++;
				precio= m2.group();
				//System.out.println("Segund bucle (precio): "+ precio);
				//TODO db (pero bien...) aquí
				//Date Dfecha = fecha.getTime();
				//DB.setDatosVuelo(Origen, Destino, precio, fecha );
				
			}
		}
		System.out.println("");
		
		//Informamos de errores
		/*
		if(contador==0){
			//System.out.println("ERROR: no se ha encontrado importe alguno en la página.");
			//ERROR_NO_IMPORTE_ENCONTRADO;
		}else if(contador!=1){
			//System.out.println("ERROR: se han encontrado más importes ("+m.groupCount()+") de los esperados en la página.");
			//ERROR_NO_IMPORTE_ENCONTRADO;
		}else{
			//System.out.println("Precio ENCONTRADO: "+ precio);
		}*/
		if(contador != 2){
			throw new ImporteNoEncontradoException(contador);
		}
		
		return precio;
		
	}

	
}



class ImporteNoEncontradoException extends Exception{
	
	private int numeroImportes;
	
	public ImporteNoEncontradoException(int numeroImportes){
		super();
		this.numeroImportes= numeroImportes;
	}
	
	public int getNumeroImportes() {
		return numeroImportes;
	}

	public String toString(){
		if(numeroImportes == 0){
			return "No se ha podido encontrar el importe en la página.";
		}else{
			return "Se han encontrado importes de mas en la página.";
		}
	}
}

class NoVueloException extends Exception{
		
	public NoVueloException(){
		super();
	}
	
	public String toString(){
		return "No se ha encontrado vuelo.";
	}
}

