package it.almaviva.impleme.tributi.anagrafe.controllers;

import io.swagger.annotations.*;
import it.almaviva.impleme.tributi.anagrafe.dto.AttributeDTO;
import it.almaviva.impleme.tributi.anagrafe.dto.NewAttributeDTO;
import it.almaviva.impleme.tributi.anagrafe.dto.TributeDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("v2/enti/{cod}/tributi/{idtributo}/attributi")
@Api(tags = { "attributi tributi" }, authorizations = { @Authorization(value = "X-Auth-Token"),
        @Authorization(value = "Bearer") })
public interface IAttributiController {

    @ApiOperation(value = "Inserisce una nuovo attributo", nickname = "createAttribute", response = TributeDTO.class)
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Attribute created", response = TributeDTO.class),
            @ApiResponse(code = 400, message = "Invalid input"),
            @ApiResponse(code = 409, message = "Attribute already exists") })
    @PostMapping(value="create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    TributeDTO createAttribute(
            @PathVariable("idtributo") @ApiParam(value = "Id del tributo", example = "01", required = true) String id,
            @PathVariable("cod") @ApiParam(value = "Codice dell'ente.", example = "SIF07", required = true) String codice,
            @RequestBody @ApiParam(value = "Attribute to add. Cannot null or empty.", required = true) @Valid NewAttributeDTO body);

            

    @ApiOperation(value = "Restituisce gli attributi di un tributo identificati da :idtributo e da: cod", response = NewAttributeDTO.class, responseContainer = "List", nickname = "getAttributi")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = AttributeDTO.class, responseContainer = "List") })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    List<AttributeDTO> getAttributi(
            @PathVariable("idtributo") @ApiParam(value = "Id del tributo", example = "01", required = true) String id,
            @PathVariable("cod") @ApiParam(value = "Codice dell'ente.", example = "SIF07", required = true) String codice);



    @ApiOperation(value = "Cancella un attributo", nickname = "deleteAttribute")
    @ApiResponses(value = { @ApiResponse(code = 204, message = "The attribute was successfully deleted"),
            @ApiResponse(code = 404, message = "Attribute not found"),
            @ApiResponse(code = 409, message = "Attribute can not be deleted") })
    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteAttribute(
            @PathVariable("idtributo") @ApiParam(value = "Id del tributo", example = "01", required = true) String idTributo,
            @PathVariable("cod") @ApiParam(value = "Codice dell'ente.", example = "SIF07", required = true) String codice,
            @PathVariable("id") @ApiParam(required = true, value = "Id of the attribute to be deleted. Cannot be empty.", example = "1") Integer id);

}
