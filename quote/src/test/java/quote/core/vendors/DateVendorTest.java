package quote.core.vendors;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.junit.Assert;
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
import quote.resource.QuoteResponseBusinessModel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class DateVendorTest {
	@Mock
	Exchange exchange;
	@Mock
	Message message;
	
	@InjectMocks
	DateVendor dateVendor;
	
	@Before
	public void init()
	{
		dateVendor = new DateVendor();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testQuoteWithDateCreated() {
		QuoteResponseBusinessModel qrbm = new QuoteResponseBusinessModel("REST", null, "message");
		Mockito.when(exchange.getOut()).thenReturn(message);
		Mockito.when(exchange.getIn()).thenReturn(message);
		Mockito.when(message.getBody(QuoteResponseBusinessModel.class)).thenReturn(qrbm);

		dateVendor.append(exchange);

		Assert.assertNotNull(qrbm.getTime());
		Assert.assertFalse(qrbm.getTime().isEmpty());
		Mockito.verify(message).setBody(Mockito.any(QuoteResponseBusinessModel.class));
	}
}
