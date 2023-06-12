package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "assignment")
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "familyname")
    private String familyname;

    @Column(name = "date")
    private String date;

    @Column(name = "contactInfo")
    private String contactInfo;


    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "eventname",nullable = true)
    private Dinnerevent dinnerevent;



    @JoinTable(name = "family_users", joinColumns = {
            @JoinColumn(name = "familyname", referencedColumnName = "familyname")}, inverseJoinColumns = {
            @JoinColumn(name = "user_email", referencedColumnName = "user_email")})
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<User> usersList = new ArrayList<>();


    public Assignment() {
    }

    public Assignment(Long id, String familyname, String date, String contactInfo, Dinnerevent dinnerevent) {
        this.id = id;
        this.familyname = familyname;
        this.date = date;
        this.contactInfo = contactInfo;
        this.dinnerevent = dinnerevent;
    }

    public Assignment(Long id, String familyname, String date, String contactInfo) {
        this.id = id;
        this.familyname = familyname;
        this.date = date;
        this.contactInfo = contactInfo;

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

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
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

    public void addUser(User assignmentUser) {
        usersList.add(assignmentUser);
    }
}