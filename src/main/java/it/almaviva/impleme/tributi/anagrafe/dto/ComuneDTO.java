package it.almaviva.impleme.tributi.anagrafe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@ApiModel(value = "Ente")
@Data
@JsonInclude(Include.NON_NULL)
public class ComuneDTO {
	
	@NotNull
	@ApiModelProperty(required = true, example = "1", notes = "Identificativo del comune")
    private Integer id;
	
	@NotNull
	@ApiModelProperty(required = true, example = "SIF07", notes = "Codice del comune")
    private String codice;
	
	@ApiModelProperty(example = "Messina", notes = "Descrizione del comune")
    private String descrizione;
	
	
}
