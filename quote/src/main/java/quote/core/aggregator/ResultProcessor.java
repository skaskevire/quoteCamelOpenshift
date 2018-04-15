package quote.core.aggregator;

import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

import quote.resource.entity.Quote;

@Component
public class ResultProcessor {

	public Quote process(Exchange exchange) {
		return (Quote) exchange.getOut().getBody();
	}
}