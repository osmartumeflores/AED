// ComponentesConexas.java
package algoritmos;

import grafo.*;
import java.util.*;

/**
 * Identifica componentes conexas en el grafo dirigido, considerando solo accesibilidad.
 */
public class ComponentesConexas {
    public static List<Set<String>> obtenerComponentes(Grafo grafo) {
        Set<Nodo> visitados = new HashSet<>();
        List<Set<String>> componentes = new ArrayList<>();

        for (String id : grafo.obtenerNodos()) {
            Nodo nodo = grafo.obtenerNodo(id);
            if (!visitados.contains(nodo)) {
                Set<String> componente = new HashSet<>();
                explorar(grafo, nodo, visitados, componente);
                componentes.add(componente);
            }
        }

        return componentes;
    }

    private static void explorar(Grafo grafo, Nodo nodo, Set<Nodo> visitados, Set<String> componente) {
        Queue<Nodo> cola = new LinkedList<>();
        cola.add(nodo);
        visitados.add(nodo);

        while (!cola.isEmpty()) {
            Nodo actual = cola.poll();
            componente.add(actual.id);

            for (Arista arista : grafo.obtenerAdyacencias(actual.id)) {
                Nodo vecino = arista.destino;
                if (!visitados.contains(vecino)) {
                    visitados.add(vecino);
                    cola.add(vecino);
                }
            }
        }
    }
}
