package it.almaviva.impleme.tributi.anagrafe.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.almaviva.eai.ljsa.sdk.core.security.LjsaUser;
import it.almaviva.eai.pm.core.grpc.Group;
import it.almaviva.eai.pm.core.grpc.Group.DomainValue;
import it.almaviva.eai.pm.core.grpc.Group.DomainValue.Domain;
import it.almaviva.impleme.tributi.anagrafe.dto.NewTributeDTO;
import it.almaviva.impleme.tributi.anagrafe.dto.TributeDTO;
import it.almaviva.impleme.tributi.anagrafe.exceptions.EnteNotFound;
import it.almaviva.impleme.tributi.anagrafe.repositories.IEnteRepository;
import it.almaviva.impleme.tributi.anagrafe.services.impl.EnteService;
import it.almaviva.impleme.tributi.anagrafe.services.impl.TributeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({ SpringExtension.class })
@WebMvcTest(controllers = { TributoController.class })
@AutoConfigureMockMvc(addFilters = false)
public class TributoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private IEnteRepository enteRepository;

	@MockBean
	private EnteService enteService;

	@MockBean
	private TributeService tributeService;

	

	


	private final static String URI = "/v2/enti";

	@BeforeEach
	public void setSecurityCOntext(){
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority("AMMINISTRATORE");
		Map<String, String> claims = new HashMap<>();
		claims.put("test", "claim");
		DomainValue domain = DomainValue.newBuilder().setDomain(Domain.newBuilder().setName("COMUNE").build()).setValue("SIF07").build();
		Group gr = Group.newBuilder().addAllDomainvalues(Arrays.asList(domain)).build();
		LjsaUser ljsaUser = new LjsaUser("User1", "password", true, true, true, true, Arrays.asList(authority), Arrays.asList("AMMINISTRATORE"), Arrays.asList(gr), claims);
		
		Authentication authentication = Mockito.mock(Authentication.class);
		Mockito.when(authentication.getPrincipal()).thenReturn(ljsaUser);
		// Mockito.whens() for your authorization object
		SecurityContext securityContext = Mockito.mock(SecurityContext.class);
		Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
		SecurityContextHolder.setContext(securityContext);
	}

	@Test
	void verify_request_matching() throws Exception {

		// given

		List<TributeDTO> tributi = new ArrayList<TributeDTO>();
		
		TributeDTO tributeDTO = new TributeDTO();
		tributeDTO.setAnno("2020");
		tributeDTO.setEnte("SIF07");
		tributeDTO.setDescrizioneTributo("Prenotazione spazio");
		tributeDTO.setId("01");
		tributeDTO.setValidazione(false);
		tributeDTO.setGiorniScadenza(5);
		tributeDTO.setDirittiSegreteria(true);
		tributeDTO.setDataCreazione(LocalDateTime.now());
		
		TributeDTO tribute2DTO = new TributeDTO();
		tributeDTO.setAnno("2020");
		tributeDTO.setEnte("SIF07");
		tributeDTO.setDescrizioneTributo("Consultazione");
		tributeDTO.setId("02");
		tributeDTO.setValidazione(false);
		tributeDTO.setGiorniScadenza(3);
		tributeDTO.setDirittiSegreteria(true);
		tributeDTO.setDataCreazione(LocalDateTime.now());
		
		tributi.add(tributeDTO);
		tributi.add(tribute2DTO);


		given(tributeService.getTributes("SIF07", Optional.empty(),Optional.empty())).willReturn(tributi);

		// when + then
		System.out.println(URI);
        System.out.println(URI + "/SIF07/tributi");
		System.out.println(URI + "/SIF07/tributi/01/attributi/1");

		// mockMvc.perform(get(URI).contentType("application/json")).andExpect(status().isOk());
		mockMvc.perform(get(URI + "/SIF07/tributi").contentType("application/json")).andExpect(status().isOk());
		// mockMvc.perform(delete(URI + "/SIF07/tributi/01/attributi/1").contentType("application/json")).andDo(print())
				// .andExpect(status().is2xxSuccessful());

	}

	@Test
	void whenValidInput_thenReturnsTributeResource() throws Exception {

		List<TributeDTO> tributi = new ArrayList<TributeDTO>();
		
		TributeDTO tributeDTO = new TributeDTO();
		tributeDTO.setAnno("2020");
		tributeDTO.setEnte("SIF07");
		tributeDTO.setDescrizioneTributo("Prenotazione spazio");
		tributeDTO.setId("01");
		tributeDTO.setValidazione(false);
		tributeDTO.setGiorniScadenza(5);
		tributeDTO.setDirittiSegreteria(true);
		tributeDTO.setDataCreazione(LocalDateTime.now());
		
		TributeDTO tribute2DTO = new TributeDTO();
		tributeDTO.setAnno("2020");
		tributeDTO.setEnte("SIF07");
		tributeDTO.setDescrizioneTributo("Consultazione");
		tributeDTO.setId("02");
		tributeDTO.setValidazione(false);
		tributeDTO.setGiorniScadenza(3);
		tributeDTO.setDirittiSegreteria(true);
		tributeDTO.setDataCreazione(LocalDateTime.now());
		
		tributi.add(tributeDTO);
		tributi.add(tribute2DTO);

		given(tributeService.getTributes("SIF07",Optional.empty(),Optional.empty())).willReturn(tributi);

		MvcResult mvcResult = mockMvc.perform(get(URI + "/SIF07/tributi").contentType("application/json"))
				.andExpect(status().isOk()).andReturn();

		List<TributeDTO> expectedResponseBody = tributi;

		String actualResponseBody = mvcResult.getResponse().getContentAsString();

		assertThat(actualResponseBody)
				.isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(expectedResponseBody));
	}

	@Test
	// @Disabled
    void should_return_created_tribute() throws Exception {

		NewTributeDTO newTributeDTO = new NewTributeDTO();
		newTributeDTO.setAnno("2020");
		newTributeDTO.setDescrizioneTributo("Prenotazione spazio");
		newTributeDTO.setValidazione(false);
		newTributeDTO.setGiorniScadenza(5);


		TributeDTO tributeDTO = new TributeDTO();
		tributeDTO.setAnno(newTributeDTO.getAnno());
		tributeDTO.setEnte("SIF07");
		tributeDTO.setDescrizioneTributo(newTributeDTO.getDescrizioneTributo());
		tributeDTO.setValidazione(newTributeDTO.getValidazione());
		tributeDTO.setGiorniScadenza(newTributeDTO.getGiorniScadenza());
		tributeDTO.setDataCreazione(LocalDateTime.now());

        given(tributeService.createTribute("SIF07",newTributeDTO)).willReturn(tributeDTO);

        mockMvc.perform(post(URI + "/SIF07/tributi/create").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(newTributeDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.anno").value(newTributeDTO.getAnno()))
                .andExpect(jsonPath("$.NomeTributo").value(newTributeDTO.getDescrizioneTributo()))
                .andExpect(jsonPath("$.validazione").value(newTributeDTO.getValidazione()))
                .andExpect(jsonPath("$.giorniScadenza").value(newTributeDTO.getGiorniScadenza()));

    }
	
	@Test
	@Disabled
    void whenNullValue_thenReturns400() throws Exception {
    	
		NewTributeDTO newTributeDTO = new NewTributeDTO();
		newTributeDTO.setAnno("2020");
		newTributeDTO.setDescrizioneTributo("Prenotazione spazio");
		newTributeDTO.setValidazione(false);
		newTributeDTO.setGiorniScadenza(5);

        mockMvc.perform(post(URI + "/SIF07/tributi").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(newTributeDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenCodicenotFound_thenReturns404() throws Exception {

		given(tributeService.getTributes("notexist", Optional.empty(),Optional.empty())).willThrow(new EnteNotFound("notexist"));
        mockMvc.perform(get(URI + "/notexist/tributi")
                .contentType("application/json"))
                .andExpect(status().isNotFound())
                .andReturn();

    }

}
