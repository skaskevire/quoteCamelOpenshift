package ribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;





@SpringBootApplication
@RestController
@RibbonClient(name = "quote", configuration = LoadBalancerConfiguration.class)
public class Application {
	

	  @LoadBalanced
	  @Bean
	  RestTemplate restTemplate(){
	    return new RestTemplate();
	  }

	  @Autowired
	  RestTemplate restTemplate;

	@RequestMapping(path = "/getQuote", method = RequestMethod.GET)
	public Quote getQuote() {
		Quote quote = this.restTemplate.getForObject("http://quote/getQuote", Quote.class);
		return quote;
	}
	


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}