// Arista.java
package grafo;

/**
 * Representa una arista dirigida y ponderada entre dos nodos.
 */
public class Arista {
    public Nodo origen;
    public Nodo destino;
    public double peso;

    public Arista(Nodo origen, Nodo destino, double peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }

    @Override
    public String toString() {
        return origen + " -> " + destino + " (" + peso + ")";
    }
}
