package it.almaviva.impleme.tributi.anagrafe.entities;

import it.almaviva.impleme.tributi.anagrafe.exceptions.MisconfiguredEnte;

import java.util.HashMap;
import java.util.Optional;

public class ParametersMap extends HashMap<String, String> {

    public static final String ID_CLIENT_KEY = "ID_CLIENT";

    public static final String PWD_CLIENT_KEY = "PWD_CLIENT";

    public static final String USER_REST_KEY = "USER_rest";

    public static final String PW_REST_KEY = "PW_rest";
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public String getIdClient() {
        return Optional.ofNullable(this.get(ID_CLIENT_KEY)).orElseThrow(() -> new MisconfiguredEnte(ID_CLIENT_KEY));
    }

    public String getPwdClient() {
        return Optional.ofNullable(this.get(PWD_CLIENT_KEY)).orElseThrow(() -> new MisconfiguredEnte(PWD_CLIENT_KEY));
    }

    public String getUserRest() {
        return Optional.ofNullable(this.get(USER_REST_KEY)).orElseThrow(() -> new MisconfiguredEnte(USER_REST_KEY));
    }

    public String getPwdRest() {
        return Optional.ofNullable(this.get(PW_REST_KEY)).orElseThrow(() -> new MisconfiguredEnte(PW_REST_KEY));
    }

}
