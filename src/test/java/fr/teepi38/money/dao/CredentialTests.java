package fr.teepi38.money.dao;

import static org.junit.Assert.assertEquals;

import java.util.Base64;

import org.junit.jupiter.api.Test;

public class CredentialTests {

    @Test
    public void credential_must_return_a_basic_auth_string() {
        String login = "login";
        String pwd = "password";
        String expected = "Basic " + Base64.getEncoder().encodeToString( (login + ":" + pwd).getBytes() );

        Credential cred = new Credential(login, pwd);
        assertEquals(expected, cred.getBasicAuth() );

    }
    
}
