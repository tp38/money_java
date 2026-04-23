package fr.teepi38.money.dao;

import java.util.Base64;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Builder;

/**
* Credential encapsulate login and password used to connect to a database.
*
* @author thierry.probst@free.fr
* @since 0.0.1 
*/
@Data
@Builder(access = AccessLevel.PUBLIC)
public class Credential {
    private String login;
    private String pwd;

    public String getBasicAuth() {
        return "Basic " + Base64.getEncoder().encodeToString( (login+":"+pwd).getBytes());   
    }
}