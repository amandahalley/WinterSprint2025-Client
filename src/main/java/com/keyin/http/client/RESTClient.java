package com.keyin.http.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyin.domain.Aircraft;
import com.keyin.domain.Airport;
import com.keyin.domain.City;
import com.keyin.domain.Passenger;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class RESTClient {
    private String serverURL;
    private HttpClient client;

    //get request for server
    private String sendGetRequest(String endpoint) {
        if (serverURL == null || !serverURL.startsWith("http")) {
            System.out.println("Error: Invalid server URL. Ensure it includes the protocol (http:// or https://)");
            return "";
        }

        String responseBody = "";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(serverURL + endpoint))
                .build();

        try {
            HttpResponse<String> response = getClient().send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                System.out.println("Error Status Code: " + response.statusCode());
            }
            responseBody = response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return responseBody;
    }

    //get all cities
    public List<City> getAllCities() {
        String response = sendGetRequest("/cities");
        return buildListFromResponse(response, new TypeReference<List<City>>() {
        });
    }

    //get all airports
    public List<Airport> getAllAirports() {
        String response = sendGetRequest("/airports");
        return buildListFromResponse(response, new TypeReference<List<Airport>>() {
        });
    }

    //get all aircraft
    public List<Aircraft> getAllAircraft() {
        String response = sendGetRequest("/aircraft");
        return buildListFromResponse(response, new TypeReference<List<Aircraft>>() {
        });
    }

    //get all passengers
    public List<Passenger> getAllPassengers() {
        String response = sendGetRequest("/passengers");
        return buildListFromResponse(response, new TypeReference<List<Passenger>>() {
        });
    }

    //airports in a city
    public List<Airport> getAirportsByCity(String cityName) {
        String response = sendGetRequest("/city/airports?name=" + cityName);
        return buildListFromResponse(response, new TypeReference<List<Airport>>() {
        });
    }

    //LOGIC TO BE ADDED FOR THESE
//    //aircrafts a passenger has travelled on
//    public List<Aircraft> getAircraftByPassenger(long passengerId) {
//        String response = sendGetRequest("/passenger/" + passengerId + "/aircraft");
//        return buildListFromResponse(response, new TypeReference<List<Aircraft>>() {});
//    }
//
//    //airports aircraft has taken off from
//    public List<Airport> getAirportsByAircraft(long aircraftId) {
//        String response = sendGetRequest("/aircraft/" + aircraftId + "/airports");
//        return buildListFromResponse(response, new TypeReference<List<Airport>>() {});
//    }
//
//    //airports used by passenger(id)
//    public List<Airport> getAirportsByPassenger(long passengerId) {
//        String response = sendGetRequest("/passenger/" + passengerId + "/airports");
//        return buildListFromResponse(response, new TypeReference<List<Airport>>() {});
//    }


    private <T> List<T> buildListFromResponse(String response, TypeReference<List<T>> typeReference) {
        List<T> resultList = new ArrayList<>();

        if (response == null || response.isEmpty()) {
            return resultList;
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            resultList = mapper.readValue(response, typeReference);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return resultList;
    }


    public String getServerURL() {
        return serverURL;
    }

    public void setServerURL(String serverURL) {
        this.serverURL = serverURL;
    }

    public HttpClient getClient() {
        if (client == null) {
            client = HttpClient.newHttpClient();
        }
        return client;
    }
}







