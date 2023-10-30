package it.almaviva.impleme.tributi.anagrafe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel(value = "NuovoAttributo")
@Data
@JsonInclude(Include.NON_NULL)
public class NewAttributeDTO {
	
	@ApiModelProperty(required = true, example = "Email", notes = "Nome del campo dell'attributo")
  @NotBlank(message = "Compilare la voce campo")
    private String campo;
	
	@ApiModelProperty(required = true, example = "Testo", notes = "Tipo del dato dell'attributo")
  @NotBlank(message = "Compilare il campo tipoDato")
    private String tipoDato;
	
	@ApiModelProperty(required = true, example = "true", notes = "Obbligatorietà dell'attributo")
  @NotNull(message = "Compilare il campo obbligatorio")
    private Boolean obbligatorio;
	
	@ApiModelProperty(required = true, example = "true", notes = "Ripetibilità dell'attributo")
  @NotNull(message = "Compilare il campo ripetibile")
    private Boolean ripetibile;
	
	@ApiModelProperty(required = true, example = "true", notes = "Editabilità dell'attributo")
  @NotNull(message = "Compilare il campo editabile")
    private Boolean editabile;
	
	@ApiModelProperty(example = "tab_comuni", notes = "Lookup dell'attributo")
    private String lookup;
	
	@ApiModelProperty(example = "jsonKey", notes = "jsonKey")
    private String jsonKey;

    @ApiModelProperty(example = "causaleTemplate", notes = "causaleTemplate")
    private String causaleTemplate;

}
