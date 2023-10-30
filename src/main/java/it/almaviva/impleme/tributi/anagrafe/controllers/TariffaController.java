package it.almaviva.impleme.tributi.anagrafe.controllers;

import it.almaviva.impleme.tributi.anagrafe.dto.NewTariffaDTO;
import it.almaviva.impleme.tributi.anagrafe.dto.TariffaDTO;
import it.almaviva.impleme.tributi.anagrafe.services.ITributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class TariffaController implements ITariffaController {

    @Autowired
    private ITributeService service;

    @Override
    public List<TariffaDTO> getList(String id, String codice, Optional<Integer> tipo, Optional<Integer> sottotipo) {
        return service.getTariffe(id, codice, tipo, sottotipo);
    }

    @RolesAllowed({ "AMMINISTRATORE_SERVIZIO" })
    @Override
    public TariffaDTO create(String id, String codice, @Valid NewTariffaDTO body) {
       return service.createTariffa(id, codice, body);
    }

    @RolesAllowed({ "AMMINISTRATORE_SERVIZIO" })
    @Override
    public TariffaDTO delete(String idTributo, String codice, Integer id) {
       return service.deleteTariffa(idTributo, codice, id);
    }
    
}
