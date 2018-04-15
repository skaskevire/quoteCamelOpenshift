package quote.core.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import quote.core.vendors.DateVendor;
import quote.core.vendors.NameVendor;

@Configuration
public class TestConfiguration
{
  @Bean
  public DateVendor dateVendor()
  {
      return new DateVendor();
  }
  
  @Bean
  public NameVendor nameVendor()
  {
      return new NameVendor();
  }
  
  @Bean
  public DateVendor dateVendorBean()
  {
      return new DateVendor();
  }
  
  @Bean
  public NameVendor nameVendorBean()
  {
      return new NameVendor();
  }
}