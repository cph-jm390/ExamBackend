package dtos;

import java.util.List;

public class UserDTO {
    private String userName;
    private String userPass;
    private List<TripDTO> trips;

    public UserDTO() {
    }

    public UserDTO(String userName, List<TripDTO> trips) {
        this.userName = userName;
        this.trips = trips;
    }

    public UserDTO(String userName, String userPass) {
        this.userName = userName;
        this.userPass = userPass;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<TripDTO> getTrips() {
        return trips;
    }

    public void setTrips(List<TripDTO> trips) {
        this.trips = trips;
    }
}
