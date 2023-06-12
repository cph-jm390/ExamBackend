package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trip")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "trip_name")
    private String trip_name;

    @Column(name = "date")
    private String date;

    @Column(name = "time")
    private String time;

    @Column(name = "location")
    private String location;

    @Column(name = "duration")
    private String duration;

    @Column(name = "packingList")
    private String packingList;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "eventname",nullable = false)
    private Dinnerevent dinnerevent;



    @JoinTable(name = "trip_users", joinColumns = {
            @JoinColumn(name = "trip_name", referencedColumnName = "trip_name")}, inverseJoinColumns = {
            @JoinColumn(name = "user_email", referencedColumnName = "user_email")})
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<User> usersList = new ArrayList<>();
    //kig på dette


    public Trip() {
    }

    public Trip(Long id, String trip_name, String date, String time, String location, String duration, String packingList, Dinnerevent dinnerevent) {
        this.id = id;
        this.trip_name = trip_name;
        this.date = date;
        this.time = time;
        this.location = location;
        this.duration = duration;
        this.packingList = packingList;
        this.dinnerevent = dinnerevent;
    }

    public Trip(Long id, String trip_name, String date, String time, String location, String duration, String packingList) {
        this.id = id;
        this.trip_name = trip_name;
        this.date = date;
        this.time = time;
        this.location = location;
        this.duration = duration;
        this.packingList = packingList;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPackingList() {
        return packingList;
    }

    public void setPackinglist(String packingList) {
        this.packingList = packingList;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTrip_name() {
        return trip_name;
    }

    public void setName(String trip_name) {
        this.trip_name = trip_name;
    }

    public Dinnerevent getDinnerevent() {
        return dinnerevent;
    }

    public void setDinnerevent(Dinnerevent dinnerevent) {
        this.dinnerevent = dinnerevent;
    }
    public List<User> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<User> usersList) {
        this.usersList = usersList;
    }

    public void addUser(User tripUser) {
        usersList.add(tripUser);
    }
}