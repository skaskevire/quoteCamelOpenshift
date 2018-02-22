package quote;

import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class Controller {

	@RequestMapping(path = "/getQuote", method = RequestMethod.GET)
	public String getQuote() {
		return new Date().toString();
	}

}