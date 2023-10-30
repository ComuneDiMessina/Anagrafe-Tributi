package it.almaviva.impleme.tributi.anagrafe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "Nuovo Servizio")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewServizioDTO {

    @ApiModelProperty(required = true, example = "Servizi Cimeteriali", notes = "Nome del servizio")
    private String titolo;

    @ApiModelProperty(required = true, example = "Descrizione", notes = "Descrzione del servizio")
    private String descrizione;

    @ApiModelProperty(required = true, example = "Note", notes = "Eventuali note")
    private String note;

}
