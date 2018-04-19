package quote.core.resource;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.EndpointInject;
import org.apache.camel.ExchangePattern;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.apache.camel.test.spring.MockEndpoints;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.epam.quote.GetQuoteRequest;
import com.epam.quote.GetQuoteRequestMessage;
import com.epam.quote.GetQuoteResponse;

import quote.main.Application;
import quote.resource.entity.Quote;

@RunWith(CamelSpringBootRunner.class)
@MockEndpoints
@SpringBootTest(classes = Application.class)

public class RouteConfigTest {
	@Autowired
	private ProducerTemplate template;

	@EndpointInject(uri="mock:endS")
	private MockEndpoint resultS;
	
	@EndpointInject(uri="mock:endR")
	private MockEndpoint resultR;

	@Test
	public void routeProcessRestGetQuoteRequest() throws Exception {
		resultR.expectedMessageCount(1);
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("name", "abc");

		Quote quote = (Quote) template.sendBodyAndHeaders("direct:getQuoteRest", ExchangePattern.InOut, null, headers);

		Assert.assertEquals(quote.getMessage(), "Hello abc");
		Assert.assertNotNull(quote.getTime());
		Assert.assertFalse(quote.getTime().isEmpty());
		resultR.assertIsSatisfied();
	}
	
	@Test
	public void routeProcessSoapGetQuoteRequest() throws Exception {
		resultS.expectedMessageCount(1);
		GetQuoteRequest gqr = new GetQuoteRequest();
		GetQuoteRequestMessage gqrm = new GetQuoteRequestMessage();
		gqr.setMessage(gqrm);
		gqrm.setName("abc");

		GetQuoteResponse quote = (GetQuoteResponse) template.sendBodyAndHeaders("direct:getQuoteSoap", ExchangePattern.InOut, gqr, null);

		Assert.assertEquals(quote.getMessage().getMessage(), "Hello abc");
		Assert.assertNotNull(quote.getMessage().getTime());
		Assert.assertFalse(quote.getMessage().getTime().isEmpty());
		resultS.assertIsSatisfied();
	}
}
