package entities;

/**
 * Created by radud on 28/10/2015.
 */
public class Flight {
    private int id;
    private int flightNr;
    private String airplaneType;
    private String departureCity;
    private String departureDateAndHour;
    private String arrivalCity;
    private String arrivalDateAndHour;

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

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getDepartureDateAndHour() {
        return departureDateAndHour;
    }

    public void setDepartureDateAndHour(String departureDateAndHour) {
        this.departureDateAndHour = departureDateAndHour;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public String getArrivalDateAndHour() {
        return arrivalDateAndHour;
    }

    public void setArrivalDateAndHour(String arrivalDateAndHour) {
        this.arrivalDateAndHour = arrivalDateAndHour;
    }
}
