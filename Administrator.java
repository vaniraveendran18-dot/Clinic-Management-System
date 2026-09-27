package model;

public class Administrator extends Person {

    private static final long serialVersionUID = 1L;

    private String staffId;
    private String role;

    public Administrator(String personId, String name, String contactNumber, String address,
                          String staffId, String role) {
        super(personId, name, contactNumber, address);
        this.staffId = staffId;
        this.role = role;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Administrator{" + getPersonId() + ", " + getName() + ", " + role + "}";
    }
}
