package it.almaviva.impleme.tributi.anagrafe.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tributo_tipo", schema = "pagopa")
public class TipoTributoEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name="tributo_id", referencedColumnName="id"),
        @JoinColumn(name = "ente", referencedColumnName = "ente")
    })  
    private TributeEntity tribute;
    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ente", referencedColumnName = "codice", nullable = false, insertable = false, updatable = false)
	private EnteEntity ente;
	
    @Column(name="nome")
    private String nome;

}
