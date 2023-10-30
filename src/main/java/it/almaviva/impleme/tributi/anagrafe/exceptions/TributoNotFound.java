package it.almaviva.impleme.tributi.anagrafe.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TributoNotFound extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    public TributoNotFound (String id, String  ente) {
        super(String.format("Nessuno Tributo trovata per l'id '%s' ed ente '%s'", id, ente));
    }

    public TributoNotFound (String message) {
        super(String.format(message));
    }

}
