package it.almaviva.impleme.tributi.anagrafe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel(value = "NuovoTipoTributo")
@Data
@JsonInclude(Include.NON_NULL)
public class NuovoTipoTributoDTO {

	@ApiModelProperty(required = true, example = "Diritti", notes = "Nome del tipo di tributo")
  @NotBlank(message = "Compilare il campo nome")
  private String nome;
	
}
