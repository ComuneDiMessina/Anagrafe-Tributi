package it.almaviva.impleme.tributi.anagrafe.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@EqualsAndHashCode(exclude = {"tribute"})
@Entity
@Table(name = "TARIFFE", schema = "pagopa")
public class TariffaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

    @JoinColumns({
        @JoinColumn(name="id_tributo", referencedColumnName="id"),
        @JoinColumn(name="ente", referencedColumnName="ente")
    })
    @ManyToOne(fetch = FetchType.LAZY)
    private TributeEntity tribute;  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tipo_id", referencedColumnName="id")
    private TipoTributoEntity tipoTributo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="sottotipo_id", referencedColumnName="id")
    private SottotipoTributoEntity sottotipoTributo;

    @Column(name="descrizione", nullable = false)
    private String descrizione;

    @Column(name="importo_unitario", nullable = false)
    private BigDecimal importoUnitario;

    @Column(name="importo_is_editable", nullable = false)
    private Boolean importoEditable;

    @Column(name="quantita", nullable = false)
    private BigInteger quantita;

    @Column(name="quantita_is_editable", nullable = false)
    private Boolean quantitaEditable;


    @Column(name="peg")
    private String peg;

}
