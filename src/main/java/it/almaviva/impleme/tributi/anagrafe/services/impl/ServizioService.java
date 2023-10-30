package it.almaviva.impleme.tributi.anagrafe.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import it.almaviva.impleme.tributi.anagrafe.dto.NewServizioDTO;
import it.almaviva.impleme.tributi.anagrafe.dto.ServizioDTO;
import it.almaviva.impleme.tributi.anagrafe.entities.EnteEntity;
import it.almaviva.impleme.tributi.anagrafe.entities.ServizioEntity;
import it.almaviva.impleme.tributi.anagrafe.exceptions.ServizioNotFound;
import it.almaviva.impleme.tributi.anagrafe.mappers.IServizioMapper;
import it.almaviva.impleme.tributi.anagrafe.repositories.IEnteRepository;
import it.almaviva.impleme.tributi.anagrafe.repositories.IServizioRepository;
import it.almaviva.impleme.tributi.anagrafe.services.IServizioService;

@Service
public class ServizioService implements IServizioService {

    private final IServizioRepository iServizioRepository;
    private final IServizioMapper iServizioMapper;
    private final IEnteRepository iEnteRepository;

    public ServizioService(IServizioRepository iServizioRepository, IServizioMapper iServizioMapper, IEnteRepository iEnteRepository) {
        this.iServizioRepository = iServizioRepository;
        this.iServizioMapper = iServizioMapper;
        this.iEnteRepository = iEnteRepository;
    }

    @Override
    public List<ServizioDTO> getServizi(String codiceComune) {


        final List<ServizioEntity> byEnte = iServizioRepository.findByEnte(codiceComune);
        return iServizioMapper.map(byEnte);
    }

    @Override
    public ServizioDTO createServizio(String codice, NewServizioDTO body) {

        ServizioEntity se = new ServizioEntity();
        se.setDescrizione(body.getDescrizione());
        se.setNote(body.getNote());
        se.setTitolo(body.getTitolo());

        final Optional<EnteEntity> enteEntity = iEnteRepository.findByCodice(codice);
        se.setEnte(enteEntity.get());

        se = iServizioRepository.save(se);

        return iServizioMapper.map(se);
    }


    @Override
    public void deleteServizio(String codice, Integer id) {
        iServizioRepository.findById(id).orElseThrow(()-> new ServizioNotFound(id, codice));
        iServizioRepository.deleteById(id);
    }

}
