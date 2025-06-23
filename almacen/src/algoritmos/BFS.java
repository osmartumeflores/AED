// BFS.java
package algoritmos;

import grafo.*;
import java.util.*;

/**
 * Implementa búsqueda en anchura (BFS) desde un nodo dado.
 */
public class BFS {
    public static List<String> recorrido(Grafo grafo, String inicioId) {
        List<String> resultado = new ArrayList<>();
        Set<Nodo> visitados = new HashSet<>();
        Queue<Nodo> cola = new LinkedList<>();

        Nodo inicio = grafo.obtenerNodo(inicioId);
        cola.add(inicio);
        visitados.add(inicio);

        while (!cola.isEmpty()) {
            Nodo actual = cola.poll();
            resultado.add(actual.id);

            for (Arista arista : grafo.obtenerAdyacencias(actual.id)) {
                Nodo vecino = arista.destino;
                if (!visitados.contains(vecino)) {
                    visitados.add(vecino);
                    cola.add(vecino);
                }
            }
        }

        return resultado;
    }
}
