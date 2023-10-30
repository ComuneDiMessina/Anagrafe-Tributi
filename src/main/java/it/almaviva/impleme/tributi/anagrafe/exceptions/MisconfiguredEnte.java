package it.almaviva.impleme.tributi.anagrafe.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class MisconfiguredEnte extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    public MisconfiguredEnte (String paramKey){
        super("The parameter " + paramKey + "is not configured");
    }
    
    
}
