package it.almaviva.impleme.tributi.anagrafe.controllers;

import io.swagger.annotations.*;
import it.almaviva.impleme.tributi.anagrafe.dto.NewAttributeDTO;
import it.almaviva.impleme.tributi.anagrafe.dto.NewTariffaDTO;
import it.almaviva.impleme.tributi.anagrafe.dto.TariffaDTO;
import it.almaviva.impleme.tributi.anagrafe.exceptions.TariffaNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping("/v2/enti/{cod}/tributi/{idTributo}/tariffe")
@Api(tags = { "tariffe" }, authorizations = { @Authorization(value = "X-Auth-Token"),
                @Authorization(value = "Bearer") })
public interface ITariffaController {

        @ApiOperation(value = "Restituisce le tariffe di un tributo identificate da :idTributo e da: cod", response = TariffaDTO.class, responseContainer = "List", nickname = "getTariffe")
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "Success", response = TariffaDTO.class, responseContainer = "List") })
        @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
        @ResponseStatus(HttpStatus.OK)
        public List<TariffaDTO> getList(
                        @PathVariable("idTributo") @ApiParam(value = "Id del tributo", example = "01", required = true) String id,
                        @PathVariable("cod") @ApiParam(value = "Codice dell'ente.", example = "SIF07", required = true) String codice,
                        @RequestParam("tipo") @ApiParam(value = "Tipo del tributo", example = "1", required = false) Optional<Integer> tipo,
                        @RequestParam("sottotipo") @ApiParam(value = "Sottotipo del tributo", example = "1", required = false) Optional<Integer> sottotipo);

        @ApiOperation(value = "Inserisce una nuova tariffa", nickname = "createTariffa", response = TariffaDTO.class)
        @ApiResponses(value = { @ApiResponse(code = 201, message = "Tariffa created", response = TariffaDTO.class),
                        @ApiResponse(code = 400, message = "Invalid input"),
                        @ApiResponse(code = 409, message = "Tariffa already exists") })
        @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        @ResponseStatus(HttpStatus.CREATED)
        TariffaDTO create(
                        @PathVariable("idTributo") @ApiParam(value = "Id del tributo", example = "01", required = true) String id,
                        @PathVariable("cod") @ApiParam(value = "Codice dell'ente.", example = "SIF07", required = true) String codice,
                        @RequestBody @ApiParam(value = "Tariffa to add. Cannot null or empty.", required = true) @Valid NewTariffaDTO body);

        @ApiOperation(value = "Elimina la tariffa identificata da :idTributo da :cod e :id", response = NewAttributeDTO.class, nickname = "deleteTariffa")
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "Success", response = TariffaDTO.class),
                        @ApiResponse(code = 404, message = "Tariffa not found", response = TariffaNotFound.class) })
        @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
        @ResponseStatus(HttpStatus.OK)
        public TariffaDTO delete(
                        @PathVariable("idTributo") @ApiParam(value = "Id del tributo", example = "01", required = true) String idTributo,
                        @PathVariable("cod") @ApiParam(value = "Codice dell'ente.", example = "SIF07", required = true) String codice,
                        @PathVariable("id") @ApiParam(value = "Id della tariffa", example = "111", required = true) Integer id);
}
