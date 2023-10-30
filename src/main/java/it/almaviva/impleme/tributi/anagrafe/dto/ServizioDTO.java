package it.almaviva.impleme.tributi.anagrafe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@ApiModel(value = "Servizio")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServizioDTO {


    @NotNull
    @ApiModelProperty(required = true, example = "01", notes = "Identificativo del servizio")
    @JsonProperty("id")
    private String id;

    @ApiModelProperty(required = true, example = "SIF07", notes = "Ente del tributo")
    @JsonProperty("ente")
    private String ente;

    @ApiModelProperty(required = true, example = "Servizi Cimeteriali", notes = "Nome del servizio")
    private String titolo;

    @ApiModelProperty(required = true, example = "Descrizione", notes = "Descrzione del servizio")
    private String descrizione;

    @ApiModelProperty(required = true, example = "Note", notes = "Eventuali note")
    private String note;

}
