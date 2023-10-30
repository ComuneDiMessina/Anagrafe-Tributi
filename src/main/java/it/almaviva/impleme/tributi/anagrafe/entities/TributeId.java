package it.almaviva.impleme.tributi.anagrafe.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TributeId implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -3678122332191974288L;

    @Column(name = "ente", nullable = false)
    private String ente;

    @Column(name = "Id", nullable = false)
    private String id;
}
