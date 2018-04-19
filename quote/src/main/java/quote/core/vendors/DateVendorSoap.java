package quote.core.vendors;

import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Component;

import com.epam.quote.GetQuoteResponse;
import com.epam.quote.GetQuoteResponseMessage;

@Component(value = "dateVendorBeanS")
public class DateVendorSoap {

	@Produce(uri = "seda:quoteAggregatorS")
	private ProducerTemplate quoteAggregatorS;

	public void append(Exchange exchange) {
		GetQuoteResponse gqr = new GetQuoteResponse();
		GetQuoteResponseMessage gqrm = new GetQuoteResponseMessage();
		gqr.setMessage(gqrm);
		gqrm.setTime(new Date().toString());
		exchange.getOut().setBody(gqr);
		quoteAggregatorS.send(exchange);
	}
}