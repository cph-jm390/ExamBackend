package dtos;

import java.util.List;

public class GuideDTO {
    private String GUIDE_NAME;
    private String gender;
    private String birthyear;
    private String profile;
    private String imageUrl;
    private List<TripDTO> trips;

    public GuideDTO() {
    }

    public GuideDTO(String GUIDE_NAME, String gender, String birthyear, String profile, String imageUrl, List<TripDTO> trips) {
        this.GUIDE_NAME = GUIDE_NAME;
        this.gender = gender;
        this.birthyear = birthyear;
        this.profile = profile;
        this.imageUrl = imageUrl;
        this.trips = trips;
    }

    public String getGUIDE_NAME() {
        return GUIDE_NAME;
    }

    public void setGUIDE_NAME(String GUIDE_NAME) {
        this.GUIDE_NAME = GUIDE_NAME;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthyear() {
        return birthyear;
    }

    public void setBirthyear(String birthyear) {
        this.birthyear = birthyear;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<TripDTO> getTrips() {
        return trips;
    }

    public void setTrips(List<TripDTO> trips) {
        this.trips = trips;
    }
}
