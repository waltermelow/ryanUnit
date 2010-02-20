


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;

import org.w3c.dom.html.HTMLElement;

import com.gargoylesoftware.htmlunit.BrowserVersion;
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
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.gargoylesoftware.htmlunit.javascript.host.html.HTMLInputElement;
import com.gargoylesoftware.htmlunit.javascript.host.html.HTMLSelectElement;
import com.gargoylesoftware.htmlunit.javascript.host.html.HTMLTableElement;

public class checkYahooMail {

	public static void main(String[] args) throws Exception {

		// Create and initialize WebClient object
		WebClient webClient = new WebClient(BrowserVersion.FIREFOX_3);
		webClient.setJavaScriptEnabled(true);
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
	    
	    setComboValue(page, "AvailabilitySearchInputFRSearchView_DropDownListMarketOrigin1", "VLL");
	    setComboValue(page, "AvailabilitySearchInputFRSearchView_DropDownListMarketDestination1", "BGY");

	    setComboValue(page, "AvailabilitySearchInputFRSearchView_DropDownListMarketDay1", "01");
	    setComboValue(page, "AvailabilitySearchInputFRSearchView_DropDownListMarketMonth1", "2010-04");
	    setComboValue(page, "AvailabilitySearchInputFRSearchView_DropDownListMarketDay2", "08");
	    setComboValue(page, "AvailabilitySearchInputFRSearchView_DropDownListMarketMonth2", "2010-04");

	    
	    setCheckboxValue(page, "AvailabilitySearchInputFRSearchView_DropDownListSearchBy", true);
	    
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
 */

	    //System.out.println("\n" + page.asXml());
	    Calendar cal = Calendar.getInstance();
	    String ruta= "C:\\paginaHTML_"+cal.get(Calendar.HOUR_OF_DAY)+"."+cal.get(Calendar.MINUTE)+"."+cal.get(Calendar.SECOND)+".html";
	    File f= new File(ruta);
	    page.save(f);	    
	    new LeerArchivoServidor("file://" + ruta);
	    //System.out.println(dataTable.asXml());

	}

	private static void setComboValue(HtmlPage pagina, String combo, String value) {
		((HtmlSelect) pagina.getElementById(combo)).getOptionByValue(value).setSelected(true);
	}
	
	private static void setCheckboxValue(HtmlPage pagina, String checkbox, boolean value) {
		((HtmlCheckBoxInput) pagina.getElementById(checkbox)).setChecked(value);
	}
	
}