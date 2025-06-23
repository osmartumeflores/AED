package grafo;

import java.util.*;

/**
 * Clase que representa un grafo dirigido y ponderado usando listas de adyacencia.
 */
public class Grafo {
    private Map<String, Nodo> nodos = new HashMap<>();
    private Map<Nodo, List<Arista>> adyacencias = new HashMap<>();

    public void agregarNodo(String id) {
        Nodo nodo = new Nodo(id);
        nodos.put(id, nodo);
        adyacencias.put(nodo, new ArrayList<>());
    }

    public void eliminarNodo(String id) {
        Nodo nodo = nodos.get(id);
        adyacencias.remove(nodo);
        for (List<Arista> lista : adyacencias.values()) {
            lista.removeIf(a -> a.destino.equals(nodo));
        }
        nodos.remove(id);
    }

    public void agregarArista(String origenId, String destinoId, double peso) {
        Nodo origen = nodos.get(origenId);
        Nodo destino = nodos.get(destinoId);
        adyacencias.get(origen).add(new Arista(origen, destino, peso));
    }

    public void eliminarArista(String origenId, String destinoId) {
        Nodo origen = nodos.get(origenId);
        Nodo destino = nodos.get(destinoId);
        adyacencias.get(origen).removeIf(a -> a.destino.equals(destino));
    }

    public Set<String> obtenerNodos() {
        return nodos.keySet();
    }

    public List<Arista> obtenerAdyacencias(String nodoId) {
        return adyacencias.get(nodos.get(nodoId));
    }

    public Map<Nodo, List<Arista>> obtenerGrafo() {
        return adyacencias;
    }

    public Nodo obtenerNodo(String id) {
        return nodos.get(id);
    }

    /**
     * Devuelve una lista de nodos que no tienen aristas salientes ni entrantes.
     */
    public List<Nodo> obtenerNodosAislados() {
        List<Nodo> aislados = new ArrayList<>();
        for (Nodo nodo : nodos.values()) {
            boolean tieneSalientes = !adyacencias.get(nodo).isEmpty();
            boolean tieneEntrantes = false;

            for (Map.Entry<Nodo, List<Arista>> entry : adyacencias.entrySet()) {
                for (Arista arista : entry.getValue()) {
                    if (arista.destino.equals(nodo)) {
                        tieneEntrantes = true;
                        break;
                    }
                }
                if (tieneEntrantes) break;
            }

            if (!tieneSalientes && !tieneEntrantes) {
                aislados.add(nodo);
            }
        }
        return aislados;
    }
}
