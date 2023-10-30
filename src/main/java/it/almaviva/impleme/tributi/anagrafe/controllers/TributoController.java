package it.almaviva.impleme.tributi.anagrafe.controllers;

import it.almaviva.eai.ljsa.sdk.core.security.LjsaUser;
import it.almaviva.eai.pm.core.grpc.Group;
import it.almaviva.impleme.tributi.anagrafe.dto.NewTributeDTO;
import it.almaviva.impleme.tributi.anagrafe.dto.NuovoSottotipoTributoDTO;
import it.almaviva.impleme.tributi.anagrafe.dto.NuovoTipoTributoDTO;
import it.almaviva.impleme.tributi.anagrafe.dto.SottotipoTributoDTO;
import it.almaviva.impleme.tributi.anagrafe.dto.TipoTributoDTO;
import it.almaviva.impleme.tributi.anagrafe.dto.TributeDTO;
import it.almaviva.impleme.tributi.anagrafe.dto.UpdateTributeDTO;
import it.almaviva.impleme.tributi.anagrafe.permission.AllowedInDomain;
import it.almaviva.impleme.tributi.anagrafe.services.ITributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class TributoController implements ITributoController {

	@Autowired
	private ITributeService iTributeService;


	@RolesAllowed({ "SUPER_AMMINISTRATORE","AMMINISTRATORE_SISTEMA" })
	@Override
	public TributeDTO createTribute(@AllowedInDomain("COMUNE") String codice, @Valid NewTributeDTO body) {

		LjsaUser ljsaUser = (LjsaUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		final Group group = (Group) ljsaUser.getGroups().stream().findFirst().get();
		// SIF07
		Group.DomainValue dv = group.getDomainvaluesList().stream()
				.filter(domainValue -> domainValue.getDomain().getName().equals("COMUNE")).findFirst().get();

		if (!dv.getValue().equals(codice)) {
			throw new RuntimeException("Errore nella selezione dell'ente");
		}

		return iTributeService.createTribute(codice, body);
	}

	@RolesAllowed({ "SUPER_AMMINISTRATORE","AMMINISTRATORE_SISTEMA" })
	@Override
	public TributeDTO updateTribute(String codice, String idTributo, @Valid UpdateTributeDTO body) {
		return iTributeService.updateTributo(codice, idTributo, body);
	}


	@Override
	public List<TributeDTO> getTributi(String codice, Optional<String> anno, Optional<Boolean> spontaneo) {
		return iTributeService.getTributes(codice, anno, spontaneo);
	}

	@RolesAllowed({ "SUPER_AMMINISTRATORE","AMMINISTRATORE_SISTEMA" })
	@Override
	public TributeDTO delete(String codice, String idTributo) {
		return iTributeService.deleteTributo(codice, idTributo);
	}

	@RolesAllowed({ "SUPER_AMMINISTRATORE","AMMINISTRATORE_SISTEMA" })
	@Override
	public TipoTributoDTO createTributeType(@AllowedInDomain("COMUNE") String codice, String idTributo, @Valid NuovoTipoTributoDTO body) {

		LjsaUser ljsaUser = (LjsaUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		final Group group = (Group) ljsaUser.getGroups().stream().findFirst().get();
		// SIF07
		Group.DomainValue dv = group.getDomainvaluesList().stream()
				.filter(domainValue -> domainValue.getDomain().getName().equals("COMUNE")).findFirst().get();

		if (!dv.getValue().equals(codice)) {
			throw new RuntimeException("Errore nella selezione dell'ente");
		}
		
		return iTributeService.createTributeType(codice, idTributo, body);
	}

	@RolesAllowed({ "SUPER_AMMINISTRATORE","AMMINISTRATORE_SISTEMA" })
	@Override
	public SottotipoTributoDTO createTributeSubtype(Integer idTipo, @Valid NuovoSottotipoTributoDTO body) {
		
		return iTributeService.createTributeSubtype(idTipo, body);
		
	}

	@Override
	public List<TipoTributoDTO> getTipiTributo(String codice, String idTributo) {

		return iTributeService.getTributeType(codice, idTributo);
	}

	@Override
	public List<SottotipoTributoDTO> getSottotipiTributo(String idtributo, Integer idTipoTributo) {

		return iTributeService.getTributeSubtype(idTipoTributo);
	}

	@Override
	@RolesAllowed({ "SUPER_AMMINISTRATORE","AMMINISTRATORE_SISTEMA" })
	public TipoTributoDTO deleteTipoTributo(Integer idTipoTributo) {

		return iTributeService.deleteTributeType(idTipoTributo);
	}

	@Override
	@RolesAllowed({ "SUPER_AMMINISTRATORE","AMMINISTRATORE_SISTEMA" })
	public SottotipoTributoDTO deleteSottotipoTributo(Integer idSottotipoTributo) {

		return iTributeService.deleteTributeSubtype(idSottotipoTributo);
	}

	

}
