package it.almaviva.impleme.tributi.anagrafe.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import it.almaviva.impleme.tributi.anagrafe.util.FieldMatch;
import lombok.Data;

@ApiModel(value = "NuovaTariffa")
@Data
@JsonInclude(Include.NON_NULL)
@FieldMatch.List({
    @FieldMatch(first = "tipoTributo", second = "sottotipoTributo", message = "Il sottotipo del tributo non può essere valorizzato se il tipo del tributo è nullo"),
})
public class NewTariffaDTO {

    @NotNull(message = "Compilare il campo descrizione")
    @ApiModelProperty(required = true, example = "Unica", notes = "Descrizione tariffa")
    @JsonProperty("descrizione")
    private String descrizione;

    @NotNull(message = "Compilare il campo importoUnitario")
    @ApiModelProperty(required = true, example = "12.00", notes = "Importo unitario")
    @JsonProperty("importoUnitario")
    private BigDecimal importoUnitario;

    @NotNull(message = "Compilare il campo importoEditable")
    @ApiModelProperty(example = "false", notes = "Importo editabile",value = "false")
    @JsonProperty("isImportoEditable")
    private Boolean importoEditable;

    @NotNull(message = "Compilare il campo quantita")
    @ApiModelProperty(example = "1", notes = "quantità",value = "1")
    @JsonProperty("quantita")
    private BigInteger quantita;

    @NotNull(message = "Compilare il campo quantitaEditable")
    @ApiModelProperty(example = "false", notes = "quantità editabile",value = "false")
    @JsonProperty("isQuantitaEditable")
    private Boolean quantitaEditable;

    @NotNull(message = "Compilare il campo peg")
    @ApiModelProperty(required =true, example = "Peg", notes = "Peg del tributo")
    @JsonProperty("peg")
    private String peg;
    
    @ApiModelProperty(example = "1", notes = "Tipo del tributo")
    @JsonProperty("tipoTributo")
    private Integer tipoTributo;
    
    @ApiModelProperty(example = "1", notes = "Sottotipo del tributo")
    @JsonProperty("sottotipoTributo")
    private Integer sottotipoTributo;
}
