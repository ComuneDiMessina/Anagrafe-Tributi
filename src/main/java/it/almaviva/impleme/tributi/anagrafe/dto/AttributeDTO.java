package it.almaviva.impleme.tributi.anagrafe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@ApiModel(value = "DettaglioAttributo")
@Data
@JsonInclude(Include.NON_NULL)
public class AttributeDTO {
	
	@NotNull
	@ApiModelProperty(required = true, example = "1", notes = "Identificativo dell'attributo")
    private Integer id;
	
	@ApiModelProperty(required = true, example = "Email", notes = "Nome del campo dell'attributo")
    private String campo;
	
	@ApiModelProperty(required = true, example = "Testo", notes = "Tipo del dato dell'attributo")
    private String tipoDato;
	
	@ApiModelProperty(required = true, example = "true", notes = "Obbligatorietà dell'attributo")
    private Boolean obbligatorio;
	
	@ApiModelProperty(required = true, example = "true", notes = "Ripetibilità dell'attributo")
    private Boolean ripetibile;
	
	@ApiModelProperty(required = true, example = "true", notes = "Editabilità dell'attributo")
    private Boolean editabile;
	
	@ApiModelProperty(example = "tab_comuni", notes = "Lookup dell'attributo")
    private String lookup;
	
	@ApiModelProperty(example = "01", notes = "Id del tributo dell'attributo")
    private String tributoId;
	
	@ApiModelProperty(example = "SIF07", notes = "Ente dell'attributo")
    private String ente;
	
	@ApiModelProperty(example = "jsonKey", notes = "jsonKey")
    private String jsonKey;

	@ApiModelProperty(example = "causaleTemplate", notes = "causaleTemplate")
    private String causaleTemplate;
}
