package it.almaviva.impleme.tributi.anagrafe.factory;

import it.almaviva.impleme.tributi.anagrafe.entities.ParametersMap;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateFactory {

    public RestTemplate getAuthenticated(ParametersMap params) {
       

        RestTemplate rest = new RestTemplate();

        rest.getInterceptors().add(new BasicAuthenticationInterceptor(params.getUserRest(), params.getPwdRest()));
        return rest;

    }


}
