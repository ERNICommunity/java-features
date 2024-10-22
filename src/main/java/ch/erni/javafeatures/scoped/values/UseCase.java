package ch.erni.javafeatures.scoped.values;

import java.util.UUID;

public class UseCase {

    public Data invoke(UUID id) {
        // mach irgendwas
        Data data = new Repository().getData(id);
        // arbeite weiter
        return data;
    }
}
