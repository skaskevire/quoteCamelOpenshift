package quote.core.vendors;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
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
import quote.resource.QuoteRequestBusinessModel;
import quote.resource.QuoteResponseBusinessModel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class NameVendorTest {
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
	public void testQuoteWithNameCreated()
	{
		QuoteRequestBusinessModel qrbm = new QuoteRequestBusinessModel();
		qrbm.setName("abc");
		qrbm.setProcessingType("REST");
		Mockito.when(exchange.getIn()).thenReturn(message);
		Mockito.when(exchange.getOut()).thenReturn(message);
		Mockito.when(message.getBody(QuoteRequestBusinessModel.class)).thenReturn(qrbm);
		
		nameVendor.append(exchange);

		Mockito.verify(message).setBody(Mockito.any(QuoteResponseBusinessModel.class));
	}
}
