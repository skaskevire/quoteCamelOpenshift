package quote;

public class Quote {

	private String date;
	private String port;

	private String applicationId;

	public Quote(String date, String port, String applicationId) {
		super();
		this.date = date;
		this.port = port;
		this.applicationId = applicationId;
	}

	public String getPort() {
		return port;
	}
	
	public String getDate() {
		return  date;
	}
	
	public String getApplicationId() {
		return applicationId;
	}
}
