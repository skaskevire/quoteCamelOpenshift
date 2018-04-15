package quote.core.resource;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import quote.core.test.config.TestConfiguration;
import quote.resource.*;
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = TestConfiguration.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class RestApiTest
//extends CamelSpringTestSupport 
{
	//@Autowired
	//private AbstractApplicationContext applicationContext;
	//@Override
	//public RouteBuilder createRouteBuilder() throws Exception {
	//	return new MainRestEndpoint();
	//}
	
	@Test
	public void test() throws InterruptedException
	{
	/*	MockEndpoint result = getMockEndpoint("mock:result");
        result.expectedMessageCount(1);
        // START SNIPPET: e1
        result.expectedBodiesReceived(1); // expect the lowest quote
        // END SNIPPET: e1

        // START SNIPPET: e2
        Map<String, Object> headers = new HashMap<String, Object>();

        headers.put("name", "abc");
        template.sendBodyAndHeaders("direct:getQuote", "", headers);
        // END SNIPPET: e2
        
        result.assertIsSatisfied();*/
		
		
		
		
	//	Assert.assertEquals(context.getRestDefinitions().size(), 1);
		//RestDefinition rest = context.getRestDefinitions().get(0);

		//template.sendBody("localhost:9090/camel/getQuote?name=abc", "asdasdasd");
	}

	//@Override
//	protected AbstractApplicationContext createApplicationContext() {
//		// TODO Auto-generated method stub
//		return applicationContext;
	//}
}
