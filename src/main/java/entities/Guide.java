package entities;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQuery(name = "Guide.deleteAllRows", query = "DELETE from Guide")
public class Guide {
    @Id
    private String GUIDE_NAME;

    @Column (name = "gender")
    private String gender;

    @Column (name = "birthyear")
    private String birthyear;

    @Column (name = "profile")
    private String profile;

    @Column (name = "imageUrl")
    private String imageUrl;

    @OneToMany(mappedBy = "guide" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Trip> trips;



    public Guide() {
    }

    public Guide(String GUIDE_NAME, String gender, String birthyear, String profile, String imageUrl, List<Trip> trips) {
        this.GUIDE_NAME = GUIDE_NAME;
        this.gender = gender;
        this.birthyear = birthyear;
        this.profile = profile;
        this.imageUrl = imageUrl;
        this.trips = trips;
    }

    public Guide(String GUIDE_NAME, String gender, String birthyear, String profile, String imageUrl) {
        this.GUIDE_NAME = GUIDE_NAME;
        this.gender = gender;
        this.birthyear = birthyear;
        this.profile = profile;
        this.imageUrl = imageUrl;
    }

    public String getGUIDE_NAME() {
        return GUIDE_NAME;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthyear() {
        return birthyear;
    }

    public String getProfile() {
        return profile;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    // Constructors, getters, and setters
}