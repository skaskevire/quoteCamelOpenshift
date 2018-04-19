package quote.core.vendors;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.ProducerTemplate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import quote.core.test.config.TestConfiguration;
import quote.resource.entity.Quote;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class NameVendorTest {
	@Mock
	ProducerTemplate quoteAggregator;
	@Mock
	Exchange exchange;
	@Mock
	Message message;
	
	@InjectMocks
	NameVendor nameVendor;
	
	@Before
	public void init()
	{
		nameVendor = new NameVendor();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testQuoteWithDateCreated()
	{
		Mockito.when(exchange.getOut()).thenReturn(message);

		nameVendor.append("nameString", exchange);

		Mockito.verify(quoteAggregator).send(exchange);
		Mockito.verify(message).setBody(Mockito.any(Quote.class));
	}
}
