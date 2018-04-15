package quote.core.vendors;

import org.apache.camel.Exchange;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Component;

import quote.resource.entity.Quote;

@Component(value = "nameVendorBean")
public class NameVendor {
	private static final String MSG_PATTERN = "Hello %s";
	@Produce(uri = "seda:quoteAggregator")
	private ProducerTemplate quoteAggregator;

	public void append(String name, Exchange exchange) {
		exchange.getOut().setBody(new Quote(null, String.format(MSG_PATTERN, name)));
		quoteAggregator.send(exchange);
	}

	public ProducerTemplate getQuoteAggregator() {
		return quoteAggregator;
	}

	public void setQuoteAggregator(ProducerTemplate quoteAggregator) {
		this.quoteAggregator = quoteAggregator;
	}
}