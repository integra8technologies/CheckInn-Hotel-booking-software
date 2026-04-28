package lk.checkinn.validation;

public enum Validation {
    EMAIL_VALIDATION(){
        @Override
        public String validate() {
            return "^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
            }
    },
    
    PASSWORD_VALIDATION(){
        @Override
        public String validate() {
            return "^.*(?=.{4,8})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$";
        }
        
    };
    
    public String validate(){
        return "";
    }
}
