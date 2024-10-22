package ch.erni.javafeatures.scoped.values;

import java.util.UUID;

public class Repository {

    public Data getData(UUID id) {
        User loggedInUser = Server.LOGGED_IN_USER.get();
        if (loggedInUser.isAdmin()) {
            return new Data(id, loggedInUser.name());
        } else {
            return new Data(id, null);
        }
    }
}
