package it.almaviva.impleme.tributi.anagrafe.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;

import it.almaviva.impleme.tributi.anagrafe.entities.ParametersMap;

@Converter(autoApply = true)
public class ParametersMapConverter implements AttributeConverter<ParametersMap , String> {

  private final static ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public String convertToDatabaseColumn(ParametersMap meta) {
    try {
      return objectMapper.writeValueAsString(meta);
    } catch (JsonProcessingException ex) {
      throw new RuntimeException(ex);
    }
  }

  @Override
  public ParametersMap convertToEntityAttribute(String dbData) {
    try {
      return objectMapper.readValue(dbData, ParametersMap.class);
    } catch (IOException ex) {
        throw new RuntimeException(ex);
    }
  }

}