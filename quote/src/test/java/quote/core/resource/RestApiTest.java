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

import quote.main.Application;
import quote.resource.entity.Quote;

@RunWith(CamelSpringBootRunner.class)
@MockEndpoints
@SpringBootTest(classes = Application.class)

public class RestApiTest {
	@Autowired
	private ProducerTemplate template;

	@EndpointInject(uri="mock:direct:processResult")
	private MockEndpoint result;

	@Test
	public void routeProcessGetQuoteRequest() throws Exception {
		result.expectedMessageCount(1);
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("name", "abc");

		Quote quote = (Quote) template.sendBodyAndHeaders("direct:getQuote", ExchangePattern.InOut, "", headers);

		Assert.assertEquals(quote.getMessage(), "Hello abc");
		Assert.assertNotNull(quote.getTime());
		Assert.assertFalse(quote.getTime().isEmpty());
		result.assertIsSatisfied();
	}
}
