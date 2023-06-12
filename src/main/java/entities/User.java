package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Size;
import org.mindrot.jbcrypt.BCrypt;

@Entity
@Table(name = "users")
public class User implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @Column(name = "user_email", length = 40, nullable = false)
  private String userEmail;

  @Basic(optional = false)
  @Size(min = 1, max = 255)
  @Column(name = "user_password", nullable = false)
  private String userPass;

  @Basic(optional = false)
  @Size(min = 1, max = 255)
  @Column(name = "address", nullable = false)
  private String address;

    @Basic(optional = false)
  @Column(name = "birthyear", nullable = false)
  private Integer birthyear;

    @Basic(optional = false)
  @Column(name = "account", nullable = false)
  private Integer account;




  @ManyToMany(mappedBy = "usersList", cascade = CascadeType.ALL)
  private List<Assignment> assignmentList;

  @JoinTable(name = "user_roles", joinColumns = {
    @JoinColumn(name = "user_email", referencedColumnName = "user_email")}, inverseJoinColumns = {
    @JoinColumn(name = "role_name", referencedColumnName = "role_name")})
  @ManyToMany
  private List<Role> roleList = new ArrayList<>();


  @JoinTable
  public List<String> getRolesAsStrings() {
    if (roleList.isEmpty()) {
      return null;
    }
    List<String> rolesAsStrings = new ArrayList<>();
    roleList.forEach((role) -> {
        rolesAsStrings.add(role.getRoleName());
      });
    return rolesAsStrings;
  }
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Transaction> transactionsList;
  public User() {}

  //TODO Change when password is hashed
   public boolean verifyPassword(String pw){
    return BCrypt.checkpw(pw, userPass);
    }

  public User(String userEmail, String userPass, String address, Integer birthyear, Integer account) {
    this.userEmail = userEmail;
    this.userPass = BCrypt.hashpw(userPass, BCrypt.gensalt());
    this.address = address;
    this.birthyear = birthyear;
    this.account = account;
  }

  public User(String userEmail, String userPass, String address, Integer birthyear, Integer account, List<Transaction> transactionsList) {
    this.userEmail = userEmail;
    this.userPass = userPass;
    this.address = address;
    this.birthyear = birthyear;
    this.account = account;
    this.transactionsList = transactionsList;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  public String getUserPass() {
    return this.userPass;
  }

  public void setUserPass(String userPass) {
    this.userPass = BCrypt.hashpw(userPass, BCrypt.gensalt());;
  }
  public Integer getBirthyear() {
    return birthyear;
  }

  public void setBirthyear(Integer birthyear) {
    this.birthyear = birthyear;
  }

  public Integer getAccount() {
    return account;
  }

  public void setAccount(Integer account) {
    this.account = account;
  }
  public List<Role> getRoleList() {
    return roleList;
  }

  public void setRoleList(List<Role> roleList) {
    this.roleList = roleList;
  }

  public void addRole(Role userRole) {
    roleList.add(userRole);
  }

  public List<Assignment> getAssignmentList() {
    return assignmentList;
  }

  public void setAssignmentList(List<Assignment> assignmentList) {
    this.assignmentList = assignmentList;
  }

  public List<Transaction> getTransactionsList() {
    return transactionsList;
  }

  public void setTransactionsList(List<Transaction> transactionsList) {
    this.transactionsList = transactionsList;
  }
  public void addTransaction(Transaction userTransaction) {
    transactionsList.add(userTransaction);
  }
}
