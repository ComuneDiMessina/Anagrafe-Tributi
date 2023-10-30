package it.almaviva.impleme.tributi.anagrafe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.BigInteger;

@ApiModel(value = "Tariffa")
@Data
@JsonInclude(Include.NON_NULL)
public class TariffaDTO {
 
    @NotNull
    @ApiModelProperty(required = true, example = "01", notes = "Identificativo della tariffa")
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
    @ApiModelProperty(required = true, example = "Unica", notes = "Descrizione tariffa")
    @JsonProperty("descrizione")
    private String descrizione;

    @NotNull
    @ApiModelProperty(required = true, example = "12.00", notes = "Importo unitario")
    @JsonProperty("importoUnitario")
    private BigDecimal importoUnitario;

    @NotNull
    @ApiModelProperty(example = "false", notes = "Importo editabile",value = "false")
    @JsonProperty("isImportoEditable")
    private Boolean importoEditable;

    @NotNull
    @ApiModelProperty(example = "1", notes = "quantità",value = "1")
    @JsonProperty("quantita")
    private BigInteger quantita;

    @NotNull
    @ApiModelProperty(example = "false", notes = "quantità editabile",value = "false")
    @JsonProperty("isQuantitaEditable")
    private Boolean quantitaEditable;

    @NotNull
    @ApiModelProperty(required =true, example = "Peg", notes = "Peg del tributo")
    @JsonProperty("peg")
    private String peg;
    
    @JsonProperty("idTipo")
    private String idTipo;
    
    @JsonProperty("idSottotipo")
    private String idSottotipo;
    
}
