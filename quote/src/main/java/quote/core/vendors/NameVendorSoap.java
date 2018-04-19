package quote.core.vendors;

import org.apache.camel.Exchange;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Component;

import com.epam.quote.GetQuoteResponse;
import com.epam.quote.GetQuoteResponseMessage;

@Component(value = "nameVendorBeanS")
public class NameVendorSoap {
	private static final String MSG_PATTERN = "Hello %s";
	@Produce(uri = "seda:quoteAggregatorS")
	private ProducerTemplate quoteAggregatorS;

	public void append(String name, Exchange exchange) {
		GetQuoteResponse gqr = new GetQuoteResponse();
		GetQuoteResponseMessage gqrm = new GetQuoteResponseMessage();
		gqr.setMessage(gqrm);
		gqrm.setMessage(String.format(MSG_PATTERN, name));
		exchange.getOut().setBody(gqr);
		quoteAggregatorS.send(exchange);
	}
}