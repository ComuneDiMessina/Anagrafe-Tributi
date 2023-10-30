package it.almaviva.impleme.tributi.anagrafe.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.almaviva.impleme.tributi.anagrafe.entities.TributeEntity;
import it.almaviva.impleme.tributi.anagrafe.entities.TributeId;

public interface ITributeRepository extends JpaRepository<TributeEntity, TributeId> {
	
	@Query(value = "SELECT nextval(:nameseq)", nativeQuery = true)
	Long getNextSeriesId(@Param("nameseq")String nameseq);
	
	@Query("SELECT t FROM TributeEntity t WHERE (t.ente.codice = :codice)")
	List<TributeEntity> findByEnte(@Param("codice")String codice);

	@Query("SELECT t FROM TributeEntity t WHERE (t.ente.codice = :codice) AND (t.anno = :anno) AND t.validazione = true")
	List<TributeEntity> findByEnteAndAnno(@Param("codice")String codice,@Param("anno") String anno );
	
	@Query("SELECT t FROM TributeEntity t WHERE (t.ente.codice = :codice) AND (t.spontaneo = :spontaneo)  AND t.validazione = true")
	List<TributeEntity> findByEnteAndSpontaneo(@Param("codice")String codice,@Param("spontaneo") Boolean spontaneo);
	
	@Query("SELECT t FROM TributeEntity t WHERE (t.ente.codice = :codice) AND (t.anno = :anno) AND (t.spontaneo = :spontaneo) AND t.validazione = true")
	List<TributeEntity> findByEnteAndAnnoAndSpontaneo(@Param("codice")String codice,@Param("anno") String anno,@Param("spontaneo") Boolean spontaneo);

	//@Modifying
	//@Query("UPDATE TributeEntity t SET t.dataAttivazione = :data WHERE (t.ente.codice = :codice) AND (t.dataAttivazione is null)")
	//void PutDataTributiByEnte(@Param("codice")String codice, @Param("data")LocalDateTime data);
	
	@Query("SELECT t FROM TributeEntity t WHERE (t.ente.codice = :codice) and t.dirittiSegreteria = true")
	Optional<TributeEntity> findDirittiDiSegreteria(@Param("codice")String codice);
}
