package quote.resource;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import quote.core.aggregator.QuoteAggregationStrategy;
import quote.resource.entity.Quote;

@Component
public class MainRestEndpoint extends RouteBuilder {
	@Override
	public void configure() {
		restConfiguration()
			.component("servlet")
			.contextPath("/")
			.bindingMode(RestBindingMode.json);
		//interceptSendToEndpoint("seda:quoteAggregator")
       // .to("mock:result");
		rest("/getQuote/")
			.get("/").to("direct:getQuote");
		
		from("direct:getQuote")
			.multicast()
			.to("seda:nameVendor", "seda:dateVendor");
		from("seda:nameVendor")
			.to("bean:nameVendorBean?method=append(${header.name})")
			.to("seda:quoteAggregator");
		from("seda:dateVendor")
			.to("bean:dateVendorBean?method=append")
			.to("seda:quoteAggregator");
		from("seda:quoteAggregator")
			.aggregate(constant(true), new QuoteAggregationStrategy())
			.completionPredicate(
				p -> p.getOut().getBody() != null && 
				((Quote) p.getOut().getBody()).getMessage() != null	&& 
				((Quote) p.getOut().getBody()).getTime() != null)
			.to("direct:processResult").id("direct:processResult").routeId("gc");
			from("direct:processResult").to("bean:resultProcessor?method=process")
			.endRest();
			

	}
}