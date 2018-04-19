package quote.core.aggregator;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import quote.resource.QuoteResponseBusinessModel;

public class AggregationStrategyTest {
	@Mock
	Exchange oldExchange;
	@Mock
	Exchange newExchange;
	@Mock
	Message oldMessage;
	@Mock
	Message newMessage;

	QuoteAggregationStrategy aggregator;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		aggregator = new QuoteAggregationStrategy();

		Mockito.when(oldExchange.getIn()).thenReturn(oldMessage);
		Mockito.when(oldExchange.getOut()).thenReturn(oldMessage);
		Mockito.when(newExchange.getIn()).thenReturn(newMessage);
		Mockito.when(newExchange.getOut()).thenReturn(newMessage);
	}

	@Test
	public void messageAppended() {
		Mockito.doReturn(new QuoteResponseBusinessModel("REST", "date", null)).when(newMessage).getBody();
		Mockito.doReturn(new QuoteResponseBusinessModel("REST",null, "message")).when(oldMessage).getBody();
		Exchange result = aggregator.aggregate(oldExchange, newExchange);

		Assert.assertEquals("message", ((QuoteResponseBusinessModel) result.getOut().getBody()).getMessage());
	}

	@Test
	public void dateAppended() {
		Mockito.doReturn(new QuoteResponseBusinessModel("REST", null, "message")).when(newMessage).getBody();
		Mockito.doReturn(new QuoteResponseBusinessModel("REST", "date", null)).when(oldMessage).getBody();
		Exchange result = aggregator.aggregate(oldExchange, newExchange);

		Assert.assertEquals("date", ((QuoteResponseBusinessModel) result.getOut().getBody()).getTime());
	}
}
