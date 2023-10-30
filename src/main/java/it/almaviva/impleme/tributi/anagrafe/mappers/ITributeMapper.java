package it.almaviva.impleme.tributi.anagrafe.mappers;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import it.almaviva.impleme.tributi.anagrafe.dto.AttributeDTO;
import it.almaviva.impleme.tributi.anagrafe.dto.SottotipoTributoDTO;
import it.almaviva.impleme.tributi.anagrafe.dto.TariffaDTO;
import it.almaviva.impleme.tributi.anagrafe.dto.TipoTributoDTO;
import it.almaviva.impleme.tributi.anagrafe.dto.TributeDTO;
import it.almaviva.impleme.tributi.anagrafe.entities.AttributeEntity;
import it.almaviva.impleme.tributi.anagrafe.entities.SottotipoTributoEntity;
import it.almaviva.impleme.tributi.anagrafe.entities.TariffaEntity;
import it.almaviva.impleme.tributi.anagrafe.entities.TipoTributoEntity;
import it.almaviva.impleme.tributi.anagrafe.entities.TributeEntity;

@Mapper(componentModel="spring", imports = {Collectors.class, Optional.class, Collection.class, Stream.class }, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ITributeMapper {
	
	@Mapping(target = "id", source = "tributeId.id")
	@Mapping(target = "ente", source = "ente.codice")
	@Named("toDto")
	TributeDTO entityToDTO(TributeEntity tribute);
	
	@Mapping(target = "tributoId", source = "tribute.tributeId.id")
	@Mapping(target = "ente", source = "tribute.tributeId.ente")
	AttributeDTO entityToDTO(AttributeEntity attribute);

	@Mapping(target = "idTributo", source = "tribute.tributeId.id")
	@Mapping(target = "ente", source = "tribute.tributeId.ente")
	@Mapping(target = "idTipo", source = "tipoTributo.id")
	@Mapping(target = "idSottotipo", source = "sottotipoTributo.id")
	TariffaDTO entityToDTO(TariffaEntity attribute);
	
	List<AttributeDTO> map(Set<AttributeEntity> entity);
	
	@IterableMapping(qualifiedByName = "toDto")
	List<TributeDTO> toDtoList(Iterable<TributeEntity> entities);

	@Mapping(target ="tribute.tributeId.id", source =  "idTributo" )
	@Mapping(target = "tribute.tributeId.ente" , source = "ente")
	@Mapping(target = "tipoTributo.id", source = "idTipo")
	@Mapping(target = "sottotipoTributo.id", source = "idSottotipo")
	TariffaEntity dtoToEntity(TariffaDTO tariffa);
	
	@Mapping(target ="idTributo", source =  "tribute.tributeId.id" )
	@Mapping(target ="ente", source =  "tribute.tributeId.ente" )
	TipoTributoDTO entityToDTO(TipoTributoEntity tipoTributo);
	
	@Mapping(target ="idTipoTributo", source =  "tipoTributo.id" )
	SottotipoTributoDTO entityToDTO(SottotipoTributoEntity sottotipoTributo);

}
