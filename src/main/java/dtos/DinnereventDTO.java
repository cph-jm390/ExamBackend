package dtos;

import java.util.List;

public class DinnereventDTO {
    private Long id;
    private String eventname;
    private String location;
    private String dish;
    private Integer price;

    private List<AssignmentDTO> assignments;

    public DinnereventDTO() {
    }

    public DinnereventDTO(Long id, String eventname, String location, String dish, Integer price, List<AssignmentDTO> assignments) {
        this.id = id;
        this.eventname= eventname;
        this.location = location;
        this.dish=dish;
        this.price=price;
        this.assignments = assignments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public List<AssignmentDTO> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<AssignmentDTO> assignments) {
        this.assignments = assignments;
    }
}
