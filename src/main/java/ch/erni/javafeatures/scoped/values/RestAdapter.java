package ch.erni.javafeatures.scoped.values;

import java.util.UUID;

public class RestAdapter {

    public Data processRequest(Request request) {
        // ...
        UUID id = extractId(request);
        Data invoke = new UseCase().invoke(id);
        // ...
        return invoke;
    }

    private UUID extractId(Request request) {
        return request.INSTANCE.getUuid();
    }
}
