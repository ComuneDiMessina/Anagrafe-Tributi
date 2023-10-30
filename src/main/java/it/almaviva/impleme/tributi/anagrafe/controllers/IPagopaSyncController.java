package it.almaviva.impleme.tributi.anagrafe.controllers;

import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequestMapping("/v2/enti/{cod}/tributi/csv")
@Api(tags = { "tributi" }, authorizations = { @Authorization(value = "X-Auth-Token"),
        @Authorization(value = "Bearer") })
public interface IPagopaSyncController {

    @ApiOperation(value = "Restituisce il csv con cui verrà sincronizzato PagoPa contenente tutti i tributi di un ende identificati da: cod", nickname = "getCSV")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success") })
    @GetMapping(produces = "text/csv")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getCSV (
            @PathVariable("cod") @ApiParam(value = "Codice dell'ente.", example = "SIF07", required = true) String codice)  throws IOException;

    @ApiOperation(value = "Invia il csv per sincronizzare PagoPa contenente tutti i tributi di un ende identificati da: cod", nickname = "sendCSV")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success") })
    @PutMapping(produces = "text/csv")
    @ResponseStatus(HttpStatus.OK)
    public <T> ResponseEntity<T> sendCSV(
            @PathVariable("cod") @ApiParam(value = "Codice dell'ente.", example = "SIF07", required = true) String codice);

}
