package quote.core.vendors;

import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Component;

import quote.resource.entity.Quote;

@Component(value = "dateVendorBean")
public class DateVendor {

	@Produce(uri = "seda:quoteAggregator")
	private ProducerTemplate quoteAggregator;

	public void append(String name, Exchange exchange) {
		exchange.getOut().setBody(new Quote(new Date().toString(), null));
		quoteAggregator.send(exchange);
	}

	public ProducerTemplate getQuoteAggregator() {
		return quoteAggregator;
	}

	public void setQuoteAggregator(ProducerTemplate quoteAggregator) {
		this.quoteAggregator = quoteAggregator;
	}
}