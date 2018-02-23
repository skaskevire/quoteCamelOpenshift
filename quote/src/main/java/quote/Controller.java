package quote;

import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class Controller {
	
	@Value("${server.port}")
	private String applicationPort;

	@RequestMapping(path = "/getQuote", method = RequestMethod.GET)
	public Quote getQuote() {
		return new Quote(new Date().toString(), applicationPort);
	}
	

	@RequestMapping(path = "/", method = RequestMethod.GET)
	  public String home() {
	    return "Hi!";
	  }

}