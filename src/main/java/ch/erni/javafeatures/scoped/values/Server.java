package ch.erni.javafeatures.scoped.values;

public class Server {

    private User authenticatedUser = new User("Servus", true);

    /// Returnt das Objekt `Data`.
    ///
    /// Dies dient nur der Möglichkeit, einen UnitTest für die korrekte Ausführung des Codes zu schreiben.
    ///
    /// - instanziiere Server im Test
    /// - calle {@link #serve(Request)} ({@link Request} ist ein Enum und kann einfach mit
    ///   `server.serve(Request.INSTANCE);` übergeben werden.
    /// - Prüfe das Ergebnis von {@link Data}. Je nach dem, ob {@link User#isAdmin()} true ist, wird {@link User#name()}
    ///   in {@link Data#name()} zurückgegeben oder eben nicht.
    public Data serve(Request request) {
        // ...
        User user = authenticateUser(request);
        Data data = new RestAdapter().processRequest(request, user);
        // ...
        return data;
    }

    /// Dummy-Methode um Funktionalität zu simulieren.
    /// Für einen Test kann man {@link #setAuthenticatedUser(User)} verwenden, um das Ergebnis beeinflussen zu können.
    User authenticateUser(Request request) {
        return this.authenticatedUser;
    }

    public void setAuthenticatedUser(User authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
    }
}
