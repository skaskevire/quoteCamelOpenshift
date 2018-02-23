package quote;

import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class Controller {
	 private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);
	@Value("${server.port}")
	private String applicationPort;

	@RequestMapping(path = "/getQuote", method = RequestMethod.GET)
	public Quote getQuote() {
		LOGGER.info("requestType=GET path=getQuote port=" + applicationPort);
		return new Quote(new Date().toString(), applicationPort);
	}
	

	@RequestMapping(path = "/", method = RequestMethod.GET)
	  public String home() {
	    return "Hi!";
	  }

}