package pl.sda.sdaPostman;

import org.codehaus.jackson.map.ObjectMapper;
import pl.sda.messages.CreateUserRequest;
import pl.sda.messages.CreateUserResponse;
import pl.sda.messages.GetUserResponse;
import pl.sda.utils.HttpUtils;

import java.io.IOException;
import java.util.*;

public class Main {

    public static final String URL = "http://localhost:8081/sda-json/json";

    public static void main(String[] args) throws IOException {

        List<String> userIds = new ArrayList<>();
        while (true) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("1. Dodaj użytkownika");
            System.out.println("2. Wyświetl id użytkowników");
            System.out.println("3.Wyswietl dane uzytkownikow");
            String choiceRaw = scanner.nextLine();

            Integer choice = Integer.parseInt(choiceRaw);

            switch (choice) {
                case 1:
                    CreateUserRequest createUserRequest = new CreateUserRequest();

                    System.out.println("Podaj login");
                    createUserRequest.setLogin(scanner.nextLine());
                    System.out.println("Podaj imię");
                    createUserRequest.setName(scanner.nextLine());
                    System.out.println("Podaj maila");
                    createUserRequest.setMail(scanner.nextLine());

                    ObjectMapper mapper = new ObjectMapper();
                    String request = mapper.writeValueAsString(createUserRequest);
                    String createUserResponse = Sender.createUser("http://localhost:8081/sda-json/json", request);

                    CreateUserResponse response = mapper.readValue(createUserResponse, CreateUserResponse.class);
                    userIds.add(response.getId());
                    break;

                case 2:
                    System.out.println(userIds.toString());
                    break;
                case 3:
                    List<String> getUserResponses = new ArrayList<>();
                    for (String id : userIds) {

                        getUserResponses.add(Sender.getUser(URL, id));

                    }
                    System.out.println(getUserResponses.toString());
                default:
                    break;
            }
        }
    }
}
