package entities;

import javax.persistence.*;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    @Column(name = "startvalue", nullable = false)
    private Integer startvalue;

    @Basic(optional = false)
    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Basic(optional = false)
    @Column(name = "postvalue", nullable = false)
    private Integer postvalue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_email", referencedColumnName = "user_email")
    private User user;

    public Transaction() {
    }

    public Transaction(Long id, Integer startvalue, Integer amount, Integer postvalue) {
        this.id = id;
        this.startvalue = startvalue;
        this.amount = amount;
        this.postvalue = postvalue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStartvalue() {
        return startvalue;
    }

    public void setStartvalue(Integer startvalue) {
        this.startvalue = startvalue;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getPostvalue() {
        return postvalue;
    }

    public void setPostvalue(Integer postvalue) {
        this.postvalue = postvalue;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
