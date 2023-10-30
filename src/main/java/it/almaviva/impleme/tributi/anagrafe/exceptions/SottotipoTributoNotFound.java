package it.almaviva.impleme.tributi.anagrafe.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SottotipoTributoNotFound extends RuntimeException{
	
    /**
    *
    */
   private static final long serialVersionUID = 1L;
   
   public SottotipoTributoNotFound (String id) {
       super(String.format("Nessuno sottotipo del tributo trovato con id '%s' ", id));
   }

}
