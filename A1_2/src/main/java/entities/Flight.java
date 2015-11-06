package entities;

import java.sql.Timestamp;

/**
 * Created by radud on 28/10/2015.
 */
public class Flight {
    private int id;
    private int flightNr;
    private String airplaneType;
    private City departureCity;
    private Timestamp departureDateAndHour;
    private City arrivalCity;
    private Timestamp arrivalDateAndHour;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFlightNr() {
        return flightNr;
    }

    public void setFlightNr(int flightNr) {
        this.flightNr = flightNr;
    }

    public String getAirplaneType() {
        return airplaneType;
    }

    public void setAirplaneType(String airplaneType) {
        this.airplaneType = airplaneType;
    }

    public void setDepartureCity(City departureCity) {
        this.departureCity = departureCity;
    }

    public Timestamp getDepartureDateAndHour() {
        return departureDateAndHour;
    }

    public void setDepartureDateAndHour(Timestamp departureDateAndHour) {
        this.departureDateAndHour = departureDateAndHour;
    }

    public City getDepartureCity() {
        return departureCity;
    }

    public City getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(City arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public Timestamp getArrivalDateAndHour() {
        return arrivalDateAndHour;
    }

    public void setArrivalDateAndHour(Timestamp arrivalDateAndHour) {
        this.arrivalDateAndHour = arrivalDateAndHour;
    }
}
