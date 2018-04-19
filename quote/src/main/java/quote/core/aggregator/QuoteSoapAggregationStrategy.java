package quote.core.aggregator;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import com.epam.quote.GetQuoteResponse;

public class QuoteSoapAggregationStrategy implements AggregationStrategy {
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		if (oldExchange == null) {
			return newExchange;
		}

		GetQuoteResponse quoteToMerge = (GetQuoteResponse) newExchange.getIn().getBody();
		GetQuoteResponse result = (GetQuoteResponse) oldExchange.getIn().getBody();
		if (result != null) {
			if (result.getMessage() != null) {
				quoteToMerge.getMessage().setMessage(result.getMessage().getMessage());
			}
			if (result.getMessage().getTime() != null) {
				quoteToMerge.getMessage().setTime(result.getMessage().getTime());
			}
		} else {
			oldExchange.getOut().setBody(quoteToMerge);
		}

		return oldExchange;
	}

}