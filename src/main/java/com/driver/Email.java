package com.driver;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId) {
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPassword, String newPassword) {
        //Change password only if the oldPassword is equal to current password and the new password meets all the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character

        String currentPassword = this.password;
        if (currentPassword.equals(oldPassword)) {
            int passLength = newPassword.length();
            boolean isUpper = false;
            boolean isLower = false;
            boolean isNum = false;
            boolean isSpecial = false;

            for (char c : newPassword.toCharArray()) {
                if (c >= 65 && c <= 90) isUpper = true;
                else if (c >= 97 && c <= 127) isLower = true;
                else if (c >= 48 && c <= 57) isNum = true;
                else isSpecial = true;
            }

            if (passLength >= 8 && isUpper && isLower && isNum && isSpecial) {
                this.password = newPassword;
            }
        }
    }
}
