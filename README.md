## Release
    mvn release:clean release:prepare release:perform -Darguments="-Dmaven.deploy.skip=true" -Darguments="-DskipTests"

## Build Immagine Docker

    docker image build -t [REGISTRY]/impleme/anagrafe-tributi[:TAG] .

## Pubblicazione api

le seguenti API sono pubbliche quindi su wso2 non va settata l'autenticazione con bearer
* /v2/enti
* /v2/enti/{cod}/servizi
* /v2/enti/{cod}/tributi
* /v2/enti/{cod}/tributi/{idTributo}/tipi/{idTipo}/sottotipi
* /v2/enti/{cod}/tributi/{idTributo}/tipi  
* /v2/enti/{cod}/tributi/{idTributo}/tariffe
* /v2/enti/{cod}/tributi/{idtributo}/attributi

## Requisiti

* Il ms si appoggia allo schema pagopa
* Eseguire lo script sql che si trova in docs
