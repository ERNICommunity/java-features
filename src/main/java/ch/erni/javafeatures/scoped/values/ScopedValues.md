# Scoped Values

## Erklärung
Scoped Values sind eine Form von impliziten Methodenparametern, die es ermöglichen, einen oder mehrere Werte (d. h. 
beliebige Objekte) an eine oder mehrere weit entfernte Methoden zu übergeben, ohne sie als explizite Parameter zu jeder 
Methode in der Aufrufkette hinzufügen zu müssen.

Sie sind nur während der Laufzeit des an die `run(...)`-Methode übergebenen `Runnable`s gültig und werden danach (sofern 
keine weiteren Referenzen auf sie existieren) zur Garbage Collection freigegeben.

Ein Scoped Value ist unveränderlich – er kann nur durch das oben erwähnte Rebinding für einen neuen Scope neu gesetzt 
werden.

Die durch`StructuredTaskScope` erzeugten Kind-Threads haben Zugriff auf den Scoped Value des Eltern-Threads.

### Verwendung:
Scoped Values werden statisch mit einem generischen Parameter **initialisiert**:
```java
public class Foo {
    
    public static ScopedValue<Bar> MY_SCOPED_BAR = ScopedValue.newInstance();
    
    private void doFoo() {
        // hier wird dann der Wert des Scoped Values übergeben
        // und der Scope definiert:
        OtherClass otherClass = new OtherClass();
        Bar bar = initBar();
        
        ScopedValue.where(MY_SCOPED_BAR, bar)
                .run(() -> otherClass.process());
        // Solange der Callstack otherClass.process() existiert und eine Referenz auf unser Scoped Value 
        // existiert, ist der Wert verfügbar
    }
}
```
Und so kann der Wert wieder abgerufen werden:

```java
public class OtherScope {
    private void doSomethingWithMyBar() {
        Foo.MY_SCOPED_BAR.get();
    }
}
```

## Aufgabe

Die Klasse `Server` erzeugt diesen Code-Flow:

![la](./ScopedValueSampleSequence.png)

- Entferne User als Methoden-Parameter aus allen fraglichen Klassen
  - `RestAdapter`
  - `UseCase`
  - `Repository`
- Verwende das **Scoped Value Feature**, in der Klasse `Server`,
damit das Objekt User nicht mehr als Parameter durch die Methoden durchgereicht werden muss.<br>
- verwende das ScopedValue in `Repository`, um wieder an den User zu kommen.
