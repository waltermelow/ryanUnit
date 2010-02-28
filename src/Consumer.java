
public class Consumer extends Thread
{
  protected Buffer b;
  private int id;

  public Consumer(Buffer b, int id)
  {
    this.b = b;
    this.id = id;
  }

  public void run()
  {
    Object value;
        
    while (true)
      {
        System.out.println("      -> Consumer #" + id + ": [ get ...");
        value = b.Get();
        System.out.println("      -> Consumer #" + id + ": " + value.toString() + " ]");
        consume(value);
      }
  }

  private void consume(Object value)
  {/*
	  try{
		  Vuelo vuelo= (Vuelo)value;
		  CheckYahooMail.lanzarCheckThreaded(vuelo.getOrigen(), vuelo.getDestino(), UtilsFechas.getFechadd(vuelo.getFecha()), UtilsFechas.getFechayyyyMM(vuelo.getFecha()));
		  //sleep((int)(Math.random() * 100));
	  }catch (InterruptedException e){
		  e.printStackTrace();
	  }catch (Exception e) {
		  e.printStackTrace();
	  }*/
  }
}
