//package com.cse.ju.oop;
//
//import com.cse.ju.oop.views.screens.HomeScreen;
//
//public class Application {
//    public static void main(String[] args) {
//        HomeScreen homeScreen = new HomeScreen();
//        //Added comment to the project
//        homeScreen.setVisible(true);
//    }
//}

//package com.cse.ju.oop;
//
//import com.cse.ju.oop.views.screens.LoginScreen;
//
//public class Application {
//    public static void main(String[] args) {
//        // Create and display the login screen
//        new LoginScreen();
//    }
//}

package com.cse.ju.oop;

import com.cse.ju.oop.views.screens.LoginScreen;

public class Application {
    public static void main(String[] args) {
        // Create and display the login screen
        javax.swing.SwingUtilities.invokeLater(() -> new LoginScreen());
    }
}