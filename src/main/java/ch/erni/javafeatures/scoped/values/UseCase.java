package ch.erni.javafeatures.scoped.values;

import java.util.UUID;

public class UseCase {

    public void invoke(UUID id, User loggedInUser) {
        // mach irgendwas
        Data data = new Repository().getData(id, loggedInUser);
        // arbeite weiter
    }
}
