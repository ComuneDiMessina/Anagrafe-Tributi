package it.almaviva.impleme.tributi.anagrafe.services;

import it.almaviva.impleme.tributi.anagrafe.dto.ComuneDTO;
import it.almaviva.impleme.tributi.anagrafe.entities.EnteEntity;

import java.util.List;

public interface IEnteService {
	
	List<ComuneDTO> getComuni();

	public ComuneDTO getComune(String codice);

	public EnteEntity getComuneEntity(String codice);

}
