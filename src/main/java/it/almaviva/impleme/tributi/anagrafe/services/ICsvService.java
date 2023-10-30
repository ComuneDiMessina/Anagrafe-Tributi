package it.almaviva.impleme.tributi.anagrafe.services;

import org.springframework.core.io.ByteArrayResource;

public interface ICsvService {



    public ByteArrayResource getCsv(String ente);

    public void sendCsv(String ente);

    
}
