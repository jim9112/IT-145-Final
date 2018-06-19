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
    public static void userScreen(String currentRole, String adminPrint ){
        if (currentRole.equals("zookeeper")) {
            System.out.println("Hello Zookeepr");
        }  else if (currentRole.equals("admin")) {
            System.out.println(adminPrint);
        } else if (currentRole.equals("veterinarian")) {
            System.out.println("Hello Veterinarian");
        }
    }
    
   public static void main(String[] args) throws Exception {
       Scanner scnr = new Scanner(System.in);
       FileInputStream fileByteStream = null;
       Scanner inFS = null;
        String inputUserName = "";
        String inputPassWord = "";
        String updatedPassWord = "";
        String firstUser = "";
        String userName1 = "";
        String md5 = "";
        String passWord = "";
        String role = "";
        String adminPrint = "";
        Boolean authorizedUser = false;
        String currentRole = "";
        Integer numAttempts = 1;

        // declare users
        UserAccounts user1 = new UserAccounts();
        UserAccounts user2 = new UserAccounts();
        UserAccounts user3 = new UserAccounts();
        UserAccounts user4 = new UserAccounts();
        UserAccounts user5 = new UserAccounts();
        UserAccounts user6 = new UserAccounts();


    //    initiates reader to read credentials file
        fileByteStream = new FileInputStream("credentials.txt");
        inFS = new Scanner(fileByteStream);
        
        
        // build user 1
        userName1 = inFS.next();
        user1.setUserName(userName1);
        md5 = inFS.next();
        user1.setMD5(md5);
        passWord = inFS.next();
        passWord = passWord + " " + inFS.next();
        user1.setPassword(passWord);
        role = inFS.next();
        user1.setRole(role);

        // build user 2
        userName1 = inFS.next();
        user2.setUserName(userName1);
        md5 = inFS.next();
        user2.setMD5(md5);
        passWord = inFS.next();
        passWord = passWord + " " + inFS.next();
        user2.setPassword(passWord);
        role = inFS.next();
        user2.setRole(role);

        // build user 3
        userName1 = inFS.next();
        user3.setUserName(userName1);
        md5 = inFS.next();
        user3.setMD5(md5);
        passWord = inFS.next();
        passWord = passWord + " " + inFS.next();
        user3.setPassword(passWord);
        role = inFS.next();
        user3.setRole(role);

        // build user 4
        userName1 = inFS.next();
        user4.setUserName(userName1);
        md5 = inFS.next();
        user4.setMD5(md5);
        passWord = inFS.next();
        passWord = passWord + " " + inFS.next();
        user4.setPassword(passWord);
        role = inFS.next();
        user4.setRole(role);

        // build user 5
        userName1 = inFS.next();
        user5.setUserName(userName1);
        md5 = inFS.next();
        user5.setMD5(md5);
        passWord = inFS.next();
        user5.setPassword(passWord);
        role = inFS.next();
        user5.setRole(role);
        
        // build user 6
        userName1 = inFS.next();
        user6.setUserName(userName1);
        md5 = inFS.next();
        user6.setMD5(md5);
        passWord = inFS.next();
        user6.setPassword(passWord);
        role = inFS.next();
        user6.setRole(role);

        // close filestream
        inFS.close();
        fileByteStream.close();


        // retrieve admin file
        fileByteStream = new FileInputStream("admin.txt");
        inFS = new Scanner(fileByteStream);
        adminPrint = inFS.nextLine();
        if(inFS.hasNextLine()){
        adminPrint = adminPrint + "\n"+ inFS.nextLine();    
        }
        if(inFS.hasNextLine()){
            adminPrint = adminPrint + "\n"+ inFS.nextLine();    
            }
        inFS.close();
        fileByteStream.close();

        // this statement will run login loop 3 times or when correct credentials are given
        while (authorizedUser.equals(false) && numAttempts <= 3) {
           
            // ask for username and password
            System.out.println("Input UserName");
            inputUserName = scnr.nextLine();
            System.out.println("Input Password");
            inputPassWord = scnr.nextLine();

            // convert to md5
            String original = inputPassWord;  
		    MessageDigest md = MessageDigest.getInstance("MD5");
		    md.update(original.getBytes());
		    byte[] digest = md.digest();
            StringBuffer sb = new StringBuffer();
		    for (byte b : digest) {
			    sb.append(String.format("%02x", b & 0xff));
            }
        
        // Check username and password
            if (inputUserName.equals(user1.getUserName()) && sb.toString().equals(user1.getMD5())) {
                authorizedUser = true;
                currentRole = user1.getRole();
                userScreen(currentRole, adminPrint);
            } else if (inputUserName.equals(user2.getUserName()) && sb.toString().equals(user2.getMD5())) {
                authorizedUser = true;
                currentRole = user2.getRole();
                userScreen(currentRole, adminPrint);
            } else if (inputUserName.equals(user3.getUserName()) && sb.toString().equals(user3.getMD5())) {
                authorizedUser = true;
                currentRole = user3.getRole();
                userScreen(currentRole, adminPrint);
            } else if (inputUserName.equals(user4.getUserName()) && sb.toString().equals(user4.getMD5())) {
                authorizedUser = true;
                currentRole = user4.getRole();
                userScreen(currentRole, adminPrint);
            } else if (inputUserName.equals(user5.getUserName()) && sb.toString().equals(user5.getMD5())) {
                authorizedUser = true;
                currentRole = user5.getRole();
                userScreen(currentRole, adminPrint);
            } else if (inputUserName.equals(user6.getUserName()) && sb.toString().equals(user6.getMD5())) {
                authorizedUser = true;
                currentRole = user6.getRole();
                userScreen(currentRole, adminPrint);
            } else {
                System.out.println("Wrong, try again");
                numAttempts = numAttempts + 1;
            }
        }
       
   }
}
