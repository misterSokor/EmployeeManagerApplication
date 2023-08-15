package employee;

public class Employee {

    private String emp_id;
    private String firstName;
    private String lastName;
    private String ssn;
    private String dept;
    private String roomNumber;
    private String phoneNumber;

    public String getId() {
        return emp_id;
    }

    public void setId(String id) {
        this.emp_id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + emp_id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", ssn='" + ssn + '\'' +
                ", department='" + dept + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
