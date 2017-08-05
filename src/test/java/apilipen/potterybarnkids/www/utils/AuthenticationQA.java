package apilipen.potterybarnkids.www.utils;

import org.sikuli.script.*;

/**
 * Created by anna on 12/1/16.
 */
public class AuthenticationQA
    implements Runnable {



        private static String username;
        private static String password;
   //  private static String loginBtn;


        private static String authTitle;
        private static String userNameScreen;
        private static String passWordScreen;
            // private static String loginBtnScreen;

        private static String driverType;

    /* Constractor - 1 */
        public AuthenticationQA(String driverType, String username, String password) {           //        , String loginBtn
            this.driverType = driverType;
            this.username = username;
            this.password = password;
 //   this.loginBtn =loginBtn;

        }


	/* Constractor -2 */
//	public NewTreadLoginSikuli(String username, String password) {
//		this.driverType = getDriverType();
//		this.username = username;
//		this.password = password;
//	}

        @Override
        public void run() {
            // TODO Auto-generated method stub
            try {
                System.out.println("Authentication thread started.");
                login(driverType, username, password);         //  , loginBtn

            } catch (FindFailed | InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                throw new IllegalArgumentException("Can't Login.");
            }

        }

    public static void login(final String driverType, final String username, final String password)
            throws FindFailed, InterruptedException {
        Thread.sleep(5000L);
        System.out.println("Authentication.login started:");
        switch (driverType.toUpperCase()) {

            case "FIREFOX": //"FF":
                // Default FF
                authTitle = "ff/auth_FF.png";
                userNameScreen = "ff/userName_FF.png";
                passWordScreen = "ff/passWord_FF.png";
                break;

            case "CHROME":
                authTitle = "chr/auth_CHR.png";
                userNameScreen = "chr/userName_CHR.png";
                passWordScreen = "chr/passWord_CHR.png";
           //     loginBtnScreen =  "chr/loginButton_CHR.png";
                break;

            default:
                String msg = "Unknown driver type";
                throw new IllegalArgumentException(msg);
        }

        // App.focus("");    ./src/test/java/resourses/img/mac/chr/auth_CHR.png
        Screen screen = new Screen();
        screen.wait("src/main/resources/img/mac/" + authTitle, 10).highlight(1.0f);

        Pattern userNameP = new Pattern("./src/main/resources/img/mac/" + userNameScreen);
        Match userName = screen.wait(userNameP.similar(0.7f), 10);
        userName.highlight(1.0f);

        userName.setTargetOffset(30, 0);
        userName.click();
        userName.paste(username); //   userName.type(username); print

         System.out.println("Authentication.login entered");

        Pattern passWordP = new Pattern("src/main/resources/img/mac/" + passWordScreen);
        Match passWord = screen.wait(passWordP.similar(0.7f), 20);
        passWord.highlight(1.0f);
        passWord.setTargetOffset(30, 0);
        passWord.click();
        passWord.write(password);

         System.out.println("Authentication.password entered");

//        Pattern loginBtnP = new Pattern("./src/main/resources/img/chr/loginButton_CHR.png");
//        Match loginBtnM = screen.wait(loginBtnP.similar(0.7f), 10);
//        loginBtnM.highlight(1.0f);
//        loginBtnM.setTargetOffset(30, 0);
//        loginBtnM.click();

             // System.out.println("Authentication.LoginButton clicked");
//        screen.wait("./src/main/resources/img/chr/loginButton_CHR.png", 10).highlight(1.0f);
//        screen.click("./src/main/resources/img/chr/loginButton_CHR.png");

  passWord.type(Key.ENTER);


            System.out.println("Authentication.LoginButton clicked");

        System.out.println("Authentication.login ended.");
    }
}//end class







