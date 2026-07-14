/*
Programming Couesework
 * 24046745
 * Dibyant Bhatta
L1C6
 */

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.File;

//class deffination starts from here
public class Gym_GUI {

    private static ArrayList<GymMember> memberList = new ArrayList<GymMember>();

    // its the entry point of the application.
    public static void main(String[] args) {
        loadMembersFromFile();
        new WelcomeFrame();
    }

    // Its the first frame that opens when we run the code 
    static class WelcomeFrame extends JFrame {
        public WelcomeFrame() {
            setTitle("Gym");
            setSize(950, 500);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(null);
            setResizable(false);

            JLabel titleLabel = new JLabel("KardioKlub Gym");
            titleLabel.setFont(new Font("SansSerif", Font.BOLD, 32));
            titleLabel.setBounds(320, 30, 400, 40);
            add(titleLabel);

            JSeparator topSeparator = new JSeparator();
            topSeparator.setBounds(20, 80, 900, 2);
            add(topSeparator);

            JLabel addMembersLabel = new JLabel("Add Members:");
            addMembersLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
            addMembersLabel.setBounds(60, 120, 200, 30);
            add(addMembersLabel);

            JButton regularMemberButton = new JButton("Regular Member");
            regularMemberButton.setBounds(180, 180, 200, 50);
            add(regularMemberButton);

            JButton premiumMemberButton = new JButton("Premium Members");
            premiumMemberButton.setBounds(540, 180, 200, 50);
            add(premiumMemberButton);

            JSeparator bottomSeparator = new JSeparator();
            bottomSeparator.setBounds(20, 350, 900, 2);
            add(bottomSeparator);

            regularMemberButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        new Add_RegularMember();
                    }
                });

            premiumMemberButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        new Add_PremiumMember();
                    }
                });

            setVisible(true);
        }
    }

    // Its the second frame that opens when we press the first option in first frame.
    static class Add_RegularMember extends JFrame {
        private JTextField idField, phoneField, locationField, nameField, emailField, referralSourceField;
        private JComboBox<String> planBox;
        private JTextField priceField, membershipTypeField;
        private JRadioButton maleRadio, femaleRadio, othersRadio;
        private ButtonGroup genderGroup;

        public Add_RegularMember() {
            setTitle("Gym");
            setSize(950, 550);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(null);
            setResizable(false);

            JLabel titleLabel = new JLabel("Regular Membership");
            titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
            titleLabel.setBounds(330, 20, 300, 30);
            add(titleLabel);

            JSeparator topSeparator = new JSeparator();
            topSeparator.setBounds(20, 60, 900, 2);
            add(topSeparator);

            addLabelAndTextField("ID:", 50, 80);
            addLabelAndTextField("Phone:", 50, 120);
            addLabelAndTextField("Location:", 50, 160);

            JLabel dobLabel = new JLabel("DOB:");
            dobLabel.setBounds(50, 200, 150, 30);
            add(dobLabel);
            addDateComboBox(200, 200);

            JLabel msdLabel = new JLabel("Membership Start Date:");
            msdLabel.setBounds(50, 240, 200, 30);
            add(msdLabel);
            addDateComboBox(200, 240);

            JLabel planLabel = new JLabel("Membership Plan:");
            planLabel.setBounds(50, 280, 150, 30);
            add(planLabel);

            String[] plans = {"Basic", "Standard", "Deluxe"};
            planBox = new JComboBox<>(plans);
            planBox.setBounds(200, 280, 150, 30);
            add(planBox);

            JLabel priceLabel = new JLabel("Price:");
            priceLabel.setBounds(500, 280, 50, 30);
            add(priceLabel);

            priceField = new JTextField("Rs 6500");
            priceField.setBounds(650, 280, 100, 30);
            priceField.setEditable(false);
            add(priceField);

            addLabelAndTextField("Name:", 500, 80);
            addLabelAndTextField("Email:", 500, 120);

            JLabel genderLabel = new JLabel("Gender:");
            genderLabel.setBounds(500, 160, 100, 30);
            add(genderLabel);

            maleRadio = new JRadioButton("Male");
            femaleRadio = new JRadioButton("Female");
            othersRadio = new JRadioButton("Others");
            maleRadio.setBounds(645, 160, 60, 30);
            femaleRadio.setBounds(710, 160, 80, 30);
            othersRadio.setBounds(790, 160, 80, 30);

            genderGroup = new ButtonGroup();
            genderGroup.add(maleRadio);
            genderGroup.add(femaleRadio);
            genderGroup.add(othersRadio);

            add(maleRadio);
            add(femaleRadio);
            add(othersRadio);

            addLabelAndTextField("Referral Source:", 500, 200);

            JLabel membershipTypeLabel = new JLabel("Membership Plan Type:");
            membershipTypeLabel.setBounds(500, 240, 150, 30);
            add(membershipTypeLabel);

            membershipTypeField = new JTextField("Basic");
            membershipTypeField.setBounds(650, 240, 150, 30);
            membershipTypeField.setEditable(false);
            add(membershipTypeField);

            JSeparator bottomSeparator = new JSeparator();
            bottomSeparator.setBounds(20, 330, 900, 2);
            add(bottomSeparator);

            JButton backButton = new JButton("Back");
            backButton.setBounds(10, 10, 80, 30);
            add(backButton);

            JButton clearButton = new JButton("Clear");
            clearButton.setBounds(50, 350, 130, 30);
            add(clearButton);

            JButton saveButton = new JButton("Save");
            saveButton.setBounds(730, 350, 100, 30);
            add(saveButton);
            saveButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Gym_GUI.saveMembersToFile();
                    }
                });

            JButton readButton = new JButton("Read");
            readButton.setBounds(730, 390, 100, 30);
            add(readButton);
            readButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Gym_GUI.readMembersFromFile();
                    }
                });

            JButton addMemberButton = new JButton("Add Regular Member");
            addMemberButton.setBounds(50, 450, 300, 40);
            add(addMemberButton);

            JButton displayButton = new JButton("Display Regular Members");
            displayButton.setBounds(500, 450, 300, 40);
            add(displayButton);

            planBox.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String selected = (String) planBox.getSelectedItem();
                        switch (selected) {
                            case "Basic":
                                priceField.setText("Rs 6500");
                                membershipTypeField.setText("Basic");
                                break;
                            case "Standard":
                                priceField.setText("Rs 12500");
                                membershipTypeField.setText("Standard");
                                break;
                            case "Deluxe":
                                priceField.setText("Rs 18500");
                                membershipTypeField.setText("Deluxe");
                                break;
                        }
                    }
                });

            backButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        new WelcomeFrame();
                    }
                });

            clearButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        clearFields();
                    }
                });

            addMemberButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        addRegularMember();
                    }
                });

            displayButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        new Show_MemberList("Regular");
                    }
                });

            setVisible(true);
        }

        private void addLabelAndTextField(String label, int x, int y) {
            JLabel jLabel = new JLabel(label);
            jLabel.setBounds(x, y, 150, 30);
            add(jLabel);
            JTextField textField = new JTextField();
            textField.setBounds(x + 150, y, 200, 30);
            add(textField);

            if(label.equals("ID:")) idField = textField;
            else if(label.equals("Phone:")) phoneField = textField;
            else if(label.equals("Location:")) locationField = textField;
            else if(label.equals("Name:")) nameField = textField;
            else if(label.equals("Email:")) emailField = textField;
            else if(label.equals("Referral Source:")) referralSourceField = textField;
        }

        private void addDateComboBox(int x, int y) {
            String[] years = new String[81];
            for (int i = 0; i < 81; i++) years[i] = Integer.toString(1945 + i);

            String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
                    "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

            String[] days = new String[31];
            for (int i = 0; i < 31; i++) days[i] = Integer.toString(i + 1);

            JComboBox<String> yearBox = new JComboBox<>(years);
            JComboBox<String> monthBox = new JComboBox<>(months);
            JComboBox<String> dayBox = new JComboBox<>(days);

            yearBox.setBounds(x, y, 70, 30);
            monthBox.setBounds(x + 75, y, 70, 30);
            dayBox.setBounds(x + 150, y, 60, 30);

            add(yearBox);
            add(monthBox);
            add(dayBox);
        }

        private void clearFields() {
            idField.setText("");
            phoneField.setText("");
            locationField.setText("");
            nameField.setText("");
            emailField.setText("");
            referralSourceField.setText("");
            genderGroup.clearSelection();
            planBox.setSelectedIndex(0);
            priceField.setText("Rs 6500");
            membershipTypeField.setText("Basic");
        }

        private void addRegularMember() {
            if(idField.getText().trim().isEmpty() || phoneField.getText().trim().isEmpty() ||
            locationField.getText().trim().isEmpty() || nameField.getText().trim().isEmpty() ||
            genderGroup.getSelection() == null) {
                JOptionPane.showMessageDialog(this, "Please fill all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Integer.parseInt(idField.getText().trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "ID must be numeric.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String gender = "";
            if(maleRadio.isSelected()) gender = "Male";
            else if(femaleRadio.isSelected()) gender = "Female";
            else if(othersRadio.isSelected()) gender = "Others";

            RegularMember member = new RegularMember(
                    Integer.parseInt(idField.getText().trim()),
                    nameField.getText().trim(),
                    locationField.getText().trim(),
                    "01-Jan-1990",  // Hardcoded DOB, replace with date picker if needed
                    emailField.getText().trim(),
                    phoneField.getText().trim(),
                    gender,
                    "01-May-2025",  // Hardcoded membership start date
                    (String) planBox.getSelectedItem(),
                    referralSourceField.getText().trim()
                );

            for(GymMember m : memberList) {
                if(m.getId() == member.getId()) {
                    JOptionPane.showMessageDialog(this, "Member ID already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            memberList.add(member);
            JOptionPane.showMessageDialog(this, "Regular member added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        }
    }

    // Its the third frame which opens when we press on second option on first page 
    static class Add_PremiumMember extends JFrame {
        private JTextField idField, phoneField, locationField, nameField, emailField, trainerNameField, referralSourceField;
        private JCheckBox trainerCheckBox;
        private JRadioButton maleRadio, femaleRadio, othersRadio;
        private ButtonGroup genderGroup;

        public Add_PremiumMember() {
            setTitle("Gym");
            setSize(950, 550);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(null);
            setResizable(false);

            JLabel titleLabel = new JLabel("Premium Membership");
            titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
            titleLabel.setBounds(320, 20, 350, 30);
            add(titleLabel);

            JSeparator topSeparator = new JSeparator();
            topSeparator.setBounds(20, 60, 900, 2);
            add(topSeparator);

            addLabelAndField("ID:", 50, 80);
            addLabelAndField("Phone:", 50, 120);
            addLabelAndField("Location:", 50, 160);

            JLabel dobLabel = new JLabel("DOB:");
            dobLabel.setBounds(50, 200, 200, 30);
            add(dobLabel);
            addDateComboBox(200, 200);

            JLabel msdLabel = new JLabel("Membership Start Date:");
            msdLabel.setBounds(50, 240, 200, 30);
            add(msdLabel);
            addDateComboBox(200, 240);

            JLabel priceLabel = new JLabel("Premium Plan Price: Rs. 50000");
            priceLabel.setBounds(50, 280, 300, 30);
            priceLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
            add(priceLabel);

            addLabelAndField("Name:", 500, 80);
            addLabelAndField("Email:", 500, 120);

            JLabel genderLabel = new JLabel("Gender:");
            genderLabel.setBounds(500, 160, 100, 30);
            add(genderLabel);

            maleRadio = new JRadioButton("Male");
            femaleRadio = new JRadioButton("Female");
            othersRadio = new JRadioButton("Others");
            maleRadio.setBounds(645, 160, 60, 30);
            femaleRadio.setBounds(710, 160, 80, 30);
            othersRadio.setBounds(790, 160, 80, 30);

            genderGroup = new ButtonGroup();
            genderGroup.add(maleRadio);
            genderGroup.add(femaleRadio);
            genderGroup.add(othersRadio);

            add(maleRadio);
            add(femaleRadio);
            add(othersRadio);

            addLabelAndField("Referral Source:", 500, 200);

            trainerCheckBox = new JCheckBox("Trainer");
            trainerCheckBox.setBounds(500, 240, 80, 30);
            add(trainerCheckBox);

            JLabel trainerNameLabel = new JLabel("Trainer's Name:");
            trainerNameLabel.setBounds(500, 280, 100, 30);
            add(trainerNameLabel);
            trainerNameField = new JTextField();
            trainerNameField.setBounds(650, 280, 200, 30);
            trainerNameField.setEnabled(false);
            add(trainerNameField);

            trainerCheckBox.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        trainerNameField.setEnabled(trainerCheckBox.isSelected());
                        if(!trainerCheckBox.isSelected()) {
                            trainerNameField.setText("");
                        }
                    }
                });

            JSeparator midSeparator = new JSeparator();
            midSeparator.setBounds(20, 330, 900, 2);
            add(midSeparator);

            JButton backButton = new JButton("Back");
            backButton.setBounds(10, 10, 80, 30);
            add(backButton);

            JButton clearButton = new JButton("Clear");
            clearButton.setBounds(50, 350, 130, 30);
            add(clearButton);

            JButton saveButton = new JButton("Save");
            saveButton.setBounds(730, 350, 100, 30);
            add(saveButton);
            saveButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Gym_GUI.saveMembersToFile();
                    }
                });

            JButton readButton = new JButton("Read");
            readButton.setBounds(730, 390, 100, 30);
            add(readButton);
            readButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Gym_GUI.readMembersFromFile();
                    }
                });

            JButton addMemberButton = new JButton("Add Premium Member");
            addMemberButton.setBounds(50, 450, 300, 40);
            add(addMemberButton);

            JButton displayButton = new JButton("Display Premium Members");
            displayButton.setBounds(500, 450, 300, 40);
            add(displayButton);

            backButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        new WelcomeFrame();
                    }
                });

            clearButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        clearFields();
                    }
                });

            addMemberButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        addPremiumMember();
                    }
                });

            displayButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        new Show_MemberList("Premium");
                    }
                });

            setVisible(true);
        }

        private void addLabelAndField(String label, int x, int y) {
            JLabel jLabel = new JLabel(label);
            jLabel.setBounds(x, y, 150, 30);
            add(jLabel);
            JTextField textField = new JTextField();
            textField.setBounds(x + 150, y, 200, 30);
            add(textField);

            if(label.equals("ID:")) idField = textField;
            else if(label.equals("Phone:")) phoneField = textField;
            else if(label.equals("Location:")) locationField = textField;
            else if(label.equals("Name:")) nameField = textField;
            else if(label.equals("Email:")) emailField = textField;
            else if(label.equals("Referral Source:")) referralSourceField = textField;
        }

        private void addDateComboBox(int x, int y) {
            String[] years = new String[81];
            for (int i = 0; i < 81; i++) years[i] = Integer.toString(1945 + i);

            String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
                    "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

            String[] days = new String[31];
            for (int i = 0; i < 31; i++) days[i] = Integer.toString(i + 1);

            JComboBox<String> yearBox = new JComboBox<>(years);
            JComboBox<String> monthBox = new JComboBox<>(months);
            JComboBox<String> dayBox = new JComboBox<>(days);

            yearBox.setBounds(x, y, 70, 30);
            monthBox.setBounds(x + 75, y, 70, 30);
            dayBox.setBounds(x + 150, y, 60, 30);

            add(yearBox);
            add(monthBox);
            add(dayBox);
        }

        private void clearFields() {
            idField.setText("");
            phoneField.setText("");
            locationField.setText("");
            nameField.setText("");
            emailField.setText("");
            referralSourceField.setText("");
            trainerNameField.setText("");
            trainerCheckBox.setSelected(false);
            trainerNameField.setEnabled(false);
            genderGroup.clearSelection();
        }

        private void addPremiumMember() {
            if(idField.getText().trim().isEmpty() || phoneField.getText().trim().isEmpty() ||
            locationField.getText().trim().isEmpty() || nameField.getText().trim().isEmpty() ||
            genderGroup.getSelection() == null) {
                JOptionPane.showMessageDialog(this, "Please fill all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Integer.parseInt(idField.getText().trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "ID must be numeric.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if(trainerCheckBox.isSelected() && trainerNameField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Trainer name must be entered if trainer is selected.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String gender = "";
            if(maleRadio.isSelected()) gender = "Male";
            else if(femaleRadio.isSelected()) gender = "Female";
            else if(othersRadio.isSelected()) gender = "Others";

            PremiumMember member = new PremiumMember(
                    Integer.parseInt(idField.getText().trim()),
                    nameField.getText().trim(),
                    locationField.getText().trim(),
                    emailField.getText().trim(),
                    phoneField.getText().trim(),
                    gender,
                    "01-Jan-1990", // Hardcoded DOB
                    "01-May-2025", // Hardcoded membership start date
                    trainerCheckBox.isSelected() ? trainerNameField.getText().trim() : ""
                );

            for(GymMember m : memberList) {
                if(m.getId() == member.getId()) {
                    JOptionPane.showMessageDialog(this, "Member ID already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            memberList.add(member);
            JOptionPane.showMessageDialog(this, "Premium member added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        }
    }

    // Its the fourth frame which opens when we press on second option on second/third  page 
    static class Show_MemberList extends JFrame {
        private String memberType; // Regular or Premium

        public Show_MemberList(String memberType) {
            this.memberType = memberType;

            setTitle("Gym");
            setSize(900, 500);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(null);
            setResizable(false);

            ((JComponent) getContentPane()).setBorder(new LineBorder(Color.BLACK));

            JButton backButton = new JButton("Back");
            backButton.setBounds(10, 10, 80, 30);
            add(backButton);

            JLabel totalMembersLabel = new JLabel();
            totalMembersLabel.setBounds(10, 50, 200, 30);
            totalMembersLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
            totalMembersLabel.setBorder(new LineBorder(Color.BLACK));
            add(totalMembersLabel);

            JPanel listPanel = new JPanel(null);
            listPanel.setBounds(10, 90, 860, 360);
            listPanel.setBorder(new LineBorder(Color.BLACK));
            add(listPanel);

            final int rowHeight = 40;
            int yOffset = 0;

            ArrayList<GymMember> filteredMembers = new ArrayList<GymMember>();
            for(GymMember m : memberList) {
                if((memberType.equals("Regular") && m instanceof RegularMember) ||
                (memberType.equals("Premium") && m instanceof PremiumMember)) {
                    filteredMembers.add(m);
                }
            }

            totalMembersLabel.setText("Total " + memberType + " Members: " + filteredMembers.size());

            for(final GymMember member : filteredMembers) {
                JPanel row = new JPanel();
                row.setBounds(10, yOffset, 840, rowHeight);
                row.setBorder(new LineBorder(Color.BLACK));
                row.setLayout(null);
                row.setBackground(Color.WHITE);

                String info = "ID: " + member.getId() + " | Name: " + member.getName() + " | Phone: " + member.getPhone()
                    + " | Membership Start Date: " + member.getMembershipStartDate();

                if(member instanceof RegularMember) {
                    info += " | Plan: " + ((RegularMember) member).getPlan() + " | Active Status: "
                    + (((RegularMember) member).isActiveStatus() ? "Active" : "Inactive");
                } else if(member instanceof PremiumMember) {
                    info += " | Plan: Premium | Active Status: "
                    + (((PremiumMember) member).isActiveStatus() ? "Active" : "Inactive");
                }

                JLabel memberInfo = new JLabel(info);
                memberInfo.setBounds(10, 5, 820, 30);
                memberInfo.setFont(new Font("SansSerif", Font.PLAIN, 14));
                row.add(memberInfo);

                row.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            dispose();
                            if(memberType.equals("Regular")) {
                                new Regular_member_details((RegularMember) member);
                            } else {
                                new Premium_member((PremiumMember) member);
                            }
                        }

                        public void mouseEntered(MouseEvent e) {
                            row.setBackground(new Color(220, 240, 255));
                        }

                        public void mouseExited(MouseEvent e) {
                            row.setBackground(Color.WHITE);
                        }
                    });

                listPanel.add(row);
                yOffset += rowHeight + 5;
            }
            backButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        if (memberType.equals("Regular")) {
                            new Add_RegularMember();
                        } else {
                            new Add_PremiumMember();
                        }
                    }
                });

            setVisible(true);
        }
    }

    //This frame opens when we display details of regular member
    static class Regular_member_details extends JFrame {
        private RegularMember member;

        private JLabel idValue, nameValue, phoneValue, emailValue, attendanceValue, genderValue, dobValue, locationValue,
        membershipStartDateValue, loyaltyPointsValue;
        private JCheckBox activeCheckBox;

        public Regular_member_details(RegularMember member) {
            this.member = member;

            setTitle("Gym");
            setSize(950, 600);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(null);
            setResizable(false);

            JButton backButton = new JButton("Back");
            backButton.setBounds(10, 10, 80, 30);
            add(backButton);

            JLabel titleLabel = new JLabel("Regular Membership");
            titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
            titleLabel.setBounds(320, 10, 350, 30);
            add(titleLabel);

            activeCheckBox = new JCheckBox("Active");
            activeCheckBox.setBounds(840, 10, 80, 30);
            activeCheckBox.setSelected(member.isActiveStatus());
            add(activeCheckBox);

            JSeparator separator = new JSeparator();
            separator.setBounds(10, 50, 910, 2);
            add(separator);

            addLabelAndValue("ID:", String.valueOf(member.getId()), 50, 80);
            addLabelAndValue("Name:", member.getName(), 50, 120);
            addLabelAndValue("Phone:", member.getPhone(), 50, 160);
            addLabelAndValue("Email:", member.getEmail(), 50, 200);
            addLabelAndValue("Attendance:", String.valueOf(member.getAttendance()), 50, 240);

            addLabelAndValue("Gender:", member.getGender(), 490, 80);
            addLabelAndValue("DOB:", member.getDOB(), 490, 120);
            addLabelAndValue("Location:", member.getLocation(), 490, 160);
            addLabelAndValue("Membership start date:", member.getMembershipStartDate(), 490, 200);
            addLabelAndValue("Loyalty Points:", String.valueOf(member.getLoyaltyPoints()), 490, 240);

            JButton markAttendanceBtn = new JButton("Mark Attendance");
            markAttendanceBtn.setBounds(50, 290, 850, 35);
            add(markAttendanceBtn);

            JSeparator midSeparator = new JSeparator();
            midSeparator.setBounds(10, 340, 910, 2);
            add(midSeparator);

            JButton upgradeBtn = new JButton("Upgrade Plan");
            upgradeBtn.setBounds(50, 360, 250, 40);
            add(upgradeBtn);

            JButton revertBtn = new JButton("Revert Membership");
            revertBtn.setBounds(340, 360, 250, 40);
            add(revertBtn);

            JButton payDueBtn = new JButton("Pay Due Amount");
            payDueBtn.setBounds(630, 360, 250, 40);
            add(payDueBtn);

            JButton activateBtn = new JButton("Activate Membership");
            activateBtn.setBounds(50, 420, 400, 45);
            add(activateBtn);

            JButton deactivateBtn = new JButton("Deactivate Membership");
            deactivateBtn.setBounds(480, 420, 400, 45);
            add(deactivateBtn);

            JSeparator bottomSeparator = new JSeparator();
            bottomSeparator.setBounds(10, 490, 910, 2);
            add(bottomSeparator);

            //action listeners
            backButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        new Show_MemberList("Regular");
                    }
                });

            activateBtn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        member.activateMembership();
                        activeCheckBox.setSelected(true);
                        JOptionPane.showMessageDialog(null, "Membership activated.");
                    }
                });

            deactivateBtn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        member.deactivateMembership();
                        activeCheckBox.setSelected(false);
                        JOptionPane.showMessageDialog(null, "Membership deactivated.");
                    }
                });

            markAttendanceBtn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (member.isActiveStatus()) {
                            member.markAttendance();
                            attendanceValue.setText(String.valueOf(member.getAttendance()));
                            loyaltyPointsValue.setText(String.valueOf(member.getLoyaltyPoints()));
                        } else {
                            JOptionPane.showMessageDialog(null, "Activate membership before marking attendance.");
                        }
                    }
                });

            upgradeBtn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (member.getAttendance() >= 30) {
                            String[] options = {"Basic", "Standard", "Deluxe"};
                            String newPlan = (String) JOptionPane.showInputDialog(null, "Select new plan:",
                                    "Upgrade Plan", JOptionPane.PLAIN_MESSAGE, null, options, member.getPlan());

                            if (newPlan != null && !newPlan.equalsIgnoreCase(member.getPlan())) {
                                String message = member.upgradePlan(newPlan);
                                JOptionPane.showMessageDialog(null, message);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Attendance less than 30, not eligible for upgrade.");
                        }
                    }
                });

            revertBtn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        member.revertRegularMember("User requested revert");
                        activeCheckBox.setSelected(false);
                        attendanceValue.setText("0");
                        loyaltyPointsValue.setText("0.0");
                        JOptionPane.showMessageDialog(null, "Membership reverted.");
                    }
                });

            payDueBtn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Payment_regular paymentForm = new Payment_regular(member);
                        paymentForm.setVisible(true);
                    }
                });

            setVisible(true);
        }

        private void addLabelAndValue(String label, String value, int x, int y) {
            JLabel lbl = new JLabel(label);
            lbl.setFont(new Font("SansSerif", Font.BOLD, 16));
            lbl.setBounds(x, y, 150, 30);
            add(lbl);

            JLabel valLbl = new JLabel(value);
            valLbl.setFont(new Font("SansSerif", Font.PLAIN, 16));
            valLbl.setBounds(x + 170, y, 250, 30);
            add(valLbl);

            if (label.equals("Attendance:")) attendanceValue = valLbl;
            else if (label.equals("Loyalty Points:")) loyaltyPointsValue = valLbl;
        }
    }

    // this frame is for payment for RegularMember
    static class Payment_regular extends JFrame {
        private RegularMember member;
        private JTextField payAmountField, remainingField;

        public Payment_regular(RegularMember member) {
            this.member = member;

            setTitle("Payment");
            setSize(400, 300);
            setLayout(null);
            setResizable(false);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JLabel titleLabel = new JLabel("Payment");
            titleLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
            titleLabel.setBounds(130, 20, 150, 40);
            add(titleLabel);

            JButton backButton = new JButton("Back");
            backButton.setBounds(10, 10, 80, 30);
            add(backButton);

            JLabel payLabel = new JLabel("Pay Amount:");
            payLabel.setBounds(50, 90, 100, 30);
            add(payLabel);

            payAmountField = new JTextField();
            payAmountField.setBounds(150, 90, 180, 30);
            add(payAmountField);

            JLabel remainingLabel = new JLabel("Remaining:");
            remainingLabel.setBounds(50, 140, 100, 30);
            add(remainingLabel);

            remainingField = new JTextField();
            remainingField.setBounds(150, 140, 180, 30);
            remainingField.setEditable(false);
            remainingField.setText("0");
            add(remainingField);

            JButton doneButton = new JButton("Done");
            doneButton.setBounds(130, 200, 120, 40);
            add(doneButton);

            backButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });

            payAmountField.addKeyListener(new KeyAdapter() {
                    public void keyReleased(KeyEvent e) {
                        try {
                            double paid = Double.parseDouble(payAmountField.getText());
                            double remaining = member.getPrice() - paid;
                            if (remaining < 0) remaining = 0;
                            remainingField.setText(String.valueOf(remaining));
                        } catch (NumberFormatException ex) {
                            remainingField.setText("Invalid input");
                        }
                    }
                });

            doneButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            double amount = Double.parseDouble(payAmountField.getText());
                            if (amount <= 0) {
                                JOptionPane.showMessageDialog(null, "Enter positive amount.");
                                return;
                            }
                            JOptionPane.showMessageDialog(null, "Payment has been recordrd. Remaining: " +
                                (member.getPrice() - amount));
                            dispose();
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Invalid payment amount.");
                        }
                    }
                });

            setVisible(true);
        }
    }

    //This frame is for Premium member display
    static class Premium_member extends JFrame {
        private PremiumMember member;

        private JLabel idValue, nameValue, phoneValue, emailValue, genderValue, dobValue, locationValue,
        membershipStartDateValue, paidAmountValue, discountValue, remainingAmountValue;
        private JCheckBox activeCheckBox;

        public Premium_member(PremiumMember member) {
            this.member = member;

            setTitle("Gym");
            setSize(950, 600);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(null);
            setResizable(false);

            JButton backButton = new JButton("Back");
            backButton.setBounds(30, 20, 100, 30);
            add(backButton);

            JLabel titleLabel = new JLabel("Premium Membership");
            titleLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
            titleLabel.setBounds(320, 20, 400, 40);
            add(titleLabel);

            activeCheckBox = new JCheckBox("Active");
            activeCheckBox.setBounds(830, 30, 80, 30);
            activeCheckBox.setSelected(member.isActiveStatus());
            add(activeCheckBox);

            JSeparator topSeparator = new JSeparator();
            topSeparator.setBounds(20, 70, 900, 2);
            add(topSeparator);

            addLabel("ID:", String.valueOf(member.getId()), 50, 110);
            addLabel("Name:", member.getName(), 50, 150);
            addLabel("Phone:", member.getPhone(), 50, 190);
            addLabel("Email:", member.getEmail(), 50, 230);

            addLabel("Gender:", member.getGender(), 500, 110);
            addLabel("DOB:", member.getDOB(), 500, 150);
            addLabel("Location:", member.getLocation(), 500, 190);
            addLabel("Membership start date:", member.getMembershipStartDate(), 500, 230);

            JSeparator midSeparator = new JSeparator();
            midSeparator.setBounds(20, 280, 900, 2);
            add(midSeparator);

            JButton discountButton = new JButton("Calculate Discount");
            discountButton.setBounds(70, 310, 240, 40);
            add(discountButton);

            JButton revertButton = new JButton("Revert Membership");
            revertButton.setBounds(355, 310, 240, 40);
            add(revertButton);

            JButton payDueButton = new JButton("Pay Due Amount");
            payDueButton.setBounds(640, 310, 240, 40);
            add(payDueButton);

            JButton activateButton = new JButton("Activate Membership");
            activateButton.setBounds(150, 390, 280, 45);
            add(activateButton);

            JButton deactivateButton = new JButton("Deactivate Membership");
            deactivateButton.setBounds(500, 390, 280, 45);
            add(deactivateButton);

            JSeparator bottomSeparator = new JSeparator();
            bottomSeparator.setBounds(20, 480, 900, 2);
            add(bottomSeparator);

            //action listeners
            backButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        new Show_MemberList("Premium");
                    }
                });

            activateButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        member.activateMembership();
                        activeCheckBox.setSelected(true);
                        JOptionPane.showMessageDialog(null, "Membership activated.");
                    }
                });

            deactivateButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        member.deactivateMembership();
                        activeCheckBox.setSelected(false);
                        JOptionPane.showMessageDialog(null, "Membership deactivated.");
                    }
                });

            discountButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        member.calculateDiscount();
                        JOptionPane.showMessageDialog(null, "Discount calculated: " + member.getDiscountAmount());
                    }
                });

            revertButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        member.revertPremiumMember();
                        activeCheckBox.setSelected(false);
                        JOptionPane.showMessageDialog(null, "Membership reverted.");
                    }
                });

            payDueButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Payment_premium paymentForm = new Payment_premium(member);
                        paymentForm.setVisible(true);
                    }
                });

            setVisible(true);
        }

        private void addLabel(String label, String value, int x, int y) {
            JLabel lbl = new JLabel(label);
            lbl.setFont(new Font("SansSerif", Font.BOLD, 16));
            lbl.setBounds(x, y, 250, 30);
            add(lbl);

            JLabel valLbl = new JLabel(value);
            valLbl.setFont(new Font("SansSerif", Font.PLAIN, 16));
            valLbl.setBounds(x + 260, y, 250, 30);
            add(valLbl);
        }
    }

    //payment for premium members.
    static class Payment_premium extends JFrame {
        private PremiumMember member;
        private JTextField payAmountField;
        private JLabel remainingAmountLabel;

        public Payment_premium(PremiumMember member) {
            this.member = member;

            setTitle("Payment");
            setSize(400, 300);
            setLayout(null);
            setResizable(false);

            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JLabel titleLabel = new JLabel("Payment");
            titleLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
            titleLabel.setBounds(130, 20, 150, 40);
            add(titleLabel);

            JButton backButton = new JButton("Back");
            backButton.setBounds(10, 10, 80, 30);
            add(backButton);

            JLabel payLabel = new JLabel("Pay Amount:");
            payLabel.setBounds(50, 90, 100, 30);
            add(payLabel);

            payAmountField = new JTextField();
            payAmountField.setBounds(150, 90, 180, 30);
            add(payAmountField);

            JLabel remainingLabel = new JLabel("Remaining:");
            remainingLabel.setBounds(50, 140, 100, 30);
            add(remainingLabel);

            remainingAmountLabel = new JLabel(String.valueOf(member.getPremiumCharge() - member.getPaidAmount()));
            remainingAmountLabel.setBounds(150, 140, 180, 30);
            add(remainingAmountLabel);

            JButton doneButton = new JButton("Done");
            doneButton.setBounds(130, 200, 120, 40);
            add(doneButton);

            backButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });

            payAmountField.addKeyListener(new KeyAdapter() {
                    public void keyReleased(KeyEvent e) {
                        try {
                            double paid = Double.parseDouble(payAmountField.getText());
                            double remaining = member.getPremiumCharge() - member.getPaidAmount() - paid;
                            if (remaining < 0) remaining = 0;
                            remainingAmountLabel.setText(String.valueOf(remaining));
                        } catch (NumberFormatException ex) {
                            remainingAmountLabel.setText("Invalid input");
                        }
                    }
                });

            doneButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            double amount = Double.parseDouble(payAmountField.getText());
                            if (amount <= 0) {
                                JOptionPane.showMessageDialog(null, "Enter positive amount.");
                                return;
                            }
                            String message = member.payDueAmount(amount);
                            JOptionPane.showMessageDialog(null, message);
                            remainingAmountLabel.setText(String.valueOf(member.getPremiumCharge() - member.getPaidAmount()));
                            dispose();
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Invalid payment amount.");
                        }
                    }
                });

            setVisible(true);
        }
    }

    public static void loadMembersFromFile() {
        File file = new File("MemberDetails.txt");
        if (!file.exists()) return;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine(); //skips header

            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split("\\s{2,}"); 
                if (parts.length < 14) continue;

                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                String location = parts[2];
                String phone = parts[3];
                String email = parts[4];
                String startDate = parts[5];
                String plan = parts[6];

                if (plan.equalsIgnoreCase("Premium")) {
                    double charge = Double.parseDouble(parts[7]);
                    boolean active = parts[10].equalsIgnoreCase("Yes");
                    boolean fullPayment = parts[11].equalsIgnoreCase("Yes");
                    double discount = Double.parseDouble(parts[12]);
                    double paid = Double.parseDouble(parts[13]);

                    PremiumMember p = new PremiumMember(id, name, location, email, phone, "N/A", "N/A", startDate, "");
                    if (active) p.activateMembership();
                    if (fullPayment) p.payDueAmount(charge);
                    else p.payDueAmount(paid);
                    p.calculateDiscount();
                    memberList.add(p);

                } else {
                    double price = Double.parseDouble(parts[7]);
                    int attendance = Integer.parseInt(parts[8]);
                    double points = Double.parseDouble(parts[9]);
                    boolean active = parts[10].equalsIgnoreCase("Yes");

                    RegularMember r = new RegularMember(id, name, location, "N/A", email, phone, "N/A", "N/A", plan, "Restored");
                    for (int i = 0; i < attendance; i++) r.markAttendance();
                    if (active) r.activateMembership();
                    memberList.add(r);
                }
            }
            reader.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error loading members: " + e.getMessage());
        }
    }

    //Save all member data to the txt file
    public static void saveMembersToFile() {
        if (memberList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No members to save.");
            return;
        }
        try {
            PrintWriter writer = new PrintWriter("MemberDetails.txt");
            writer.printf("%-5s %-15s %-15s %-15s %-25s %-20s %-10s %-10s %-10s %-15s %-10s %-15s %-15s %-15s\n",
                "ID", "Name", "Location", "Phone", "Email",
                "Membership Start Date", "Plan", "Price", "Attendance",
                "Loyalty Points", "Active Status", "Full Payment",
                "Discount", "Net Paid");

            for (GymMember m : memberList) {
                if (m instanceof RegularMember) {
                    RegularMember r = (RegularMember) m;
                    writer.printf("%-5d %-15s %-15s %-15s %-25s %-20s %-10s %-10.2f %-10d %-15.2f %-10s %-15s %-15s %-15s\n",
                        r.getId(), r.getName(), r.getLocation(), r.getPhone(), r.getEmail(),
                        r.getMembershipStartDate(), r.getPlan(), r.getPrice(), r.getAttendance(),
                        r.getLoyaltyPoints(), r.isActiveStatus() ? "Yes" : "No", "-", "-", "-");
                } else if (m instanceof PremiumMember) {
                    PremiumMember p = (PremiumMember) m;
                    writer.printf("%-5d %-15s %-15s %-15s %-25s %-20s %-10s %-10.2f %-10s %-15s %-10s %-15s %-15.2f %-15.2f\n",
                        p.getId(), p.getName(), p.getLocation(), p.getPhone(), p.getEmail(),
                        p.getMembershipStartDate(), "Premium", p.getPremiumCharge(), "-", "-",
                        p.isActiveStatus() ? "Yes" : "No",
                        p.isFullPayment() ? "Yes" : "No",
                        p.getDiscountAmount(), p.getPaidAmount());
                }
            }
            writer.close();
            JOptionPane.showMessageDialog(null, "Members saved to MemberDetails.txt");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving file: " + e.getMessage());
        }
    }

    //Read file and and displays saved files.
    public static void readMembersFromFile() {
        File file = new File("MemberDetails.txt");
        if (!file.exists()) {
            JOptionPane.showMessageDialog(null, "No data file found. Save members first.");
            return;
        }

        JFrame frame = new JFrame("Read Members");
        frame.setSize(1000, 600);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);

        JTextArea area = new JTextArea();
        area.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JScrollPane scroll = new JScrollPane(area);
        frame.add(scroll);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                area.append(line + "\n");
            }
            reader.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage());
        }

        frame.setVisible(true);
    }
}

