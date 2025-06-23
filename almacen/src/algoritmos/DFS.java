// DFS.java
package algoritmos;

import grafo.*;
import java.util.*;

/**
 * Implementa búsqueda en profundidad (DFS) desde un nodo dado.
 */
public class DFS {
    public static List<String> recorrido(Grafo grafo, String inicioId) {
        List<String> resultado = new ArrayList<>();
        Set<Nodo> visitados = new HashSet<>();
        dfsRec(grafo, grafo.obtenerNodo(inicioId), visitados, resultado);
        return resultado;
    }

    private static void dfsRec(Grafo grafo, Nodo actual, Set<Nodo> visitados, List<String> resultado) {
        visitados.add(actual);
        resultado.add(actual.id);

        for (Arista arista : grafo.obtenerAdyacencias(actual.id)) {
            Nodo vecino = arista.destino;
            if (!visitados.contains(vecino)) {
                dfsRec(grafo, vecino, visitados, resultado);
            }
        }
    }
}