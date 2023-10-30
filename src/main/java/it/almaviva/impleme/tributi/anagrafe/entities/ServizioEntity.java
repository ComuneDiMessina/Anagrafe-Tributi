package it.almaviva.impleme.tributi.anagrafe.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = {"ente"})
@Entity
@Table(name = "ENTI_SERVIZI", schema = "pagopa")
public class ServizioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String titolo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descrizione;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String note;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ente_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private EnteEntity ente;


}
