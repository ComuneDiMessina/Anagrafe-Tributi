package it.almaviva.impleme.tributi.anagrafe.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import it.almaviva.impleme.tributi.anagrafe.dto.NewTributeDTO;
import it.almaviva.impleme.tributi.anagrafe.dto.NuovoSottotipoTributoDTO;
import it.almaviva.impleme.tributi.anagrafe.dto.NuovoTipoTributoDTO;
import it.almaviva.impleme.tributi.anagrafe.dto.SottotipoTributoDTO;
import it.almaviva.impleme.tributi.anagrafe.dto.TipoTributoDTO;
import it.almaviva.impleme.tributi.anagrafe.dto.TributeDTO;
import it.almaviva.impleme.tributi.anagrafe.dto.UpdateTributeDTO;
import it.almaviva.impleme.tributi.anagrafe.exceptions.TributoNotFound;

@RequestMapping("v2/enti/{cod}/tributi")
@Api(tags = { "tributi" }, authorizations = { @Authorization(value = "X-Auth-Token"),
                @Authorization(value = "Bearer") })
public interface ITributoController {

        @ApiOperation(value = "Restituisce i tributi identificati da: cod", response = TributeDTO.class, responseContainer = "List", nickname = "getTributi")
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "Success", response = TributeDTO.class, responseContainer = "List") })
        @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
        @ResponseStatus(HttpStatus.OK)
        List<TributeDTO> getTributi(
                        @PathVariable("cod") @ApiParam(value = "Codice dell'ente.", example = "SIF07", required = true) String codice,
                        @RequestParam("anno") @ApiParam(value = "Anno tributo", example = "2020", required = false) Optional<String> anno,
                        @RequestParam("spontaneo") @ApiParam(value = "Spontaneo del tributo", example = "true", required = false) Optional<Boolean> spontaneo);

        @ApiOperation(value = "Inserisce una nuovo tributo", nickname = "createTribute", response = TributeDTO.class)
        @ApiResponses(value = { @ApiResponse(code = 201, message = "Tribute created", response = TributeDTO.class),
                        @ApiResponse(code = 400, message = "Invalid input"),
                        @ApiResponse(code = 409, message = "Tribute already exists") })
        @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        @ResponseStatus(HttpStatus.CREATED)
        TributeDTO createTribute(
                        @PathVariable("cod") @ApiParam(value = "Codice dell'ente.", example = "SIF07", required = true) String codice,
                        @RequestBody @ApiParam(value = "Tribute to add. Cannot null or empty.", required = true) @Valid NewTributeDTO body);

        @ApiOperation(value = "Aggiorna un tributo", nickname = "updateTribute", response = TributeDTO.class)
        @ApiResponses(value = { @ApiResponse(code = 201, message = "Tribute updated", response = TributeDTO.class),
                @ApiResponse(code = 400, message = "Invalid input")})
        @PutMapping(value = "{idTributo}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        @ResponseStatus(HttpStatus.CREATED)
        TributeDTO updateTribute(
                @PathVariable("cod") @ApiParam(value = "Codice dell'ente.", example = "SIF07", required = true) String codice,
                @PathVariable("idTributo") @ApiParam(value = "Id del Tributo.", example = "01", required = true) String idTributo,
                @RequestBody @ApiParam(value = "Tribute to update", required = true) @Valid UpdateTributeDTO body);

        @ApiOperation(value = "Elimina il tributo identificato da :cod e :idTributo", response = TributeDTO.class,  nickname = "deleteTributi")
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "Success", response = TributeDTO.class),
                        @ApiResponse(code = 404, message = "Tributo not found", response = TributoNotFound.class) })

        @DeleteMapping(value = "{idTributo}", produces = MediaType.APPLICATION_JSON_VALUE)
        @ResponseStatus(HttpStatus.OK)
        TributeDTO delete(
                        @PathVariable("cod") @ApiParam(value = "Codice dell'ente.", example = "SIF07", required = true) String codice,
                        @PathVariable("idTributo") @ApiParam(value = "Id del Tributo.", example = "01", required = true) String idTributo);

        
        @ApiOperation(value = "Inserisce una nuovo tipo di tributo", nickname = "createTributeType", response = TipoTributoDTO.class)
        @ApiResponses(value = { @ApiResponse(code = 201, message = "Tribute type created", response = TipoTributoDTO.class),
                        @ApiResponse(code = 400, message = "Invalid input"),
                        @ApiResponse(code = 409, message = "Tribute type already exists") })
        @PostMapping(value = "{idTributo}/tipi/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        @ResponseStatus(HttpStatus.CREATED)
        TipoTributoDTO createTributeType(
                        @PathVariable("cod") @ApiParam(value = "Codice dell'ente.", example = "SIF07", required = true) String codice,
                        @PathVariable("idTributo") @ApiParam(value = "Id del Tributo.", example = "01", required = true) String idTributo,
                        @RequestBody @ApiParam(value = "Tribute type to add. Cannot null or empty.", required = true) @Valid NuovoTipoTributoDTO body);
        
        @ApiOperation(value = "Restituisce i tipi tributi identificati da: cod e da :idTributo", response = TipoTributoDTO.class, responseContainer = "List", nickname = "getTipiTributo")
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "Success", response = TipoTributoDTO.class, responseContainer = "List") })
        @GetMapping(value = "{idTributo}/tipi", produces = MediaType.APPLICATION_JSON_VALUE)
        @ResponseStatus(HttpStatus.OK)
        List<TipoTributoDTO> getTipiTributo(
                        @PathVariable("cod") @ApiParam(value = "Codice dell'ente.", example = "SIF07", required = true) String codice,
                        @PathVariable("idTributo") @ApiParam(value = "Id del Tributo.", example = "01", required = true) String idTributo);
        
        @DeleteMapping(value = "/tipi/{idTipo}", produces = MediaType.APPLICATION_JSON_VALUE)
        @ResponseStatus(HttpStatus.OK)
        TipoTributoDTO deleteTipoTributo(
                        @PathVariable("idTipo") @ApiParam(value = "Id del tipo del tributo.", example = "1", required = true) Integer idTipoTributo);
        
        @ApiOperation(value = "Inserisce una nuovo sottotipo di tributo", nickname = "createTributeSubtype", response = SottotipoTributoDTO.class)
        @ApiResponses(value = { @ApiResponse(code = 201, message = "Tribute subtype created", response = SottotipoTributoDTO.class),
                        @ApiResponse(code = 400, message = "Invalid input"),
                        @ApiResponse(code = 409, message = "Tribute subtype already exists") })
        @PostMapping(value = "/tipi/{idTipo}/sottotipi/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        @ResponseStatus(HttpStatus.CREATED)
        SottotipoTributoDTO createTributeSubtype(
                        @PathVariable("idTipo") @ApiParam(value = "Id del tipo del tributo.", example = "1", required = true) Integer idTipo,
                        @RequestBody @ApiParam(value = "Tribute subtype to add. Cannot null or empty.", required = true) @Valid NuovoSottotipoTributoDTO body);
        
        @ApiOperation(value = "Restituisce i sottotipi di tributi identificati da:idTipoTributo", response = SottotipoTributoDTO.class, responseContainer = "List", nickname = "getSottotipiTributo")
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "Success", response = SottotipoTributoDTO.class, responseContainer = "List") })
        @GetMapping(value = "/{idTributo}/tipi/{idTipo}/sottotipi", produces = MediaType.APPLICATION_JSON_VALUE)
        @ResponseStatus(HttpStatus.OK)
        List<SottotipoTributoDTO> getSottotipiTributo(
                @PathVariable("idTributo") @ApiParam(value = "Id del Tributo.", example = "01", required = true) String idTributo,
                        @PathVariable("idTipo") @ApiParam(value = "Id del tipo del tributo.", example = "1", required = true) Integer idTipoTributo);

        @DeleteMapping(value = "/sottotipi/{idSottotipo}", produces = MediaType.APPLICATION_JSON_VALUE)
        @ResponseStatus(HttpStatus.OK)
        SottotipoTributoDTO deleteSottotipoTributo(
                        @PathVariable("idSottotipo") @ApiParam(value = "Id del sottotipo del tributo.", example = "1", required = true) Integer idSottotipoTributo);
        
}
