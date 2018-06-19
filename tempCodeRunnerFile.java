if (inputUserName.equals(user1.getUserName()) && sb.toString().equals(user1.getMD5())) {
            System.out.println("Access granted");
        } else if (inputUserName.equals(user2.getUserName()) && sb.toString().equals(user2.getMD5())) {
            System.out.println("Access granted");
        } else {
            System.out.println("nope");
        }