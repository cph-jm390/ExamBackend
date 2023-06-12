package dtos;

import entities.Dinnerevent;

import java.util.List;

public class AssignmentDTO {
    private Long id;

    private String familyname;
    private String date;
    private String contactinfo;
    private String eventname;
    private Dinnerevent dinnerevent;
    private List<UserDTO> usersList;

    public AssignmentDTO() {
    }

    public AssignmentDTO(Long id, String familyname, String date, String contactinfo, String eventname, List<UserDTO> usersList) {
        this.id = id;
        this.familyname = familyname;
        this.date = date;
        this.contactinfo = contactinfo;
        this.eventname = eventname;
        this.usersList = usersList;
    }

    public AssignmentDTO(Long id, String familyname, String date, String contactinfo, String eventname) {
        this.id = id;
        this.familyname = familyname;
        this.date = date;
        this.contactinfo = contactinfo;
        this.eventname = eventname;
    }

    public AssignmentDTO(Long id, String familyname, String date, String contactinfo, Dinnerevent dinnerevent) {
        this.id = id;
        this.familyname = familyname;
        this.date = date;
        this.contactinfo = contactinfo;
        this.dinnerevent = dinnerevent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFamilyname() {
        return familyname;
    }

    public void setFamilyname(String familyname) {
        this.familyname = familyname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getContactinfo() {
        return contactinfo;
    }

    public void setContactinfo(String contactinfo) {
        this.contactinfo = contactinfo;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public Dinnerevent getDinnerevent() {
        return dinnerevent;
    }

    public void setDinnerevent(Dinnerevent dinnerevent) {
        this.dinnerevent = dinnerevent;
    }

    public List<UserDTO> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<UserDTO> usersList) {
        this.usersList = usersList;
    }
}
