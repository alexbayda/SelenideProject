package org.saucelabs.utils;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDto {

    private String name;
    private String surname;
    private String username;
    private String password;

    private String postalCode;


    public UserDto(){
    }
    public UserDto(String name, String surname, String username, String password, String postalCode){
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.postalCode = postalCode;
    }

}
