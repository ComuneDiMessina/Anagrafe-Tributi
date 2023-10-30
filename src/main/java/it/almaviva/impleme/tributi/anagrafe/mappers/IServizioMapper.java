package it.almaviva.impleme.tributi.anagrafe.mappers;

import it.almaviva.impleme.tributi.anagrafe.dto.ServizioDTO;
import it.almaviva.impleme.tributi.anagrafe.entities.ServizioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mapper(componentModel="spring", imports = {Collectors.class, Optional.class, Collection.class, Stream.class }, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IServizioMapper {

    @Mapping(source = "ente.codice", target = "ente")
    ServizioDTO map(ServizioEntity entity);

    List<ServizioDTO> map(List<ServizioEntity> entityList);
}
