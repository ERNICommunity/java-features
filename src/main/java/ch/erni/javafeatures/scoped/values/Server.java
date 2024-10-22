package ch.erni.javafeatures.scoped.values;

public class Server {

    public static final ScopedValue<User> LOGGED_IN_USER = ScopedValue.newInstance();
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
        RestAdapter restAdapter = new RestAdapter();
        Data data = ScopedValue.where(LOGGED_IN_USER, user)
                .call(() -> restAdapter.processRequest(request));
        // ...
        return data;
    }

    public Data serveWithoudScopedValueWillCauseException(Request request) {
        User user = authenticateUser(request);
        RestAdapter restAdapter = new RestAdapter();
        return restAdapter.processRequest(request);
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
