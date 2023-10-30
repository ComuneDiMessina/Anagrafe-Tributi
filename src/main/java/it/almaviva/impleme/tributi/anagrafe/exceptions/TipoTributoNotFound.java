package it.almaviva.impleme.tributi.anagrafe.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TipoTributoNotFound extends RuntimeException{

    /**
    *
    */
   private static final long serialVersionUID = 1L;
   
   public TipoTributoNotFound (String id) {
       super(String.format("Nessuno tipo del tributo trovato con id '%s' ", id));
   }

	
}
