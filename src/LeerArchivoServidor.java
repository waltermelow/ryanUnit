// Uso de un objeto JEditorPane para mostrar el contenido de un archivo en un servidor Web.
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;



public class LeerArchivoServidor extends JFrame {

   private JTextField campoIntroducir;
   private JEditorPane areaContenido;
   
   
   // configurar GUI
   public LeerArchivoServidor()
   {
      super( "Navegador Web simple" );
      Container contenedor = getContentPane();
      // crear campoIntroducir y registrar su componente de escucha
      campoIntroducir = new JTextField( "Escriba aquí el URL del archivo" );
      campoIntroducir.addActionListener(
         new ActionListener() {
            // obtener el documento especificado por el usuario
            public void actionPerformed( ActionEvent evento )
            {
               obtenerLaPagina( evento.getActionCommand() );
            }
         } // fin de la clase interna

      ); // fin de la llamada a addActionListener
      contenedor.add( campoIntroducir, BorderLayout.NORTH );
      // crear areaContenido y registrar componente de escucha de evento HyperlinkEvent
      areaContenido = new JEditorPane();
      areaContenido.setEditable( false );
      areaContenido.addHyperlinkListener(
         new HyperlinkListener() {
            // si el usuario hizo clic en el hipervínculo, ir a la página especificada
            public void hyperlinkUpdate( HyperlinkEvent evento )
            {
               if ( evento.getEventType() == 
                    HyperlinkEvent.EventType.ACTIVATED )
                  obtenerLaPagina( evento.getURL().toString() );
            }
         } // fin de la clase interna
      ); // fin de la llamada a addHyperlinkListener

      contenedor.add( new JScrollPane( areaContenido ), BorderLayout.CENTER );
      setSize( 400, 300 );
      setVisible( true );

   } // fin del constructor de LeerArchivoServidor


   
   private void obtenerLaPagina( String ubicacion )
   {
      // cargar documento y mostrar ubicación
      try {
         areaContenido.setPage( ubicacion );
         campoIntroducir.setText( ubicacion );
      }
      catch ( IOException excepcionES ) {
         JOptionPane.showMessageDialog( this, "Error al recuperar el URL especificado", "URL incorrecto", JOptionPane.ERROR_MESSAGE );
      }

   }
    
   
   
   
   public LeerArchivoServidor(String url){
	   
	   super( "Navegador Web simple" );
	   Container contenedor = getContentPane();
	   // crear campoIntroducir y registrar su componente de escucha
	   campoIntroducir = new JTextField( "Escriba aquí el URL del archivo" );
	   campoIntroducir.addActionListener(
			   new ActionListener() {
				   // obtener el documento especificado por el usuario
				   public void actionPerformed( ActionEvent evento )
				   {
					   obtenerLaPagina( evento.getActionCommand() );
				   }
			   } // fin de la clase interna
			   
	   ); // fin de la llamada a addActionListener
	   contenedor.add( campoIntroducir, BorderLayout.NORTH );
	   // crear areaContenido y registrar componente de escucha de evento HyperlinkEvent
	   areaContenido = new JEditorPane();
	   areaContenido.setEditable( false );
	   areaContenido.addHyperlinkListener(
			   new HyperlinkListener() {
				   // si el usuario hizo clic en el hipervínculo, ir a la página especificada
				   public void hyperlinkUpdate( HyperlinkEvent evento )
				   {
					   if ( evento.getEventType() == 
						   HyperlinkEvent.EventType.ACTIVATED )
						   obtenerLaPagina( evento.getURL().toString() );
				   }
			   } // fin de la clase interna
	   ); // fin de la llamada a addHyperlinkListener
	   
	   contenedor.add( new JScrollPane( areaContenido ), 
			   BorderLayout.CENTER );
	   setSize( 400, 300 );
	   setVisible( true );
	   
	   obtenerLaPagina(url);
	   
   }
   

   public static void main( String args[] )
   {
      JFrame.setDefaultLookAndFeelDecorated(true);
      LeerArchivoServidor aplicacion = new LeerArchivoServidor("http://www.google.es");
      aplicacion.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
   }

}

