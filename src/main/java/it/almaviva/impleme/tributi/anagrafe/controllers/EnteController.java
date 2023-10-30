package it.almaviva.impleme.tributi.anagrafe.controllers;

import it.almaviva.impleme.tributi.anagrafe.dto.ComuneDTO;
import it.almaviva.impleme.tributi.anagrafe.services.IEnteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class EnteController implements IEnteController{

    @Autowired
	private IEnteService iEnteService;

    // @PermitAll
	@Override
	public List<ComuneDTO> getComuni() {
		return iEnteService.getComuni();
	}
    
}
