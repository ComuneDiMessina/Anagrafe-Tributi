package it.almaviva.impleme.tributi.anagrafe.services;

import it.almaviva.impleme.tributi.anagrafe.dto.*;

import java.util.List;
import java.util.Optional;

public interface ITributeService {
	
	List<TributeDTO> getTributes(String codice, Optional<String> anno, Optional<Boolean> spontaneo);
	
	TributeDTO createTribute(String codice, NewTributeDTO tribute);
	
	TributeDTO createAttribute(String id, String codice, NewAttributeDTO attribute);
	
	TipoTributoDTO createTributeType(String codice, String idTributo, NuovoTipoTributoDTO tipoTributo);
	
	List<TipoTributoDTO> getTributeType(String codice, String idTributo);
	
	TipoTributoDTO deleteTributeType(Integer idTipoTributo);
	
	SottotipoTributoDTO createTributeSubtype(Integer idTipoTributo, NuovoSottotipoTributoDTO sottotipoTributo);
	
	List<SottotipoTributoDTO> getTributeSubtype(Integer idTipoTributo);
	
	SottotipoTributoDTO deleteTributeSubtype(Integer idSottotipoTributo);
	
	List<AttributeDTO> getAttributi(String id, String codice);
	
	void deleteAttribute(Integer id);

	List<TariffaDTO> getTariffe(String idTributo, String codice, Optional<Integer> tipo, Optional<Integer> sottotipo);

	TariffaDTO createTariffa (String idTributo, String codice, NewTariffaDTO attribute);

	TariffaDTO deleteTariffa (String idTributo, String codice, Integer id);

	TributeDTO deleteTributo (String codice, String id);

	void updateDataAttivazioneTributi(String codiceEnte);

    TributeDTO updateTributo(String codice, String idTributo, UpdateTributeDTO body);
}
