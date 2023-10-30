package it.almaviva.impleme.tributi.anagrafe.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = {"tribute"})
@Entity
@Table(name = "TRIBUTI_ATTRIBUTI", schema = "pagopa")
public class AttributeEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;
	
	@Column(nullable = false)
	private String campo;
	
	@Column(name="tipo_dato", nullable = false)
	private String tipoDato;
	
	@Column(nullable = false, columnDefinition = "boolean default false")
	private Boolean obbligatorio;
	
	@Column(nullable = false, columnDefinition = "boolean default false")
	private Boolean ripetibile;
	
	@Column(nullable = false)
	private Boolean editabile;
	
	@Column
	private String lookup;
	
    @JoinColumns({
        @JoinColumn(name="tributo_id", referencedColumnName="id"),
        @JoinColumn(name="ente", referencedColumnName="ente")
    })
    @ManyToOne(fetch = FetchType.LAZY)
    private TributeEntity tribute;
	
	@Column(name = "json_key")
	private String jsonKey;

	@Column(name = "causale_template")
	private String causaleTemplate;

}
