/* @author jameshannan
*/
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
public class Final {

   /**
    * @param args the command line arguments
    */

    // method to select what user sees based on role
    public static void userScreen(String currentRole) throws Exception {
        FileInputStream fileByteStream = null;
        Scanner inFS = null;
        Scanner inSS = null;
        String rolePrintStatement = "";

        // reads credentials file based on current role
        fileByteStream = new FileInputStream(currentRole + ".txt");
        inFS = new Scanner(fileByteStream);
        rolePrintStatement = inFS.nextLine();
        if(inFS.hasNextLine()){
            rolePrintStatement = rolePrintStatement + "\n"+ inFS.nextLine();    
        }
        if(inFS.hasNextLine()){
            rolePrintStatement = rolePrintStatement + "\n"+ inFS.nextLine();    
        }
        inFS.close();
        System.out.println(rolePrintStatement);
        fileByteStream.close();
            userScreenNavigation();
        }  
    

    // method to give users options to logout or quit
    public static void userScreenNavigation() {
        Scanner s = new Scanner(System.in);
        String option = "";
        System.out.println("Options: to Exit enter \"q\", to logout enter \"l\"");
        System.out.println("Enter Option");
        option = s.next();
        if (option.equals("q")){
            System.out.print("\033[H\033[2J");
            System.out.println("Goodbye");
        } else if (option.equals("l")){
           System.out.println("Placeholder");
        }
        s.close();
    }
    
    // Main method
   public static void main(String[] args) throws Exception {
        Scanner scnr = new Scanner(System.in);
        FileInputStream fileByteStream = null;
        Scanner inFS = null;
        Scanner inSS = null;
        String inputUserName = "";
        String inputPassWord = "";
        String userName1 = "";
        String md5 = "";
        String passWord = "";
        String role = "";
        Boolean authorizedUser = false;
        String currentRole = "";
        Integer numAttempts = 3;
        String userLine = "";

        // declare users
        UserAccounts user1 = new UserAccounts();

        // this statement will run login loop 3 times or when correct credentials are given
        while (authorizedUser.equals(false) && numAttempts > 0) {
           
            // ask for username and password
            System.out.print("\033[H\033[2J");
            System.out.println("Welcome, to login enter your username and password when prompted");
            System.out.println("To exit type q into the username field");
            System.out.println("After 3 unsuccessful login attempts, the program will exit automatically");
            System.out.println("You have " + numAttempts + " attempt remaining");
            System.out.println("Input UserName");
            inputUserName = scnr.nextLine();
            if (inputUserName.equals("q")){
                break;
            }
            System.out.println("Input Password");
            inputPassWord = scnr.nextLine();

            // convert password to md5 hash
            String original = inputPassWord;  
		    MessageDigest md = MessageDigest.getInstance("MD5");
		    md.update(original.getBytes());
		    byte[] digest = md.digest();
            StringBuffer sb = new StringBuffer();
		    for (byte b : digest) {
			    sb.append(String.format("%02x", b & 0xff));
            }

        // fileByteStream = new FileInputStream("credentials.txt");
        fileByteStream = new FileInputStream("credentials.txt");
        inFS = new Scanner(fileByteStream);
      
        // testing stuff out
       while (inputUserName != user1.getUserName() && sb.toString() != user1.getMD5()){
            if (inFS.hasNextLine()){
            userLine = inFS.nextLine();
            inSS = new Scanner(userLine);
            userName1 = inSS.next();
            if (inSS.hasNext()){
                md5 = inSS.next();
            }
            if (inSS.hasNext()){
                passWord = inSS.next();
            }
            if (inSS.hasNext()) {
                role = inSS.next();
                if (role.contains("\"")) {
                    passWord = passWord + role;
                } 
            }
            if (inSS.hasNext()) {
                role = inSS.next();
            }
            user1.setUserName(userName1);
            user1.setMD5(md5);
            user1.setPassword(passWord);
            user1.setRole(role);
            if (inputUserName.equals(user1.getUserName()) && sb.toString().equals(user1.getMD5())) {
                authorizedUser = true;
                currentRole = user1.getRole();
                System.out.println(user1.getUserName() + " " + user1.getMD5() + " " + user1.getPassword() + " " + user1.getRole());
                userScreen(currentRole);
            } 
       } else {
           System.out.println("nope");
           numAttempts = numAttempts -1;
           break;

       }
    } 
            // close scanner
            inFS.close();
            fileByteStream.close();
    }       
}
}
