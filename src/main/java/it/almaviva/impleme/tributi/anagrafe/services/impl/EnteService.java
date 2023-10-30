package it.almaviva.impleme.tributi.anagrafe.services.impl;

import it.almaviva.impleme.tributi.anagrafe.dto.ComuneDTO;
import it.almaviva.impleme.tributi.anagrafe.entities.EnteEntity;
import it.almaviva.impleme.tributi.anagrafe.exceptions.EnteNotFound;
import it.almaviva.impleme.tributi.anagrafe.repositories.IEnteRepository;
import it.almaviva.impleme.tributi.anagrafe.services.IEnteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

//@Transactional
@Slf4j
@Service
public class EnteService implements IEnteService {

	@Autowired
	private IEnteRepository iEnteRepository;

	Function<EnteEntity, ComuneDTO> toComune = (EnteEntity enteEntity) -> {
		ComuneDTO c = new ComuneDTO();
		c.setId(enteEntity.getId());
		c.setCodice(enteEntity.getCodice());
		c.setDescrizione(enteEntity.getDescrizione());
		return c;
	};

	public List<ComuneDTO> getComuni() {

		return iEnteRepository.findAll().stream().map(toComune).collect(Collectors.toList());

	}

	public ComuneDTO getComune(String codice) {
		return this.iEnteRepository.findByCodice(codice).map(toComune).orElseThrow(() -> new EnteNotFound(codice));
	}

	@Override
	public EnteEntity getComuneEntity(String codice) {
		return this.iEnteRepository.findByCodice(codice).orElseThrow(() -> new EnteNotFound(codice));
	}

}
