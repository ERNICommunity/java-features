package ch.erni.javafeatures.scoped.values;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServerTest {

    @Test
    void verifyScopedValueWorks() {
        User authenticatedUser = new User("42User42", true);

        var testee = new Server();
        testee.setAuthenticatedUser(authenticatedUser);

        Data response = testee.serve(Request.INSTANCE);

        assertEquals(response.name(), authenticatedUser.name());
        assertEquals(response.id(), Request.INSTANCE.getUuid());
    }
}