
package petCareApp.src.com.cse.ju.oop;
import petCareApp.src.com.cse.ju.oop.views.screens.LoginScreen;
import petCareApp.src.com.cse.ju.oop.views.screens.RegistrationScreen;

public class Application {
    public static void main(String[] args) {
        // Create and display the login screen
        javax.swing.SwingUtilities.invokeLater(Application::loginScr);
        javax.swing.SwingUtilities.invokeLater(Application::RegistrationScr);
    }

    private static void loginScr() {
        // if you want to registration show then comment out new LoginScreen();
        new LoginScreen();
    }

    private static void RegistrationScr() {
        new RegistrationScreen();
    }


}