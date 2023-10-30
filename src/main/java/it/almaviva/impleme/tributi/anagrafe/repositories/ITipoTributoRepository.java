package it.almaviva.impleme.tributi.anagrafe.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.almaviva.impleme.tributi.anagrafe.entities.TipoTributoEntity;
import it.almaviva.impleme.tributi.anagrafe.entities.TributeEntity;

public interface ITipoTributoRepository extends JpaRepository<TipoTributoEntity, Integer>{
	
	List<TipoTributoEntity> findByTribute(TributeEntity tribute);

}
