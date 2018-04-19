package quote.resource;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Component;


@Component
public class QuoteResponseRouter implements Processor {
	@Produce(uri = "direct:soapResponseRoute")
	private ProducerTemplate soapRoute;
	@Produce(uri = "direct:restResponseRoute")
	private ProducerTemplate restRoute;

	@Override
	public void process(Exchange exchange) throws Exception {
		QuoteResponseBusinessModel qbm = exchange.getOut().getBody(QuoteResponseBusinessModel.class);
		switch (qbm.getProcessingType()) {
		case "REST":
			restRoute.send(exchange);
			break;
		case "SOAP":
			soapRoute.send(exchange);
			break;
		}
	}

}
