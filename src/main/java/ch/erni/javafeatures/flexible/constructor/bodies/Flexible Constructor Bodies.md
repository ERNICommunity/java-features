# Flexible Constructor Bodies

<!-- TOC -->
* [Flexible Constructor Bodies](#flexible-constructor-bodies)
  * [Erklärung](#erklärung)
  * [Aufgabe](#aufgabe)
  * [Quellen](#quellen)
<!-- TOC -->

## Erklärung
Mit Flexible Constructor Bodies ist es möglich innerhalbe des Constructors **Code vor dem `super()` bzw. `this()` 
Statement auszuführen**.<br>
Der Constructor teilt sich in
- Prolog
- Eplilog

auf.

Im Prolog darf der Code Felder initialisieren, aber nicht lesend auf Felder der Klasse zugreifen und keine nicht-
statische Methoden der Klasse aufrufen. Er darf außerdem keine Instanzen von nicht-statischen inneren Klassen erzeugen, 
da diese dann eine Referenz auf das potentiell uninitialisierte Elternobjekt haben würden.

Der Prolog des Konstruktors einer inneren Klasse darf hingegen uneingeschränkt auf Felder und Methoden der äußeren 
Klasse zugreifen.

**Records und Enums** können zwar keine Elternklasse haben, deren Konstruktoren können allerdings mit this(...)
alternative Konstruktoren aufrufen.<br>
Auch davor darf nun Code, der den oben genannten Einschränkungen standhält, ausgeführt werden.

## Aufgabe
Führt man aktuell die Main-Methode von `Child aus`, erhält man folgendes:
> a = 1
> 
> b = 0

Der Grund ist, dass `printMe()` in Parent ausgeführt wird, bevor `b` im Child-Constructor initialisiert werden konnte.

Passe den Konstruktor folgendermassen an:
- stelle vor dem Aufruf von `super()` sicher, dass die Werte positiv sind
- Stelle sicher, dass beim Ausführen der Main-Methode folgendes auf der Konsole ausgegeben wird (**ACHRUNT:** Der Aufruf 
  von `printMe()` soll im Parent-Constructor bestehen bleiben):
> a = 1
>
> b = 2

## Quellen
- [happycoders Java 23 Features](https://www.happycoders.eu/de/java/java-23-features/#Scoped_Values_Third_Preview_-_JEP_481)
- [happycoders Flexible Constructor Bodies](https://www.happycoders.eu/de/java/flexible-constructor-bodies/)