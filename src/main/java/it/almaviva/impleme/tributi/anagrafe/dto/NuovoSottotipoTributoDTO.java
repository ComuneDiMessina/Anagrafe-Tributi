package it.almaviva.impleme.tributi.anagrafe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "NuovoSottotipoTributo")
@Data
@JsonInclude(Include.NON_NULL)
public class NuovoSottotipoTributoDTO {
	
	@ApiModelProperty(required = true, example = "Aree Costruzioni", notes = "Nome del sottotipo di tributo")
  @NotBlank(message = "Compilare il campo nome")
  private String nome;

}
