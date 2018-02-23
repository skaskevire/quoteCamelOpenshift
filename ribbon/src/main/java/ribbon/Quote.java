package ribbon;

public class Quote {

	private String date;
	private String port;

    public Quote() {
    }

	public Quote(String date, String port) {
		super();
		this.date = date;
		this.port = port;
	}
	
	public String getDate() {
		return  date;
	}

	public String getPort() {
		return  port;
	}
}
