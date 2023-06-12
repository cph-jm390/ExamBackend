package entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@NamedQuery(name = "Dinnerevent.deleteAllRows", query = "DELETE from Dinnerevent")
public class Dinnerevent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    @Size(min = 1, max = 255)
    @Column (name = "eventname", nullable = false, length = 255)
    private String eventname;

    @Basic(optional = false)
    @Size(min = 1, max = 255)
    @Column (name = "location", nullable = false, length = 255)
    private String location;

    @Basic(optional = false)
    @Size(min = 1, max = 255)
    @Column (name = "dish")
    private String dish;


    @Basic(optional = false)
    @Column(name = "price", nullable = false)
    private Integer price;





    @OneToMany(mappedBy = "dinnerevent" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Assignment> assignments;



    public Dinnerevent() {
    }

    public Dinnerevent(Long id, String eventname, String location, String dish, Integer price ,List<Assignment> assignments) {
        this.id = id;
        this.location = location;
        this.eventname = eventname;
        this.dish = dish;
        this.price = price;
        this.assignments = assignments;
    }

    public Dinnerevent(Long id, String eventname, String location, String dish, Integer price) {
        this.id = id;
        this.location = location;
        this.eventname = eventname;
        this.dish = dish;
        this.price = price;
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

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }


}