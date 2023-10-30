package it.almaviva.impleme.tributi.anagrafe;

import it.almaviva.eai.ljsa.sdk.core.bootstrap.EnableLjsa;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableLjsa
@EnableScheduling
@EnableAspectJAutoProxy
@SpringBootApplication
public class TributesApplication 
{
	  public static void main(final String[] args) {
		    SpringApplication.run(TributesApplication.class, args);
		  }
}
