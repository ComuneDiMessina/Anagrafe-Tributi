package it.almaviva.impleme.tributi.anagrafe.controllers;

import it.almaviva.eai.ljsa.sdk.core.security.LjsaUser;
import it.almaviva.eai.pm.core.grpc.Group;
import it.almaviva.impleme.tributi.anagrafe.dto.AttributeDTO;
import it.almaviva.impleme.tributi.anagrafe.dto.NewAttributeDTO;
import it.almaviva.impleme.tributi.anagrafe.dto.TributeDTO;
import it.almaviva.impleme.tributi.anagrafe.services.ITributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;


@RestController
@CrossOrigin(origins = "*")
public class AttributiController  implements IAttributiController {

    @Autowired
	private ITributeService iTributeService;

    @RolesAllowed({ "AMMINISTRATORE_SERVIZIO" })
	@Override
	public TributeDTO createAttribute(String idTributo, String codice, @Valid NewAttributeDTO body) {

		LjsaUser ljsaUser = (LjsaUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		final Group group = (Group) ljsaUser.getGroups().stream().findFirst().get();
		// SIF07
		Group.DomainValue dv = group.getDomainvaluesList().stream()
				.filter(domainValue -> domainValue.getDomain().getName().equals("COMUNE")).findFirst().get();

		if (!dv.getValue().equals(codice)) {
			throw new RuntimeException("Errore nella selezione dell'ente");
		}

		return iTributeService.createAttribute(idTributo, codice, body);
	}

	@Override
	public List<AttributeDTO> getAttributi(String idTributo, String codice) {
		return iTributeService.getAttributi(idTributo, codice);
	}
    
    @RolesAllowed({ "AMMINISTRATORE_SERVIZIO" })
	@Override
	public void deleteAttribute(String idTributo, String codice, Integer id) {
		iTributeService.deleteAttribute(id);
    }
}
