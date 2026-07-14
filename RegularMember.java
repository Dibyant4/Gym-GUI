/*
   Programming Couesework
 * 24046745
 * Dibyant Bhatta
   L1C6
   */

// RegularMember subclass extending GymMember class
public class RegularMember extends GymMember 
{
    private final int attendanceLimit = 30; 
    private boolean isEligibleForUpgrade;
    private String removalReason = "";
    private String referralSource;
    private String plan;
    private double price;
    // Constructor 
public RegularMember(int id, String name, String location, String dob, String email,
                     String phone, String gender, String membershipStart, String planType,
                     String referralSource) {
    super(id, name, location, dob, email, phone, gender, membershipStart);
    this.plan = plan;
    this.referralSource = referralSource;
    this.plan = planType;
    this.price = getPlanPrice(planType);

}

    //methods for each attribute
    public int getAttendanceLimit() {
        return attendanceLimit;
    }

    public boolean isEligibleForUpgrade() {
        return isEligibleForUpgrade;
    }

    public String getRemovalReason() {
        return removalReason;
    }

    public String getReferralSource() {
        return referralSource;
    }

    public String getPlan() {
        return plan;
    }

    public double getPrice() {
        return price;
    }

    
    //its the abstract method markAttendance() to track attendance
    @Override
public void markAttendance() {
    if (attendance < 30) {
        attendance++;
        loyaltyPoints += 5;   
        System.out.println("Attendance marked. Current attendance: " + attendance);
        System.out.println("Loyalty points increased by 5. Current loyalty points: " + loyaltyPoints);
    } else {
        System.out.println("Attendance limit reached. Cannot mark attendance.");
    }
}

    //this is the method to get the price based on the plan
    public double getPlanPrice(String plan) {
        switch (plan.toLowerCase()) {
            case "basic":
                return 6500;
            case "standard":
                return 12500;
            case "deluxe":
                return 18500;
            default:
                return -1; 
        }
    }
    //this method is to upgrade the plan if it can be 
    public String upgradePlan(String newPlan) {
        if (getPlanPrice(newPlan) == -1) {
            return "Invalid plan selected.";
        }

        if (newPlan.equalsIgnoreCase(plan)) {
            return "You are already subscribed to the " + plan + " plan.";
        }

        if (attendance >= attendanceLimit) {
            plan = newPlan;
            price = getPlanPrice(newPlan);
            isEligibleForUpgrade = true;
            return "Plan upgraded to " + plan + ". New price: " + price;
        } else {
            return "You are not eligible for an upgrade yet.";
        }
    }

    //method to get member details
    public void revertRegularMember(String removalReason) {
        super.resetMember();  
        this.removalReason = removalReason;
        this.isEligibleForUpgrade = false;
        this.plan = "basic";
        this.price = 6500;
        System.out.println("Member reverted. Removal Reason: " + removalReason);
    }

    //Override the display method to show details of RegularMember 
    @Override
    public void displayMemberInfo() {
        super.displayMemberInfo();  
        System.out.println("Plan: " + plan);
        System.out.println("Price: " + price);
        if (!removalReason.isEmpty()) {
            System.out.println("Removal Reason: " + removalReason);
        }
    }
}
