package com.keyin.http.cli;

import com.keyin.domain.Aircraft;
import com.keyin.domain.Airport;
import com.keyin.domain.City;
import com.keyin.domain.Passenger;
import com.keyin.http.client.RESTClient;

import java.util.List;
import java.util.Scanner;

public class HTTPRestCLIApplication {

    private RESTClient restClient;
    private Scanner scanner;

    public HTTPRestCLIApplication() {
        this.restClient = new RESTClient();
        this.scanner = new Scanner(System.in);
        restClient.setServerURL("http://localhost:8080");
    }

    public void start() {
        System.out.println("Welcome to flight management!");
        boolean running = true;

        while (running) {
            System.out.println("\nChoose an option:");
            displayMenuOptions();
            int choice = getUserInput();

            switch (choice) {
                case 1 -> listAllCities();
                case 2 -> listAllAirports();
                case 3 -> listAllAircraft();
                case 4 -> listAllPassengers();
                case 5 -> searchAirportsInCity();
                case 6 -> listAircraftForPassenger();
                case 7 -> listAirportsForAircraft();
                case 8 -> listAirportsForPassengers();
                case 9 -> {
                    running = false;
                    System.out.println("Exiting..");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayMenuOptions() {
        System.out.println("1. List all cities");
        System.out.println("2. List all airports");
        System.out.println("3. List all aircraft");
        System.out.println("4. List all passengers");
        System.out.println("5. Search for airports in a city");
        System.out.println("6. List all aircraft a passenger has traveled on");
        System.out.println("7. List all airports an aircraft can take off from and land at");
        System.out.println("8. List all airports passengers have used");
        System.out.println("9. Exit");
    }

    private int getUserInput() {
        System.out.print("Enter your choice: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number between 1 and 9.");
            scanner.next(); // Consume the invalid input
        }
        return scanner.nextInt();
    }

    private void listAllCities() {
        List<City> cities = restClient.getAllCities();
        System.out.println("\n--- List of Cities ---");
        displayList(cities);
    }

    private void listAllAirports() {
        List<Airport> airports = restClient.getAllAirports();
        System.out.println("\n--- List of Airports ---");
        displayList(airports);
    }

    private void listAllAircraft() {
        List<Aircraft> aircrafts = restClient.getAllAircraft();
        System.out.println("\n--- List of Aircraft ---");
        displayList(aircrafts);
    }

    private void listAllPassengers() {
        List<Passenger> passengers = restClient.getAllPassengers();
        System.out.println("\n--- List of Passengers ---");
        displayList(passengers);
    }

    private void displayList(List<?> list) {
        if (list.isEmpty()) {
            System.out.println("No data available.");
        } else {
            list.forEach(System.out::println);
        }
    }

    private void searchAirportsInCity() {
        System.out.print("Enter city name: ");
        String cityName = scanner.nextLine();
        List<Airport> airports = restClient.getAirportsByCity(cityName);

        if (airports.isEmpty()) {
            System.out.println("No airports found in " + cityName);
        } else {
            System.out.println("\nAirports in " + cityName + ":");
            airports.forEach(airport -> System.out.println(airport.getName() + " (" + airport.getCode() + ")"));
        }
    }

    private void listAircraftForPassenger() {
        System.out.print("Enter passenger ID: ");
        long passengerId = getValidId();
        List<Aircraft> aircrafts = restClient.getAircraftByPassenger(passengerId);

        if (aircrafts.isEmpty()) {
            System.out.println("No aircraft found for this passenger.");
        } else {
            System.out.println("\nAircraft passenger has traveled on:");
            aircrafts.forEach(aircraft -> System.out.println(aircraft.getType() + " - " + aircraft.getAirlineName()));
        }
    }

    private void listAirportsForAircraft() {
        System.out.print("Enter aircraft ID: ");
        long aircraftId = getValidId();
        List<Airport> airports = restClient.getAirportsByAircraft(aircraftId);

        if (airports.isEmpty()) {
            System.out.println("No associated airports found for this aircraft.");
        } else {
            System.out.println("\nAirports where the aircraft can take off and land:");
            airports.forEach(airport -> System.out.println(airport.getName() + " (" + airport.getCode() + ")"));
        }
    }

    private void listAirportsForPassengers() {
        System.out.print("Enter passenger ID: ");
        long passengerId = getValidId();
        List<Airport> airports = restClient.getAirportsByPassenger(passengerId);

        if (airports.isEmpty()) {
            System.out.println("No airports found for this passenger.");
        } else {
            System.out.println("\nAirports used by passenger:");
            airports.forEach(airport -> System.out.println(airport.getName() + " (" + airport.getCode() + ")"));
        }
    }

    private long getValidId() {
        while (!scanner.hasNextLong()) {
            System.out.println("Invalid ID! Please enter a valid ID number.");
            scanner.next(); // Consume invalid input
        }
        return scanner.nextLong();
    }

    public static void main(String[] args) {
        HTTPRestCLIApplication cliApp = new HTTPRestCLIApplication();
        cliApp.start();
    }
}






