package it.almaviva.impleme.tributi.anagrafe.controllers;

import it.almaviva.impleme.tributi.anagrafe.services.ICsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
public class PagopaSyncController implements IPagopaSyncController {

        @Autowired
        ICsvService csvService;

        @Override
        @RolesAllowed({ "SUPER_AMMINISTRATORE", "AMMINISTRATORE_SISTEMA" })
        public ResponseEntity<?> getCSV(String codice) throws IOException {

                ByteArrayResource is = csvService.getCsv(codice);

                ResponseEntity<?> res = ResponseEntity.ok()
                                .header("Content-Disposition", "attachment; filename=" + "tributi_" + codice  + ".csv")
                                .contentLength(is.contentLength()).contentType(MediaType.parseMediaType("text/csv"))
                                .body(is);

                return res;

        }

        @Override
        @RolesAllowed({ "SUPER_AMMINISTRATORE", "AMMINISTRATORE_SISTEMA" })
        public <T> ResponseEntity<T> sendCSV(String codice) {
        		
        		csvService.sendCsv(codice);
        	
                ResponseEntity<T> res = ResponseEntity.ok().body(null);
                return res;
        }

}
