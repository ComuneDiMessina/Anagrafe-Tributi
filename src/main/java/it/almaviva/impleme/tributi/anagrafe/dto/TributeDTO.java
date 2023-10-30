package it.almaviva.impleme.tributi.anagrafe.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@ApiModel(value = "DettaglioTributo")
@Data
@JsonInclude(Include.NON_NULL)
public class TributeDTO {

	@NotNull
	@ApiModelProperty(required = true, example = "01", notes = "Identificativo del tributo")
	@JsonProperty("IDTributo")
	private String id;

	@ApiModelProperty(required = true, example = "SIF07", notes = "Ente del tributo")
	@JsonProperty("ente")
	private String ente;

	@ApiModelProperty(required = true, example = "Prenotazione spazio", notes = "Nome del tributo")
	@JsonProperty("NomeTributo")
	private String descrizioneTributo;

	@ApiModelProperty(example = "Descrizione", notes = "Descrizione del tributo")
	@JsonProperty("DescrizioneTributo")
	private String descrizioneTributoEstesa;

	@ApiModelProperty(example = "true", notes = "Spontaneo del tributo")
	private Boolean spontaneo;

	@ApiModelProperty(example = "Config param", notes = "Parametri di configurazione del tributo")
	private String configParam;

	@ApiModelProperty(example = "Consultazione", notes = "Tipo del tributo")
	@JsonProperty("TipoTributo")
	private String tipo;

	@ApiModelProperty(example = "passo carrabile", notes = "Descrizione ricevuta del tributo")
	private String descrizioneRT;

	@ApiModelProperty(example = "Sottotipo", notes = "Sottotipo del tributo")
	private String sottotipo;

	@ApiModelProperty(required = true, example = "false", notes = "Validazione del tributo")
	private Boolean validazione;
	@ApiModelProperty(required = true, example = "2020", notes = "Anno del tributo")
    private String anno;
	@ApiModelProperty(required = true, example = "5", notes = "Giorni scadenza del tributo")
	private Integer giorniScadenza;

	@ApiModelProperty(required = true, example = "true", notes = "Diritti segreteria del tributo")
	private Boolean dirittiSegreteria;

	@ApiModelProperty(required = true, example = "false", notes = "Indica se il tributo è prenotazione spazio")
	private Boolean prenotaSpazio;

	@ApiModelProperty(required = true, notes = "Attributi del tributo")
	private List<AttributeDTO> attributi;

	@ApiModelProperty(required = true, example = "2020-01-01 20:00:00", notes = "Data creazione del tributo")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonProperty("DataCreazione")
	private LocalDateTime dataCreazione;

	@ApiModelProperty(required = true, example = "2020-01-01 20:00:00", notes = "Data attivazione del tributo")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonProperty("DataAttivazione")
	private LocalDateTime dataAttivazione;

}
