// Nodo.java
package grafo;

/**
 * Representa un nodo del grafo, identificado por un ID único.
 */
public class Nodo {
    public String id;

    public Nodo(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id;
    }
}
