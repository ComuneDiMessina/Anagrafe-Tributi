package it.almaviva.impleme.tributi.anagrafe.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "Tributi", schema = "pagopa")
public class TributeEntity {

	@EmbeddedId
	private TributeId tributeId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ente", referencedColumnName = "codice", nullable = false, insertable = false, updatable = false)
	private EnteEntity ente;

	@Column(name = "descrizione_tributo")
	private String descrizioneTributo;

	@Column
	private String tipo;

	@Column
	private String anno;

	@Column(name = "giorni_scadenza")
	private Integer giorniScadenza;

	@Column
	private String sottotipo;

	@Column
	private Boolean validazione;

	@Column(name = "descrizione_rt")
	private String descrizioneRT;

	@Column(name = "descrizione_tributo_estesa")
	private String descrizioneTributoEstesa;

	@Column
	private Boolean spontaneo;

	@Column(name = "configparam")
	private String configParam;

	@Column(name = "diritti_segreteria")
	private Boolean dirittiSegreteria = false;

	@Column(name = "data_creazione", nullable = false, columnDefinition = "TIMESTAMP")
	private LocalDateTime dataCreazione;

	@Column(name = "data_attivazione", columnDefinition = "TIMESTAMP")
	private LocalDateTime dataAttivazione;

	@Column(name = "prenota_spazio")
	private Boolean prenotaSpazio = false;

	@OneToMany(mappedBy = "tribute", orphanRemoval = true, cascade = CascadeType.ALL)
	private Set<AttributeEntity> attributi = new HashSet<>(0);

	public void addAttribute(AttributeEntity item) {
		this.attributi.add(item);
		item.setTribute(this);
	}

}
