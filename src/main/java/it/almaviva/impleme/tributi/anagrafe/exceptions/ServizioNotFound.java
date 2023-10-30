package it.almaviva.impleme.tributi.anagrafe.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ServizioNotFound extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ServizioNotFound(Integer id, String  ente) {
        super(String.format("Nessuno Servizio trovata per l'id '%d' ed ente '%s'", id, ente));
    }


}
