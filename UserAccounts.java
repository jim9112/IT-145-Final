public class UserAccounts {
private String userName;
private String md5;
private String password;
private String role;


void setUserName(String name) {
    userName = name;
}
String getUserName() {
    return userName;
}
void setMD5(String inputMD5) {
    md5 = inputMD5;
}
String getMD5() {
    return md5;
}
void setPassword(String inputPassword) {
    password = inputPassword;
}
String getPassword() {
    return password;
}
void setRole(String inputRole) {
    role = inputRole;
}
String getRole() {
    return role;
}


}