// Dijkstra.java
package algoritmos;

import grafo.*;
import java.util.*;

/**
 * Implementa el algoritmo de Dijkstra para encontrar la distancia mínima desde un nodo origen.
 */
public class Dijkstra {
    public static Map<Nodo, Double> calcularDistancias(Grafo grafo, String origenId) {
        Map<Nodo, Double> distancias = new HashMap<>();
        Set<Nodo> visitados = new HashSet<>();
        PriorityQueue<Nodo> cola = new PriorityQueue<>(Comparator.comparingDouble(distancias::get));

        for (String id : grafo.obtenerNodos()) {
            Nodo n = grafo.obtenerNodo(id);
            distancias.put(n, Double.POSITIVE_INFINITY);
        }

        Nodo origen = grafo.obtenerNodo(origenId);
        distancias.put(origen, 0.0);
        cola.add(origen);

        while (!cola.isEmpty()) {
            Nodo actual = cola.poll();
            if (visitados.contains(actual)) continue;
            visitados.add(actual);

            for (Arista arista : grafo.obtenerAdyacencias(actual.id)) {
                Nodo vecino = arista.destino;
                double nuevaDist = distancias.get(actual) + arista.peso;
                if (nuevaDist < distancias.get(vecino)) {
                    distancias.put(vecino, nuevaDist);
                    cola.add(vecino);
                }
            }
        }

        return distancias;
    }
}
