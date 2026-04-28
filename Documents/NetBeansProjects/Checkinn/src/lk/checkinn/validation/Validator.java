package lk.checkinn.validation;

import javax.swing.JOptionPane;

public class Validator {
    
    public static boolean isEmailValid(String value) {
        if (value.isBlank()) {
            JOptionPane.showMessageDialog(null, "Enter email address", "Email Validation", JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (!value.matches(Validation.EMAIL_VALIDATION.validate())) {
            JOptionPane.showMessageDialog(null, "Enter valid email address", "Email Validation", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
    
    public static boolean isPasswordValid(String value) {
        if (value.isBlank()) {
            JOptionPane.showMessageDialog(null, "Enter Password", "Password Validation", JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (!value.matches(Validation.PASSWORD_VALIDATION.validate())) {
            JOptionPane.showMessageDialog(null,
                    "Password Must Contain The Following Characters. \n"
                    + "At Leasr One Lowecase, \n"
                    + "At Least One Upercase, \n"
                    + "A Special Character, \n"
                    + "At Least One Number, \n"
                    + "The Password Must Be Greater Than 4 And Less Than 8 Characters",
                    "Password Validation",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
}
