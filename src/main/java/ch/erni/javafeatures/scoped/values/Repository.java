package ch.erni.javafeatures.scoped.values;

import java.util.UUID;

public class Repository {

    public Data getData(UUID id, User loggedInUser) {
        if (loggedInUser.isAdmin()) {
            return new Data(id, loggedInUser.name());
        } else {
            return new Data(id, null);
        }
    }
}
