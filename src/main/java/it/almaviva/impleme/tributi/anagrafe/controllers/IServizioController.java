package it.almaviva.impleme.tributi.anagrafe.controllers;

import io.swagger.annotations.*;
import it.almaviva.impleme.tributi.anagrafe.dto.NewAttributeDTO;
import it.almaviva.impleme.tributi.anagrafe.dto.NewServizioDTO;
import it.almaviva.impleme.tributi.anagrafe.dto.ServizioDTO;
import it.almaviva.impleme.tributi.anagrafe.dto.TariffaDTO;
import it.almaviva.impleme.tributi.anagrafe.exceptions.TariffaNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("v2/enti/{cod}/servizi")
@Api(tags = { "servizi" }, authorizations = { @Authorization(value = "X-Auth-Token"),
        @Authorization(value = "Bearer") })
public interface IServizioController {

    @ApiOperation(value = "Restituisce i servizi disponibili", response = ServizioDTO.class, responseContainer = "List", nickname = "getServizi")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ServizioDTO.class, responseContainer = "List") })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    List<ServizioDTO> getServizi(
            @PathVariable("cod") @ApiParam(value = "Codice dell'ente.", example = "SIF07", required = true) String codice);

    @ApiOperation(value = "Inserisce una nuovo servizio", nickname = "createServizio", response = ServizioDTO.class)
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Servizio created", response = ServizioDTO.class),
            @ApiResponse(code = 400, message = "Invalid input"),
            @ApiResponse(code = 409, message = "Servizio already exists") })
    @PostMapping(value="create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    ServizioDTO createServizio(
            @PathVariable("cod") @ApiParam(value = "Codice dell'ente.", example = "SIF07", required = true) String codice,
            @RequestBody @ApiParam(value = "Servizio to add. Cannot null or empty.", required = true) @Valid NewServizioDTO body);

    @ApiOperation(value = "Elimina il servizio identificato da :idServizio e :cod", response = NewAttributeDTO.class, nickname = "deleteServizio")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Tariffa not found", response = TariffaNotFound.class) })
    @DeleteMapping(value = "{idServizio}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteServizio(
            @PathVariable("idServizio") @ApiParam(value = "Id del servizio", example = "1", required = true) Integer idServizio,
            @PathVariable("cod") @ApiParam(value = "Codice dell'ente.", example = "SIF07", required = true) String codice);

}
