package quote.resource;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.http.common.HttpMessage;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class MainRestEndpoint extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		restConfiguration()
		.component("servlet")
		.contextPath("/")
		.bindingMode(RestBindingMode.json);
	
	rest("/getQuote/")
		.get("/").to("direct:convert");
	from("direct:convert").process(new Processor() {

		@Override
		public void process(Exchange exchange) throws Exception {
			String name =  (String) exchange.getIn().getBody(HttpMessage.class).getHeader("name");
			QuoteRequestBusinessModel gqr = new QuoteRequestBusinessModel();
			gqr.setName(name);
			gqr.setProcessingType("REST");
			exchange.getOut().setBody(gqr);
		}
	}).to("direct:getQuote");
		
	}
}
