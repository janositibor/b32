package contentsite;

import java.util.*;

public class ContentService {
    private Set<User> users=new TreeSet<>();

//    private Set<Content> contents=new TreeSet<>(Comparator.comparing(Content::getTitle));
//    beállítottam, hogy a Content interface kiterjesssze a Comparable interface-t,
//    igy a TreeSet-nek nem kell megadni Comparatort!
    private Set<Content> contents=new TreeSet<>();

    public void registerUser(String username, String password) {
        User user=new User(username,password);
        if(users.contains(user)){
            throw new IllegalArgumentException("Username is already taken: "+username);
        }
        users.add(user);
    }

    public void addContent(Content content) {
        if(contents.contains(content)){
            throw new IllegalArgumentException("Content name is already taken: "+content.getTitle());
        }
        contents.add(content);
    }

    public Set<User> getAllUsers() {
        return users;
    }

    public Set<Content> getAllContent() {
        return contents;
    }

    private User getUserFromSet(User user){
        return users.stream().filter(u->u.equals(user)).findFirst().orElseThrow(()->new IllegalArgumentException(""));
    }

    public void logIn(String username, String password) {
        User user=new User(username,password);
        if(!users.contains(user)){
            throw new IllegalArgumentException("Username is wrong!");
        }
        if(user.getPassword()!=getUserFromSet(user).getPassword()){
            throw new IllegalArgumentException("Password is Invalid!");
        }
        getUserFromSet(user).setLogIn(true);
    }

    public void clickOnContent(User user, Content content) {
        if(!user.isLogIn()){
            throw new IllegalStateException("Log in to watch this content!");
        }
        if(content.isPremiumContent() && !user.isPremiumMember()){
            throw new IllegalStateException("Upgrade for Premium to watch this content!");
        }
        content.click(user);
    }
}
