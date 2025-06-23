// CicloDetector.java
package algoritmos;

import grafo.*;
import java.util.*;

/**
 * Detecta ciclos en un grafo dirigido mediante DFS.
 */
public class CicloDetector {
    public static boolean tieneCiclo(Grafo grafo) {
        Set<Nodo> visitados = new HashSet<>();
        Set<Nodo> enStack = new HashSet<>();

        for (String id : grafo.obtenerNodos()) {
            Nodo nodo = grafo.obtenerNodo(id);
            if (dfs(grafo, nodo, visitados, enStack)) return true;
        }
        return false;
    }

    private static boolean dfs(Grafo grafo, Nodo actual, Set<Nodo> visitados, Set<Nodo> enStack) {
        if (enStack.contains(actual)) return true;
        if (visitados.contains(actual)) return false;

        visitados.add(actual);
        enStack.add(actual);

        for (Arista arista : grafo.obtenerAdyacencias(actual.id)) {
            if (dfs(grafo, arista.destino, visitados, enStack)) return true;
        }

        enStack.remove(actual);
        return false;
    }
}
