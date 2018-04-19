package quote.resource;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.epam.quote.GetQuoteResponse;

import quote.core.aggregator.QuoteSoapAggregationStrategy;

@Component
public class MainSoapEndpoint extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("cxf:/quote?serviceClass=com.epam.quote.Quote").
		to("direct:getQuoteSoap");
		from("direct:getQuoteSoap")
			.multicast()
			.to("seda:nameVendor1", "seda:dateVendor1");
		from("seda:nameVendor1")
			.to("bean:nameVendorBeanS?method=append(${body[0].message.name})")
			.to("seda:quoteAggregatorS");
		from("seda:dateVendor1")
			.to("bean:dateVendorBeanS?method=append")
			.to("seda:quoteAggregatorS");
		from("seda:quoteAggregatorS")
			.aggregate(constant(true), new QuoteSoapAggregationStrategy())
			.completionPredicate(
					p -> p.getOut().getBody() != null && 
					((GetQuoteResponse) p.getOut().getBody()).getMessage().getMessage() != null	&& 
					((GetQuoteResponse) p.getOut().getBody()).getMessage().getTime() != null)
			.to("mock:endS");	
	}
}
