/*
   Programming Couesework
 * 24046745
 * Dibyant Bhatta
   L1C6
   */

// PremiumMember subclass extending GymMember class
public class PremiumMember extends GymMember 
{
    private final double premiumCharge = 50000.0; 
    private String personalTrainer;
    private boolean isFullPayment;
    private double paidAmount;
    private double discount;
    private String planType;

    //Constructor 
    public PremiumMember(int id, String name, String location, String email, String phone,
                     String gender, String dob, String membershipStart,
                      String personalTrainer) {
    super(id, name, location, email, phone, gender, dob, membershipStart);
    this.planType = planType;
    this.personalTrainer = personalTrainer;
    this.discount = 0.0;
    this.planType = "premium"; 

}

    //methods for each attribute
    public double getPremiumCharge() {
        return premiumCharge;
    }

    public String getPersonalTrainer() {
        return personalTrainer;
    }

    public boolean isFullPayment() {
        return isFullPayment;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public double getDiscountAmount() {
        return discount;
    }

    //it implements the abstract method markAttendance() to fulfill requirements
    @Override
    public void markAttendance() {
        System.out.println("Attendance tracking is not required for Premium members.");
    }

    //The method to pay due amount
    public String payDueAmount(double amount) {
        if (isFullPayment) {
            return "Payment is already complete. No more payments required.";
        }

        //updates the paid amount
        double newPaidAmount = paidAmount + amount;

        if (newPaidAmount > premiumCharge) {
            return "Payment exceeds the total premium charge. No extra payments allowed.";
        }

        paidAmount = newPaidAmount;

        if (paidAmount == premiumCharge) {
            isFullPayment = true;
            return "Payment complete. No more payments due. Remaining amount: 0";
        } else {
            return "Payment received. Remaining amount: " + (premiumCharge - paidAmount);
        }
    }

    //This method calculates the discount
    public void calculateDiscount() {
        if (isFullPayment) {
            discount = premiumCharge * 0.10;
            System.out.println("Discount calculated: " + discount);
        } else {
            discount = 0.0;
            System.out.println("No discount, payment is not complete.");
        }
    }

    public void revertPremiumMember() {
        super.resetMember(); 
        personalTrainer = ""; 
        isFullPayment = false; 
        paidAmount = 0; 
        discount = 0;
        System.out.println("Premium member reverted.");
    }

    //override the display method to show PremiumMember details
    @Override
    public void displayMemberInfo() {
        super.displayMemberInfo();  
        System.out.println("Personal Trainer: " + personalTrainer);
        System.out.println("Paid Amount: " + paidAmount);
        System.out.println("Full Payment: " + (isFullPayment ? "Yes" : "No"));
        System.out.println("Remaining Amount to be paid: " + (premiumCharge - paidAmount));
        if (isFullPayment) {
            System.out.println("Discount Amount: " + discount);
        }
    }
}
