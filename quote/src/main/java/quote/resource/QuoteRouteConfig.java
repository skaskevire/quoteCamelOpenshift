package quote.resource;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import quote.core.aggregator.QuoteAggregationStrategy;

@Component
public class QuoteRouteConfig extends RouteBuilder {
	@Override
	public void configure() {
		from("direct:getQuote")
			.to("seda:nameVendor");
		from("seda:nameVendor")
			.enrich("bean:nameVendorBean?method=append", new QuoteAggregationStrategy())
			.to("seda:dateVendor");
		from("seda:dateVendor")
			.enrich("bean:dateVendorBean?method=append", new QuoteAggregationStrategy())
			.to("direct:route");		
		
		from("direct:route").choice()
		.when(simple("${body.processingType} == 'REST'"))
			.to("direct:restResponseRoute")
		.otherwise()
			.to("direct:soapResponseRoute");
		
		from("direct:soapResponseRoute").to("bean:quoteSoapResponseConverter").to("mock:endS");
		from("direct:restResponseRoute").to("bean:quoteRestResponseConverter").to("mock:endR");		
	}
}