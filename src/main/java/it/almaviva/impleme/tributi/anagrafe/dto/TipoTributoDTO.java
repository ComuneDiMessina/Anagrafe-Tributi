package it.almaviva.impleme.tributi.anagrafe.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "TipoTributo")
@Data
@JsonInclude(Include.NON_NULL)
public class TipoTributoDTO {
	
		@NotNull
	    @ApiModelProperty(required = true, example = "1", notes = "Identificativo del tipo del tributo")
	    @JsonProperty("id")
	    private String id;

	    @NotNull
	    @ApiModelProperty(required = true, example = "01", notes = "Identificativo del tributo")
	    @JsonProperty("idTributo")
	    private String idTributo;

	    @NotNull
	    @ApiModelProperty(required = true, example = "SIF07", notes = "Ente del tributo")
	    @JsonProperty("ente")
	    private String ente;
	    
	    @NotNull
	    @ApiModelProperty(required = true, example = "Diritti", notes = "Nome del tipo del tributo")
	    @JsonProperty("nome")
	    private String nome;

}
