package ejb;

import javax.ejb.Stateful;

@Stateful
public class ConnectedUser {
   private User user;

    public User getUser() {
        return user;
    }

    public void addUser(User user) {
        this.user = user;
    }
}
