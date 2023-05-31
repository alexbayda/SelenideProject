package org.saucelabs.utils;

import lombok.experimental.UtilityClass;
import org.saucelabs.models.UserDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class CSVUtilities {

    public List<UserDto> usersList = getUserDetails();

    private static List<UserDto> getUserDetails() {
        List<UserDto> usersList = new ArrayList<>();
        Path pathToFile = Paths.get("src\\users.csv");
        try (
                BufferedReader br = Files.newBufferedReader(pathToFile)) {
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                var user = getUser(attributes);
                usersList.add(user);
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return usersList;
    }

    private UserDto getUser(String[] attributes) {
        String name = attributes[0];
        String surname = attributes[1];
        String username = attributes[2];
        String password = attributes[3];
        String postalCode = attributes[4];
        return new UserDto(name, surname, username, password, postalCode);
    }
}
