package ch.erni.javafeatures.scoped.values;

import java.util.UUID;

public enum Request {
    INSTANCE(UUID.fromString("d4de1399-2692-41c6-bc1f-98fe2202b8ac"));

    private UUID uuid;

    Request(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }
}
