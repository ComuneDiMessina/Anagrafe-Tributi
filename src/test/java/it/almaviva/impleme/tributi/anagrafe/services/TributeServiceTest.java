package it.almaviva.impleme.tributi.anagrafe.services;

import it.almaviva.impleme.tributi.anagrafe.dto.TributeDTO;
import it.almaviva.impleme.tributi.anagrafe.entities.EnteEntity;
import it.almaviva.impleme.tributi.anagrafe.entities.TributeEntity;
import it.almaviva.impleme.tributi.anagrafe.entities.TributeId;
import it.almaviva.impleme.tributi.anagrafe.mappers.ITributeMapper;
import it.almaviva.impleme.tributi.anagrafe.repositories.IAttributeRepository;
import it.almaviva.impleme.tributi.anagrafe.repositories.IEnteRepository;
import it.almaviva.impleme.tributi.anagrafe.repositories.ITributeRepository;
import it.almaviva.impleme.tributi.anagrafe.services.impl.TributeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TributeServiceTest {
	
	@Mock
	private ITributeRepository iTributeRepository;
	
	@Mock
	private IAttributeRepository iAttributeRepository;
	
	@Mock
	private IEnteRepository iEnteRepository;
	
	@Autowired
	private ITributeMapper iTributeMapper;

	@Mock
	private IEnteService iEnteService;
	
	@InjectMocks
	private TributeService tributeService;

	@BeforeEach
	public void  init () {
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(tributeService, "iTributeMapper", iTributeMapper);
	}
	
	
	@Test
    public void should_find_tributes(){
		
		List<TributeEntity> all = new ArrayList<>(2);
		
		EnteEntity enteEntity = new EnteEntity();
		enteEntity.setCodice("SIF07");
		enteEntity.setId(1);
		enteEntity.setDescrizione("Messina");
		
		TributeId primaryKey1 = new TributeId();
		primaryKey1.setEnte("SIF07");
		primaryKey1.setId("01");
		
		TributeEntity tributeEntity1 = new TributeEntity();
		tributeEntity1.setTributeId(primaryKey1);
		tributeEntity1.setAnno("2020");
		tributeEntity1.setEnte(enteEntity);
		tributeEntity1.setDescrizioneTributo("Prenotazione spazio");
		tributeEntity1.setValidazione(false);
		tributeEntity1.setGiorniScadenza(5);
		tributeEntity1.setDirittiSegreteria(true);
		tributeEntity1.setDataCreazione(LocalDateTime.now());
		
		TributeId primaryKey2 = new TributeId();
		primaryKey2.setEnte("SIF07");
		primaryKey2.setId("02");
		
		TributeEntity tributeEntity2 = new TributeEntity();
		tributeEntity2.setTributeId(primaryKey2);
		tributeEntity2.setAnno("2020");
		tributeEntity2.setEnte(enteEntity);
		tributeEntity2.setDescrizioneTributo("Consultazione");
		tributeEntity2.setValidazione(false);
		tributeEntity2.setGiorniScadenza(3);
		tributeEntity2.setDirittiSegreteria(true);
		tributeEntity2.setDataCreazione(LocalDateTime.now());
		
        all.add(tributeEntity1);
        all.add(tributeEntity2);

		TributeDTO tributeDTO = new TributeDTO();
		tributeDTO.setAnno("2020");
		tributeDTO.setEnte("SIF07");
		tributeDTO.setDescrizioneTributo("Prenotazione spazio");
		tributeDTO.setId("01");
		tributeDTO.setValidazione(false);
		tributeDTO.setGiorniScadenza(5);
		tributeDTO.setDirittiSegreteria(true);
		tributeDTO.setDataCreazione(tributeEntity1.getDataCreazione());
		tributeDTO.setAttributi(new LinkedList<>());

		TributeDTO tribute2DTO = new TributeDTO();
		tribute2DTO.setAnno("2020");
		tribute2DTO.setEnte("SIF07");
		tribute2DTO.setDescrizioneTributo("Consultazione");
		tribute2DTO.setId("02");
		tribute2DTO.setValidazione(false);
		tribute2DTO.setGiorniScadenza(3);
		tribute2DTO.setDirittiSegreteria(true);
		tribute2DTO.setDataCreazione(tributeEntity2.getDataCreazione());
		tribute2DTO.setAttributi(new LinkedList<>());
		
	    List<TributeDTO> allDTO = new ArrayList<>(2);

        allDTO.add(tributeDTO);
        allDTO.add(tribute2DTO);

        given(iTributeRepository.findByEnte("SIF07")).willReturn(all);

		final List<TributeDTO> actual = tributeService.getTributes("SIF07", Optional.empty(),Optional.empty());
		
		assertEquals(allDTO, actual);

		
		
	}
	

}
