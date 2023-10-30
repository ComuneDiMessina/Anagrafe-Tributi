package it.almaviva.impleme.tributi.anagrafe.services.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import it.almaviva.impleme.tributi.anagrafe.dto.AttributeDTO;
import it.almaviva.impleme.tributi.anagrafe.dto.NewAttributeDTO;
import it.almaviva.impleme.tributi.anagrafe.dto.NewTariffaDTO;
import it.almaviva.impleme.tributi.anagrafe.dto.NewTributeDTO;
import it.almaviva.impleme.tributi.anagrafe.dto.NuovoSottotipoTributoDTO;
import it.almaviva.impleme.tributi.anagrafe.dto.NuovoTipoTributoDTO;
import it.almaviva.impleme.tributi.anagrafe.dto.SottotipoTributoDTO;
import it.almaviva.impleme.tributi.anagrafe.dto.TariffaDTO;
import it.almaviva.impleme.tributi.anagrafe.dto.TipoTributoDTO;
import it.almaviva.impleme.tributi.anagrafe.dto.TributeDTO;
import it.almaviva.impleme.tributi.anagrafe.dto.UpdateTributeDTO;
import it.almaviva.impleme.tributi.anagrafe.entities.AttributeEntity;
import it.almaviva.impleme.tributi.anagrafe.entities.EnteEntity;
import it.almaviva.impleme.tributi.anagrafe.entities.SottotipoTributoEntity;
import it.almaviva.impleme.tributi.anagrafe.entities.TariffaEntity;
import it.almaviva.impleme.tributi.anagrafe.entities.TipoTributoEntity;
import it.almaviva.impleme.tributi.anagrafe.entities.TributeEntity;
import it.almaviva.impleme.tributi.anagrafe.entities.TributeId;
import it.almaviva.impleme.tributi.anagrafe.exceptions.SottotipoTributoNotFound;
import it.almaviva.impleme.tributi.anagrafe.exceptions.TariffaNotFound;
import it.almaviva.impleme.tributi.anagrafe.exceptions.TipoTributoNotFound;
import it.almaviva.impleme.tributi.anagrafe.exceptions.TributoNotFound;
import it.almaviva.impleme.tributi.anagrafe.mappers.ITributeMapper;
import it.almaviva.impleme.tributi.anagrafe.repositories.IAttributeRepository;
import it.almaviva.impleme.tributi.anagrafe.repositories.IEnteRepository;
import it.almaviva.impleme.tributi.anagrafe.repositories.ISottotipoTributoRepository;
import it.almaviva.impleme.tributi.anagrafe.repositories.ITariffaRepository;
import it.almaviva.impleme.tributi.anagrafe.repositories.ITipoTributoRepository;
import it.almaviva.impleme.tributi.anagrafe.repositories.ITributeRepository;
import it.almaviva.impleme.tributi.anagrafe.services.IEnteService;
import it.almaviva.impleme.tributi.anagrafe.services.ITributeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;


//@Transactional
@Slf4j
@Service
public class TributeService implements ITributeService {

	@Autowired
	private ITributeRepository iTributeRepository;

	@Autowired
	private IAttributeRepository iAttributeRepository;

	@Autowired
	private ITariffaRepository iTariffaRepository;

	@Autowired
	private IEnteRepository iEnteRepository;
	
	@Autowired
	private ITipoTributoRepository iTipoTributoRepository;
	
	@Autowired
	private ISottotipoTributoRepository iSottotipoTributoRepository;

	@Autowired
	private ITributeMapper iTributeMapper;

	@Autowired
	private IEnteService enteService;

	@Autowired
	private NamedParameterJdbcTemplate jdbc;

	@Override
	public List<TributeDTO> getTributes(String codice, Optional<String> anno, Optional<Boolean> spontaneo) {
		
		enteService.getComune(codice);

		Stream<TributeEntity> stream;
		if (anno.isPresent()) {
			if(spontaneo.isPresent())
				stream = iTributeRepository.findByEnteAndAnnoAndSpontaneo(codice, anno.get(),spontaneo.get()).stream();
			else
			stream = iTributeRepository.findByEnteAndAnno(codice, anno.get()).stream();
		} 
		else if(spontaneo.isPresent()) {
			stream = iTributeRepository.findByEnteAndSpontaneo(codice, spontaneo.get()).stream();
		}	
		else {
			stream = iTributeRepository.findByEnte(codice).stream();
		}



		return stream.map(iTributeMapper::entityToDTO).collect(Collectors.toList());

	}

	@Transactional
	@Override
	public TributeDTO createTribute(String codice, NewTributeDTO tribute) {

		String tributeId = String.format("%05d", iTributeRepository.getNextSeriesId("tributi_"+codice+"_seq"));
		
		TributeId primaryKey = new TributeId(codice, tributeId);

		TributeEntity entity = new TributeEntity();
		entity.setTributeId(primaryKey);
		entity.setDescrizioneTributo(tribute.getDescrizioneTributo());
		entity.setDescrizioneTributoEstesa(tribute.getDescrizioneTributoEstesa());
		entity.setSpontaneo(tribute.getSpontaneo());
		entity.setConfigParam(tribute.getConfigParam());
		entity.setTipo(tribute.getTipo());
		entity.setDescrizioneRT(tribute.getDescrizioneRT());
		entity.setSottotipo(tribute.getSottotipo());
		entity.setValidazione(tribute.getValidazione());
		entity.setAnno(tribute.getAnno());
		entity.setGiorniScadenza(tribute.getGiorniScadenza());
		entity.setPrenotaSpazio(false);
		entity.setValidazione(true);
		entity.setDirittiSegreteria(false);
		entity.setDataCreazione(LocalDateTime.now());
		entity.setDataAttivazione(tribute.getDataAttivazione());

		final Optional<EnteEntity> enteEntity = iEnteRepository.findByCodice(codice);
		entity.setEnte(enteEntity.get());

		createSequence(primaryKey);
		entity = iTributeRepository.save(entity);

		return iTributeMapper.entityToDTO(entity);
	}

	@Override
	public TributeDTO createAttribute(String id, String codice, NewAttributeDTO attribute) {

		TributeId primaryKey = new TributeId();
		primaryKey.setEnte(codice);
		primaryKey.setId(id);

		TributeEntity tributeEntity = iTributeRepository.findById(primaryKey).get();

		AttributeEntity entity = new AttributeEntity();
		entity.setCampo(attribute.getCampo());
		entity.setEditabile(attribute.getEditabile());
		entity.setJsonKey(attribute.getJsonKey());
		entity.setLookup(attribute.getLookup());
		entity.setObbligatorio(attribute.getObbligatorio());
		entity.setRipetibile(attribute.getRipetibile());
		entity.setTipoDato(attribute.getTipoDato());

		tributeEntity.addAttribute(entity);

		tributeEntity = iTributeRepository.save(tributeEntity);
		return iTributeMapper.entityToDTO(tributeEntity);
	}

	@Override
	public List<AttributeDTO> getAttributi(String id, String codice) {

		List<AttributeDTO> result = new ArrayList<AttributeDTO>();

		TributeId primaryKey = new TributeId();
		primaryKey.setEnte(codice);
		primaryKey.setId(id);

		final TributeEntity tributi = iTributeRepository.findById(primaryKey).get();

		for (AttributeEntity attribute : tributi.getAttributi()) {
			AttributeDTO a = iTributeMapper.entityToDTO(attribute);
			result.add(a);
		}

		return result;

	}

	@Override
	public void deleteAttribute(Integer id) {
		iAttributeRepository.deleteById(id);
	}

	@Override
	public List<TariffaDTO> getTariffe(String id, String codice, Optional<Integer> tipo, Optional<Integer> sottotipo) {
		
		Stream<TariffaEntity> stream;
		
		if(tipo.isPresent()) {
			if(sottotipo.isPresent())
				stream = iTariffaRepository.findByTipoAndSottotipo(tipo.get(), sottotipo.get()).stream();
			else
				stream = iTariffaRepository.findByTipo(tipo.get()).stream();
		}
		else if(sottotipo.isPresent()){
			
			throw new TributoNotFound("Specificare il tipo per poter specificare il sottotipo.");
		}
		else
			stream = iTariffaRepository.findAllByTribute_TributeId_Id(id).stream();
		
		return stream.map(iTributeMapper::entityToDTO)
				.collect(Collectors.toList());
	}

	@Override
	public TariffaDTO createTariffa(String id, String codice, NewTariffaDTO tariffa) {

		TariffaEntity entity = new TariffaEntity();
		entity.setDescrizione(tariffa.getDescrizione());
		entity.setImportoEditable(tariffa.getImportoEditable());
		entity.setImportoUnitario(tariffa.getImportoUnitario());
		entity.setPeg(tariffa.getPeg());
		entity.setQuantita(tariffa.getQuantita());
		entity.setQuantitaEditable(tariffa.getQuantitaEditable());
		
		if(tariffa.getTipoTributo()!=null) {
			
			TipoTributoEntity tipoTributoEntity = iTipoTributoRepository.findById(tariffa.getTipoTributo()).orElseThrow(() -> new TipoTributoNotFound(tariffa.getTipoTributo().toString()));
			entity.setTipoTributo(tipoTributoEntity);
			
			if(tariffa.getSottotipoTributo()!=null) {
				SottotipoTributoEntity sottotipoTributoEntity = iSottotipoTributoRepository.findById(tariffa.getSottotipoTributo()).orElseThrow(() -> new SottotipoTributoNotFound(tariffa.getSottotipoTributo().toString()));
				entity.setSottotipoTributo(sottotipoTributoEntity);
			}
		}

		TributeId tributeId = new TributeId(codice, id);
		final TributeEntity tributeEntity = iTributeRepository.findById(tributeId)
				.orElseThrow(() -> new TributoNotFound(id, codice));

		entity.setTribute(tributeEntity);

		TariffaEntity result = iTariffaRepository.save(entity);

		return iTributeMapper.entityToDTO(result);

	}

	@Override
	public TariffaDTO deleteTariffa(String idTributo, String codice, Integer id) {
		Optional<TariffaEntity> tariffa = Optional.ofNullable(iTariffaRepository.getOne(id));

		tariffa.ifPresent(t -> iTariffaRepository.deleteById(id));

		return tariffa.map(iTributeMapper::entityToDTO).orElseThrow(() -> new TariffaNotFound(id));
	}

	@Override
	public TributeDTO deleteTributo(String codice, String id) {

		TributeId tributeId = new TributeId(codice, id);

		Optional<TributeEntity> tributo = Optional.ofNullable(iTributeRepository.getOne(tributeId));

		tributo.ifPresent(t -> iTributeRepository.deleteById(tributeId));

		return tributo.map(iTributeMapper::entityToDTO).orElseThrow(() -> new TributoNotFound(id, codice));

	}

	protected void createSequence(TributeId primaryKey) {

		final String seqName = String.format("%s_%s_seq", primaryKey.getId(), primaryKey.getEnte().toLowerCase());
		final String script = String.format(
				"CREATE SEQUENCE \"%s\" INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START WITH 1 NO CYCLE;",
				seqName);
		jdbc.execute(script, (ps) -> ps.execute());
	}

	@Override
	public void updateDataAttivazioneTributi(String codiceEnte) {
		iTributeRepository.findByEnte(codiceEnte).stream()
		.filter(t -> null == t.getDataAttivazione())
		.forEach( t -> {
			t.setDataAttivazione(LocalDate.now().atStartOfDay().plusDays(1));
            iTributeRepository.save(t);
		});
	}

	@Override
	public TributeDTO updateTributo(String codice, String idTributo, UpdateTributeDTO body) {
		TributeId tributeId = new TributeId(codice, idTributo);
		TributeEntity entity = iTributeRepository.findById(tributeId)
				.orElseThrow(() -> new TributoNotFound(idTributo, codice));

		entity.setDescrizioneTributo(body.getDescrizioneTributo());
		entity.setDescrizioneTributoEstesa(body.getDescrizioneTributoEstesa());
		entity.setSpontaneo(body.getSpontaneo());
		entity.setConfigParam(body.getConfigParam());
		entity.setTipo(body.getTipo());
		entity.setDescrizioneRT(body.getDescrizioneRT());
		entity.setSottotipo(body.getSottotipo());
		entity.setValidazione(body.getValidazione());
		entity.setAnno(body.getAnno());
		entity.setGiorniScadenza(body.getGiorniScadenza());
		entity.setPrenotaSpazio(false);
		entity.setDirittiSegreteria(false);
		entity.setValidazione(true);
		if(body.getDataAttivazione() != null) {
			entity.setDataAttivazione(body.getDataAttivazione());
		}

		entity = iTributeRepository.save(entity);

		return iTributeMapper.entityToDTO(entity);
	}

	@Transactional
	@Scheduled(cron = "0 0 0 1 JAN *")
	public void reportCurrentTime() {
		final List<TributeEntity> all = iTributeRepository.findAll();
		for(TributeEntity tributo : all) {
			final String seqName = String.format("%s_%s_seq", tributo.getTributeId().getId(), tributo.getTributeId().getEnte().toLowerCase());
			final String script = String.format(
					"ALTER SEQUENCE %s RESTART;",
					seqName);
			jdbc.execute(script, (ps) -> ps.execute());
		}
	}

	@Override
	public TipoTributoDTO createTributeType(String codice, String idTributo, NuovoTipoTributoDTO tipoTributo) {
		
		TipoTributoEntity entity = new TipoTributoEntity();
		entity.setNome(tipoTributo.getNome());
		
		final Optional<EnteEntity> enteEntity = iEnteRepository.findByCodice(codice);
		entity.setEnte(enteEntity.get());
		
		TributeId tributeId = new TributeId(codice, idTributo);
		final TributeEntity tributeEntity = iTributeRepository.findById(tributeId)
				.orElseThrow(() -> new TributoNotFound(idTributo, codice));

		entity.setTribute(tributeEntity);
		
		entity = iTipoTributoRepository.save(entity);
		
		return iTributeMapper.entityToDTO(entity);
	}
	
	@Override
	public List<TipoTributoDTO> getTributeType(String codice, String idTributo) {
		
		Stream<TipoTributoEntity> stream;
		
		TributeId tributeId = new TributeId(codice, idTributo);
		final TributeEntity tributeEntity = iTributeRepository.findById(tributeId)
				.orElseThrow(() -> new TributoNotFound(idTributo, codice));
		
		stream = iTipoTributoRepository.findByTribute(tributeEntity).stream();
		
		return stream.map(iTributeMapper::entityToDTO)
				.collect(Collectors.toList());
	}


	@Override
	public SottotipoTributoDTO createTributeSubtype(Integer idTipoTributo, NuovoSottotipoTributoDTO sottotipoTributo) {
		
		SottotipoTributoEntity entity = new SottotipoTributoEntity();
		entity.setNome(sottotipoTributo.getNome());
		
		final TipoTributoEntity tipoTributoEntity = iTipoTributoRepository.findById(idTipoTributo)
				.orElseThrow(() -> new TipoTributoNotFound(idTipoTributo.toString()));
		
		entity.setTipoTributo(tipoTributoEntity);
		
		entity = iSottotipoTributoRepository.save(entity);
		
		return iTributeMapper.entityToDTO(entity);
	}

	@Override
	public List<SottotipoTributoDTO> getTributeSubtype(Integer idTipoTributo) {

		Stream<SottotipoTributoEntity> stream;
		
		final TipoTributoEntity tipoTributoEntity = iTipoTributoRepository.findById(idTipoTributo)
				.orElseThrow(() -> new TipoTributoNotFound(idTipoTributo.toString()));
		
		stream = iSottotipoTributoRepository.findByTipoTributo(tipoTributoEntity).stream();
		
		return stream.map(iTributeMapper::entityToDTO)
				.collect(Collectors.toList());

	}

	@Override
	public TipoTributoDTO deleteTributeType(Integer idTipoTributo) {

		Optional<TipoTributoEntity> tipoTributo = Optional.ofNullable(iTipoTributoRepository.getOne(idTipoTributo));

		tipoTributo.ifPresent(t -> iTipoTributoRepository.deleteById(idTipoTributo));

		return tipoTributo.map(iTributeMapper::entityToDTO).orElseThrow(() -> new TipoTributoNotFound(idTipoTributo.toString()));

	}

	@Override
	public SottotipoTributoDTO deleteTributeSubtype(Integer idSottotipoTributo) {

		Optional<SottotipoTributoEntity> sottotipoTributo = Optional.ofNullable(iSottotipoTributoRepository.getOne(idSottotipoTributo));

		sottotipoTributo.ifPresent(t -> iSottotipoTributoRepository.deleteById(idSottotipoTributo));

		return sottotipoTributo.map(iTributeMapper::entityToDTO).orElseThrow(() -> new SottotipoTributoNotFound(idSottotipoTributo.toString()));

	}

}
