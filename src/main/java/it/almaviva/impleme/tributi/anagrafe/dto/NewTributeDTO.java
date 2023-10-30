package it.almaviva.impleme.tributi.anagrafe.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@ApiModel(value = "NuovoTributo")
@Data
@JsonInclude(Include.NON_NULL)
public class NewTributeDTO {

	@ApiModelProperty(required = true, example = "Prenotazione spazio", notes = "Nome del tributo")
	@JsonProperty("NomeTributo")
	@NotBlank(message = "Compilare il campo NomeTributo")
	private String descrizioneTributo;

	@ApiModelProperty(example = "Descrizione", notes = "Descrizione del tributo")
	@JsonProperty("DescrizioneTributo")
	private String descrizioneTributoEstesa;

	@ApiModelProperty(example = "Spontaneo", notes = "Spontaneo del tributo")
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
	@NotNull(message = "Compilare il campo validazione")
	private Boolean validazione;

	@ApiModelProperty(required = true, example = "2020", notes = "Anno del tributo")
	@NotNull(message = "Compilare il campo anno")
    private String anno;

	@ApiModelProperty(required = true, example = "5", notes = "Giorni scadenza del tributo")
	@NotNull(message = "Compilare il campo giorniScadenza")
	private Integer giorniScadenza;

	@ApiModelProperty(required = true, example = "2020-01-01 20:00:00", notes = "Data attivazione del tributo")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonProperty(value = "DataAttivazione", required = true)
	private LocalDateTime dataAttivazione;


}
