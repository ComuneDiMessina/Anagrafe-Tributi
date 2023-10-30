package it.almaviva.impleme.tributi.anagrafe.repositories;

import it.almaviva.impleme.tributi.anagrafe.entities.ServizioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IServizioRepository extends JpaRepository<ServizioEntity, Integer> {

    @Query("SELECT s FROM ServizioEntity s inner join fetch   s.ente WHERE (s.ente.codice = :codice)")
    List<ServizioEntity> findByEnte(@Param("codice")String codice);
}
