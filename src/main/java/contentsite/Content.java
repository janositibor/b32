package contentsite;

import java.util.List;

public interface Content extends Comparable<Content>{
    boolean isPremiumContent();
    String getTitle();
    List<User> clickedBy();
    void click(User user);

    @Override
    default int compareTo(Content o) {
        return getTitle().compareTo(o.getTitle());
    }
}
