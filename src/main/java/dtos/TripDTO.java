package dtos;

import dtos.GuideDTO;
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
    private String guide_name;
    private List<UserDTO> usersList;

    public TripDTO() {
    }

    public TripDTO(Long id, String trip_name, String date, String time, String location, String duration, String packingList, String guide_name, List<UserDTO> usersList) {
        this.id = id;
        this.trip_name = trip_name;
        this.date = date;
        this.time = time;
        this.location = location;
        this.duration = duration;
        this.packingList = packingList;
        this.guide_name = guide_name;
        this.usersList = usersList;
    }

    public TripDTO(Long id, String trip_name, String date, String time, String location, String duration, String packingList, String guide_name) {
        this.id = id;
        this.trip_name = trip_name;
        this.date = date;
        this.time = time;
        this.location = location;
        this.duration = duration;
        this.packingList = packingList;
        this.guide_name = guide_name;
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

    public String getGuide_name() {
        return guide_name;
    }

    public void setGuide_name(String guide_name) {
        this.guide_name = guide_name;
    }

    public List<UserDTO> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<UserDTO> usersList) {
        this.usersList = usersList;
    }
}
