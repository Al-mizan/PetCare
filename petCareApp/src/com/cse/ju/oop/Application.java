package petCareApp.src.com.cse.ju.oop;
import petCareApp.src.com.cse.ju.oop.views.screens.LoginScreen;
import petCareApp.src.com.cse.ju.oop.views.screens.RegistrationScreen;

public class Application {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(Application::loginScr);
    }

    private static void loginScr() {
        new LoginScreen();
    }

}