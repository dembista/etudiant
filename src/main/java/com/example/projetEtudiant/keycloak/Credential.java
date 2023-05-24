package com.example.projetEtudiant.keycloak;


import org.keycloak.representations.idm.CredentialRepresentation;

public class Credential {
    public static CredentialRepresentation createPassword(String password){
        CredentialRepresentation credentials = new CredentialRepresentation();
        credentials.setTemporary(false);
        credentials.setType(CredentialRepresentation.PASSWORD);
        credentials.setValue(password);
        return credentials;
    }
}
