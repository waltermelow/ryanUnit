
public class lanzarCheckThreaded {

	public static void main(String[] args) {
		final String o= "";
		final String d= "";
		final String di= "";
		final String ma= "";
		Thread t3= new Thread() {
			public void run() {
				try {
					new checkYahooMail(o, d, di, ma);
				} catch (Exception e) {
					System.out.println(e.getStackTrace());
				}
			}
		};
		t3.start();
	}

}
