package dtos;

import java.util.List;

public class UserDTO {
    private String userName;
    private String userPass;
    private List<AssignmentDTO> assignments;

    public UserDTO() {
    }

    public UserDTO(String userName, List<AssignmentDTO> assignments) {
        this.userName = userName;
        this.assignments = assignments;
    }

    public UserDTO(String userName, String userPass) {
        this.userName = userName;
        this.userPass = userPass;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<AssignmentDTO> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<AssignmentDTO> assignments) {
        this.assignments = assignments;
    }
}
