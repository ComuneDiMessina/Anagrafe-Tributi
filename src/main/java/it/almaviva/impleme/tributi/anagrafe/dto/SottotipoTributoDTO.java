package it.almaviva.impleme.tributi.anagrafe.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "SottotipoTributo")
@Data
@JsonInclude(Include.NON_NULL)
public class SottotipoTributoDTO {

	@NotNull
    @ApiModelProperty(required = true, example = "1", notes = "Identificativo del sottotipo del tributo")
    @JsonProperty("id")
    private String id;

    @NotNull
    @ApiModelProperty(required = true, example = "01", notes = "Identificativo del tipo del tributo")
    @JsonProperty("idTipo")
    private String idTipoTributo;
    
    @NotNull
    @ApiModelProperty(required = true, example = "Diritti", notes = "Nome del sottotipo del tributo")
    @JsonProperty("nome")
    private String nome;
	
}
