package it.almaviva.impleme.tributi.anagrafe.services;

import it.almaviva.impleme.tributi.anagrafe.dto.NewServizioDTO;
import it.almaviva.impleme.tributi.anagrafe.dto.ServizioDTO;

import java.util.List;

public interface IServizioService {

    List<ServizioDTO> getServizi(String codiceComune);

    ServizioDTO createServizio(String codice, NewServizioDTO body);

    void deleteServizio(String codice, Integer id);
}
