package contentsite;

import java.util.Objects;

public class User implements Comparable<User>{
    private String userName;
    private int password;
    private boolean logIn;
    private boolean premiumMember;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = (userName+password).hashCode();
    }
    public void upgradeForPremium(){
        premiumMember=true;
    }

    public void setLogIn(boolean value) {
        logIn=value;
    }

    @Override
    public int compareTo(User o) {
        return userName.compareTo(o.userName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }

    public String getUserName() {
        return userName;
    }

    public int getPassword() {
        return password;
    }

    public boolean isLogIn() {
        return logIn;
    }

    public boolean isPremiumMember() {
        return premiumMember;
    }
}
