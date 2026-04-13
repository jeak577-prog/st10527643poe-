package st10527643;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class LoginTest {

    private Login login;

    @Before
    public void setUp() {
        login = new Login();
    }

    // Username Tests
    
    @Test
    public void testValidUsername() {
        assertTrue(login.checkUserName("ab_cd"));
    }

    @Test
    public void testInvalidUsername_NoUnderscore() {
        assertFalse(login.checkUserName("abcd"));
    }

    @Test
    public void testInvalidUsername_TooLong() {
        assertFalse(login.checkUserName("abc_def"));
    }

    // Password Tests
 
    @Test
    public void testValidPassword() {
        assertTrue(login.checkPasswordComplexity("Passw0rd!"));
    }

    @Test
    public void testInvalidPassword_NoUppercase() {
        assertFalse(login.checkPasswordComplexity("password1!"));
    }

    @Test
    public void testInvalidPassword_NoDigit() {
        assertFalse(login.checkPasswordComplexity("Password!"));
    }

    @Test
    public void testInvalidPassword_NoSpecialChar() {
        assertFalse(login.checkPasswordComplexity("Password1"));
    }

    @Test
    public void testInvalidPassword_TooShort() {
        assertFalse(login.checkPasswordComplexity("P1!a"));
    }

    // Phone Number Tests
  
    @Test
    public void testValidPhoneNumber() {
        assertTrue(login.checkCellPhoneNumber("+27831234567"));
    }

    @Test
    public void testInvalidPhoneNumber_WrongPrefix() {
        assertFalse(login.checkCellPhoneNumber("0831234567"));
    }

    @Test
    public void testInvalidPhoneNumber_WrongLength() {
        assertFalse(login.checkCellPhoneNumber("+2783123456"));
    }

    @Test
    public void testInvalidPhoneNumber_ContainsLetters() {
        assertFalse(login.checkCellPhoneNumber("+27831234A67"));
    }

    // Registration Tests

    @Test
    public void testSuccessfulRegistration() {
        String result = login.registerUser("ab_cd", "Passw0rd!", "+27831234567");
        assertEquals("Username and password successfully captured.", result);
        assertTrue(login.isRegistered());
    }

    @Test
    public void testRegistration_InvalidUsername() {
        String result = login.registerUser("abcd", "Passw0rd!", "+27831234567");
        assertEquals("Username is not correctly formatted.", result);
        assertFalse(login.isRegistered());
    }

    @Test
    public void testRegistration_InvalidPassword() {
        String result = login.registerUser("ab_cd", "password", "+27831234567");
        assertEquals("Password is not correctly formatted.", result);
        assertFalse(login.isRegistered());
    }

    @Test
    public void testRegistration_InvalidPhone() {
        String result = login.registerUser("ab_cd", "Passw0rd!", "0831234567");
        assertEquals("Cell phone number incorrectly formatted.", result);
        assertFalse(login.isRegistered());
    }

    // Login Tests 
  
    @Test
    public void testSuccessfulLogin() {
        login.registerUser("ab_cd", "Passw0rd!", "+27831234567");
        String result = login.returnLoginStatus("ab_cd", "Passw0rd!");
        assertEquals("Welcome ab_cd!", result);
    }

    @Test
    public void testFailedLogin_WrongPassword() {
        login.registerUser("ab_cd", "Passw0rd!", "+27831234567");
        String result = login.returnLoginStatus("ab_cd", "WrongPass1!");
        assertEquals("Invalid username or password. Access denied.", result);
    }

    @Test
    public void testFailedLogin_WrongUsername() {
        login.registerUser("ab_cd", "Passw0rd!", "+27831234567");
        String result = login.returnLoginStatus("wrong", "Passw0rd!");
        assertEquals("Invalid username or password. Access denied.", result);
    }

    @Test
    public void testLogin_NoRegisteredUser() {
        String result = login.returnLoginStatus("ab_cd", "Passw0rd!");
        assertTrue(result.equals("Registration failed") || result.startsWith("Registration failed:"));
        assertFalse(login.isRegistered());
    }
    
    // Additional  tests
    
    @Test
    public void testNullUsername() {
        assertFalse(login.checkUserName(null));
    }
    
    @Test
    public void testNullPassword() {
        assertFalse(login.checkPasswordComplexity(null));
    }
    
    @Test
    public void testNullPhoneNumber() {
        assertFalse(login.checkCellPhoneNumber(null));
    }
}