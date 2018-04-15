package quote.core.aggregator;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import quote.resource.entity.Quote;

public class QuoteAggregationStrategy implements AggregationStrategy {
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		if (oldExchange == null) {
			return newExchange;
		}

		Quote quoteToMerge = (Quote) newExchange.getIn().getBody();
		Quote result = (Quote) oldExchange.getIn().getBody();
		if (result != null) {
			if (result.getMessage() != null) {
				quoteToMerge.setMessage(result.getMessage());
			}
			if (result.getTime() != null) {
				quoteToMerge.setTime(result.getTime());
			}
		} else {
			oldExchange.getOut().setBody(quoteToMerge);
		}

		return oldExchange;
	}

}