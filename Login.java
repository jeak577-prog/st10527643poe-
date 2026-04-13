package st10527643;

public class Login {
    private String storedUsername;
    private String storedPassword;
    private String storedPhoneNumber;
    private boolean isRegistered = false;
    private String registrationError = "";
    
    public String registerUser(String username, String password, String cellPhoneNumber) {
        // Validate username
        if (!checkUserName(username)) {
            registrationError = "Username is not correctly formatted.";
            return "Username is not correctly formatted.";
        }
        
        // Validate password
        if (!checkPasswordComplexity(password)) {
            registrationError = "Password is not correctly formatted.";
            return "Password is not correctly formatted.";
        }
        
        // Validate phone number
        if (!checkCellPhoneNumber(cellPhoneNumber)) {
            registrationError = "Cell phone number incorrectly formatted.";
            return "Cell phone number incorrectly formatted.";
        }
        
        // Store user details - only if all validations pass
        this.storedUsername = username;
        this.storedPassword = password;
        this.storedPhoneNumber = cellPhoneNumber;
        this.isRegistered = true;
        registrationError = "";
        
        return "Username and password successfully captured.";
    }
    
    public boolean checkUserName(String username) {
        if (username == null) return false;
        return username.contains("_") && username.length() <= 5;
    }
    
    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) return false;
        
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;
        
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasCapital = true;
            if (Character.isDigit(c)) hasNumber = true;
            if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }
        
        return hasCapital && hasNumber && hasSpecial;
    }
    
    public boolean checkCellPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) return false;
        
        // Check if starts with +27 and has exactly 12 characters total
        if (!phoneNumber.startsWith("+27") || phoneNumber.length() != 12) {
            return false;
        }
        
        // Check remaining characters after +27 are all digits
        for (int i = 3; i < phoneNumber.length(); i++) {
            if (!Character.isDigit(phoneNumber.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    public String returnLoginStatus(String username, String password) {
        if (!isRegistered) {
            if (!registrationError.isEmpty()) {
                return "Registration failed: " + registrationError;
            }
            return "Registration failed";
        }
        
        if (storedUsername != null && storedUsername.equals(username) && 
            storedPassword != null && storedPassword.equals(password)) {
            return "Welcome " + username + "!";
        } else {
            return "Invalid username or password. Access denied.";
        }
    }
    
    public boolean isRegistered() {
        return isRegistered;
    }
}