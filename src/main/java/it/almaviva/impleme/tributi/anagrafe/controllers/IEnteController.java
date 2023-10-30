package it.almaviva.impleme.tributi.anagrafe.controllers;

import io.swagger.annotations.*;
import it.almaviva.impleme.tributi.anagrafe.dto.ComuneDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@RequestMapping("v2/enti")
@Api(tags = { "enti" }, authorizations = { @Authorization(value = "X-Auth-Token"), @Authorization(value = "Bearer") })
public interface IEnteController {

    @ApiOperation(value = "Restituisce tutti i comuni", response = ComuneDTO.class, responseContainer = "List", nickname = "getComuni")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ComuneDTO.class, responseContainer = "List") })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    List<ComuneDTO> getComuni();

}