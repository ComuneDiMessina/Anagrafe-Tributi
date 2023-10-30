package it.almaviva.impleme.tributi.anagrafe.services.impl;

import it.almaviva.impleme.tributi.anagrafe.dto.TributeDTO;
import it.almaviva.impleme.tributi.anagrafe.entities.EnteEntity;
import it.almaviva.impleme.tributi.anagrafe.factory.RestTemplateFactory;
import it.almaviva.impleme.tributi.anagrafe.repositories.ITributeRepository;
import it.almaviva.impleme.tributi.anagrafe.services.ICsvService;
import it.almaviva.impleme.tributi.anagrafe.services.IEnteService;
import it.almaviva.impleme.tributi.anagrafe.services.ITributeService;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

@Service
public class CsvService implements ICsvService {

	@Value("${pagopa.url}")
	private String url;

	@Autowired
	ITributeService tributeService;

	@Autowired
	IEnteService enteService;

	@Autowired
	RestTemplateFactory restFactory;
	
	@Autowired
	private ITributeRepository iTributeRepository;

	@Override
	public ByteArrayResource getCsv(String codiceEnte) {
		List<TributeDTO> tributes = tributeService.getTributes(codiceEnte, Optional.empty(),Optional.empty());

		Stream<String> header = Stream.of(
				"CREDITORE|CODICE_PARTITARIO|DESCRIZIONE_PARTITARIO|TIPO_PARTITARIO|DATA_ATTIVAZIONE|DATA_SOSPENSIONE|FLAG_SERVIZIO");
		Stream<String> data = tributes.stream()
				.map(tribute -> String.format("%s|%s|%s|%s|0000-00-00|0000-00-00|T", tribute.getEnte(), tribute.getId(),
						tribute.getDescrizioneTributo(), tribute.getSpontaneo() ? "2" : "1"));

		String csvString = Stream.concat(header, data).collect(Collectors.joining("\n"));

		return new ByteArrayResource(csvString.getBytes(), getFileName(codiceEnte));
	}

	@Transactional
	@Override
	public void sendCsv(String codiceEnte) {

		EnteEntity ente = enteService.getComuneEntity(codiceEnte);
		RestTemplate restTemplate = restFactory.getAuthenticated(ente.getConfigparam());

		String filename = getFileName(codiceEnte);

		ByteArrayResource csv = getCsv(codiceEnte);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		ContentDisposition contentDisposition = ContentDisposition.builder("form-data").name("file").filename(filename)
				.build();

		MultiValueMap<String, String> fileMap = new LinkedMultiValueMap<>();

		fileMap.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());
		HttpEntity<byte[]> fileEntity = new HttpEntity<>(csv.getByteArray(), fileMap);

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("file", fileEntity);

		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
		try {

			String xmlData = restTemplate.postForObject(url + "/configurapartitari/send", requestEntity, String.class);
			
			JSONObject xmlJSONObj = XML.toJSONObject(xmlData);
			String esito = xmlJSONObj.getJSONObject("PMPAY_ConfigurazionePartitari").get("Esito").toString();
			
			if(esito.equals("OK")) {
				tributeService.updateDataAttivazioneTributi(codiceEnte);
			}else
			{
				String errore = xmlJSONObj.getJSONObject("PMPAY_ConfigurazionePartitari").get("Errore").toString();
				throw new RuntimeException(errore);
			}
			
		} catch (HttpClientErrorException e) {
			throw new RuntimeException(e); 
		}


	}

	public String getFileName(String codiceEnte) {
		return "tributi_" + codiceEnte + ".csv";
	}

}
