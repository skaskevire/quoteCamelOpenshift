package quote;

import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class Controller {
	private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

	private String applicationId;
	@Value("${server.port}")
	private String applicationPort;

	@PostConstruct
	public void doInit() {
		applicationId = UUID.randomUUID().toString();

	}

	@RequestMapping(path = "/getQuote", method = RequestMethod.GET)
	public Quote getQuote() {
		LOGGER.info("requestType=GET path=getQuote port=" + applicationPort + " applicationId=" + applicationId);
		return new Quote(new Date().toString(), applicationPort, applicationId);
	}

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String home() {
		return "Hi!";
	}

}