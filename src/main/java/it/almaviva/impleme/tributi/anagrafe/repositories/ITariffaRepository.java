package it.almaviva.impleme.tributi.anagrafe.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.almaviva.impleme.tributi.anagrafe.entities.TariffaEntity;

public interface ITariffaRepository  extends JpaRepository<TariffaEntity, Integer>{

    List<TariffaEntity> findAllByTribute_TributeId_Id(String codiceTributo);
    
    @Query("SELECT t FROM TariffaEntity t WHERE (t.tipoTributo.id = :tipo) AND (t.sottotipoTributo.id = :sottotipo)")
    List<TariffaEntity> findByTipoAndSottotipo(@Param("tipo")Integer tipo,@Param("sottotipo") Integer sottotipo);
    
    @Query("SELECT t FROM TariffaEntity t WHERE (t.tipoTributo.id = :tipo)")
    List<TariffaEntity> findByTipo(@Param("tipo")Integer tipo);
}
