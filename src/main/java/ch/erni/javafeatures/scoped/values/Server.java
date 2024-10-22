package ch.erni.javafeatures.scoped.values;

public class Server {

    public void serve(Request request) {
        // ...
        User user = authenticateUser(request);
        new RestAdapter().processRequest(request, user);
        // ...
    }

    private User authenticateUser(Request request) {
        return new User("Servus", true);
    }
}
