package ch.erni.javafeatures.scoped.values;

import java.util.UUID;

public class RestAdapter {

    public void processRequest(Request request, User loggedInUser) {
        // ...
        UUID id = extractId(request);
        new UseCase().invoke(id, loggedInUser);
        // ...
    }

    private UUID extractId(Request request) {
        return request.INSTANCE.getUuid();
    }
}
