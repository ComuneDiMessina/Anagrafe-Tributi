package it.almaviva.impleme.tributi.anagrafe.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.almaviva.impleme.tributi.anagrafe.entities.SottotipoTributoEntity;
import it.almaviva.impleme.tributi.anagrafe.entities.TipoTributoEntity;

public interface ISottotipoTributoRepository extends JpaRepository<SottotipoTributoEntity, Integer>{
	
	List<SottotipoTributoEntity> findByTipoTributo(TipoTributoEntity tipoTributo);

}
