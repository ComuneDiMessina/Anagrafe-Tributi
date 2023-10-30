package it.almaviva.impleme.tributi.anagrafe.controllers;

import it.almaviva.eai.ljsa.sdk.core.security.LjsaUser;
import it.almaviva.eai.pm.core.grpc.Group;
import it.almaviva.impleme.tributi.anagrafe.dto.NewServizioDTO;
import it.almaviva.impleme.tributi.anagrafe.dto.ServizioDTO;
import it.almaviva.impleme.tributi.anagrafe.services.IServizioService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ServizioController implements IServizioController{

    private final IServizioService iServizioService;

    public ServizioController(IServizioService iServizioService) {
        this.iServizioService = iServizioService;
    }

    @Override
    public List<ServizioDTO> getServizi(String codice) {
        return iServizioService.getServizi(codice);
    }

    @RolesAllowed({ "AMMINISTRATORE_SERVIZIO" })
    @Override
    public ServizioDTO createServizio(String codice, @Valid NewServizioDTO body) {

        LjsaUser ljsaUser = (LjsaUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final Group group = (Group) ljsaUser.getGroups().stream().findFirst().get();
        // SIF07
        Group.DomainValue dv = group.getDomainvaluesList().stream()
                .filter(domainValue -> domainValue.getDomain().getName().equals("COMUNE")).findFirst().get();

        if (!dv.getValue().equals(codice)) {
            throw new RuntimeException("Errore nella selezione dell'ente");
        }

        return iServizioService.createServizio(codice, body);
    }

    @Override
    @RolesAllowed({"AMMINISTRATORE_SERVIZIO"})
    public void deleteServizio(Integer idServizio, String codice) {

        LjsaUser ljsaUser = (LjsaUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final Group group = (Group) ljsaUser.getGroups().stream().findFirst().get();
        // SIF07
        Group.DomainValue dv = group.getDomainvaluesList().stream()
                .filter(domainValue -> domainValue.getDomain().getName().equals("COMUNE")).findFirst().get();

        if (!dv.getValue().equals(codice)) {
            throw new RuntimeException("Errore nella selezione dell'ente");
        }

        iServizioService.deleteServizio(codice, idServizio);
    }
}
