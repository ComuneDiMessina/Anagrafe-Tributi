package it.almaviva.impleme.tributi.anagrafe.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tributo_sottotipo", schema = "pagopa")
public class SottotipoTributoEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tipo_id", referencedColumnName="id", nullable = false)
    private TipoTributoEntity tipoTributo;
    
    @Column(name="nome")
    private String nome;

}
