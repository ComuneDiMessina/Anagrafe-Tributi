package it.almaviva.impleme.tributi.anagrafe.repositories;

import it.almaviva.impleme.tributi.anagrafe.entities.EnteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IEnteRepository extends JpaRepository<EnteEntity, Integer> {

    Optional<EnteEntity> findByCodice(String codice);
}
