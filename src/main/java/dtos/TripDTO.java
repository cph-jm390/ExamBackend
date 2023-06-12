package dtos;

import dtos.DinnereventDTO;
import dtos.UserDTO;

import java.util.List;

public class TripDTO {
    private Long id;

    private String trip_name;
    private String date;
    private String time;
    private String location;
    private String duration;
    private String packingList;
    private String eventname;
    private List<UserDTO> usersList;

    public TripDTO() {
    }

    public TripDTO(Long id, String trip_name, String date, String time, String location, String duration, String packingList, String eventname, List<UserDTO> usersList) {
        this.id = id;
        this.trip_name = trip_name;
        this.date = date;
        this.time = time;
        this.location = location;
        this.duration = duration;
        this.packingList = packingList;
        this.eventname = eventname;
        this.usersList = usersList;
    }

    public TripDTO(Long id, String trip_name, String date, String time, String location, String duration, String packingList, String eventname) {
        this.id = id;
        this.trip_name = trip_name;
        this.date = date;
        this.time = time;
        this.location = location;
        this.duration = duration;
        this.packingList = packingList;
        this.eventname = eventname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrip_name() {
        return trip_name;
    }

    public void setTrip_name(String trip_name) {
        this.trip_name = trip_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPackingList() {
        return packingList;
    }

    public void setPackingList(String packingList) {
        this.packingList = packingList;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public List<UserDTO> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<UserDTO> usersList) {
        this.usersList = usersList;
    }
}
