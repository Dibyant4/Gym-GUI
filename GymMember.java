/*
   Programming Couesework
 * 24046745
 * Dibyant Bhatta
   L1C6
   */

public abstract class GymMember {

    // these are protected attributes
    protected int id;
    protected String name;
    protected String location;
    protected String DOB;
    protected String email;
    protected String phone;
    protected String gender;
    protected String membershipStartDate;
    protected int attendance;
    protected double loyaltyPoints;
    protected boolean activeStatus;

    // Constructor 
    public GymMember(int id, String name, String location, String dob, String email, String phone, String gender, String membershipStartDate) 
    {
        this.id = id;
        this.name = name;
        this.location = location;
        this.DOB = dob;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.membershipStartDate = membershipStartDate;
        //puts attendance and loyalty points to zero
        this.attendance = 0;
        this.loyaltyPoints = 0.0;
        //put membership is inactive by default
        this.activeStatus = false;
    }
    // accessor methods
    public int getId() {
        return id;
    }
    
    public String getDOB() {
        return DOB;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getMembershipStartDate() {
        return membershipStartDate;
    }

    public int getAttendance() {
        return attendance;
    }

    public double getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public boolean isActiveStatus() {
        return activeStatus;
    }

    //the abstract method which marks attendance
    public abstract void markAttendance();
    
    //this method activates membership
    public void activateMembership() {
        if (!activeStatus) {
            activeStatus = true;
            System.out.println("Membership activated.");
        } else {
            System.out.println("Membership is already active.");
        }
    }
    
    // this method deactivates membership
    public void deactivateMembership() {
        if (activeStatus) {
            activeStatus = false;
            System.out.println("Membership deactivated.");
        } else {
            System.out.println("Membership is already inactive.");
        }
    }

    //it reset the member
    public void resetMember() {
        activeStatus = false;
        attendance = 0;
        loyaltyPoints = 0.0;
        System.out.println("Member reset to default values.");
    }

    //this method displays member information
    public void displayMemberInfo() {
        System.out.println("Member ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Location: " + location);
        System.out.println("DOB: " + DOB);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("Gender: " + gender);
        System.out.println("Membership Start Date: " + membershipStartDate);
        System.out.println("Attendance: " + attendance);
        System.out.println("Loyalty Points: " + loyaltyPoints);
        System.out.println("Active Status: " + (activeStatus ? "Active" : "Inactive"));
    }
}
