// App.java
import grafo.*;
import arbol.*;
import algoritmos.*;
import modelo.Producto;

import java.util.*;

/**
 * Clase principal del sistema de gestión de inventario. Proporciona un menú interactivo en consola
 * para operar sobre un grafo de ubicaciones y árboles B+ por cada ubicación.
 */
public class App {
    private static Grafo grafo = new Grafo();
    private static Map<String, BTree> arbolesPorUbicacion = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean salir = false;

        while (!salir) {
            System.out.println("\n=== SISTEMA DE GESTIÓN DE INVENTARIOS ===");
            System.out.println("1. Gestionar ubicaciones y rutas (grafo)");
            System.out.println("2. Gestionar productos por ubicación (Árbol B+)");
            System.out.println("3. Ejecutar algoritmos de optimización");
            System.out.println("4. Simular escenario");
            System.out.println("5. Visualizar árbol y grafo (texto)");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> menuGrafo();
                case 2 -> menuArbol();
                case 3 -> menuAlgoritmos();
                case 4 -> menuSimulacion();
                case 5 -> mostrarEstructuras();
                case 0 -> salir = true;
                default -> System.out.println("Opción no válida.");
            }
        }

        System.out.println("¡Gracias por usar el sistema!");
    }

    // Submenú para gestionar nodos y aristas del grafo
    private static void menuGrafo() {
        System.out.println("\n--- Gestión del Grafo ---");
        System.out.println("1. Agregar ubicación");
        System.out.println("2. Eliminar ubicación");
        System.out.println("3. Agregar ruta");
        System.out.println("4. Eliminar ruta");
        System.out.print("Opción: ");
        int op = scanner.nextInt(); scanner.nextLine();

        switch (op) {
            case 1 -> {
                System.out.print("ID de nueva ubicación: ");
                String id = scanner.nextLine();
                grafo.agregarNodo(id);
                arbolesPorUbicacion.put(id, new BTree(3));
            }
            case 2 -> {
                System.out.print("ID a eliminar: ");
                String id = scanner.nextLine();
                grafo.eliminarNodo(id);
                arbolesPorUbicacion.remove(id);
            }
            case 3 -> {
                System.out.print("Origen: ");
                String o = scanner.nextLine();
                System.out.print("Destino: ");
                String d = scanner.nextLine();
                System.out.print("Peso: ");
                double p = scanner.nextDouble(); scanner.nextLine();
                grafo.agregarArista(o, d, p);
            }
            case 4 -> {
                System.out.print("Origen: ");
                String o = scanner.nextLine();
                System.out.print("Destino: ");
                String d = scanner.nextLine();
                grafo.eliminarArista(o, d);
            }
            default -> System.out.println("Opción inválida.");
        }
    }

    // Submenú para insertar, buscar e imprimir árbol B+ por ubicación
    private static void menuArbol() {
        System.out.println("\n--- Gestión de Productos ---");
        System.out.print("Ubicación: ");
        String ubicacion = scanner.nextLine();
        BTree arbol = arbolesPorUbicacion.get(ubicacion);

        if (arbol == null) {
            System.out.println("Ubicación no encontrada.");
            return;
        }

        System.out.println("1. Insertar producto");
        System.out.println("2. Buscar producto");
        System.out.println("3. Ver estructura");
        System.out.print("Opción: ");
        int op = scanner.nextInt(); scanner.nextLine();

        switch (op) {
            case 1 -> {
                System.out.print("Nombre: ");
                String nombre = scanner.nextLine();
                System.out.print("Categoría: ");
                String cat = scanner.nextLine();
                System.out.print("Lote: ");
                String lote = scanner.nextLine();
                Producto p = new Producto(nombre, cat, lote);
                arbol.insertar(nombre, p);
            }
            case 2 -> {
                System.out.print("Nombre a buscar: ");
                String clave = scanner.nextLine();
                Producto p = arbol.buscar(clave);
                System.out.println(p != null ? "Producto encontrado: " + p : "No encontrado.");
            }
            case 3 -> arbol.imprimir();
            default -> System.out.println("Opción inválida.");
        }
    }

    // Submenú para ejecutar algoritmos como Dijkstra, BFS, DFS, etc.
    private static void menuAlgoritmos() {
        System.out.println("\n--- Algoritmos ---");
        System.out.println("1. Ruta óptima (Dijkstra)");
        System.out.println("2. Búsqueda BFS");
        System.out.println("3. Búsqueda DFS");
        System.out.println("4. Detección de ciclos");
        System.out.println("5. Componentes conexas");
        System.out.print("Opción: ");
        int op = scanner.nextInt(); scanner.nextLine();

        switch (op) {
            case 1 -> {
                System.out.print("Ubicación de origen: ");
                String origen = scanner.nextLine();
                Map<Nodo, Double> dist = Dijkstra.calcularDistancias(grafo, origen);
                dist.forEach((n, d) -> System.out.println("→ " + n.id + ": " + d));
            }
            case 2 -> {
                System.out.print("Inicio BFS: ");
                String i = scanner.nextLine();
                System.out.println(BFS.recorrido(grafo, i));
            }
            case 3 -> {
                System.out.print("Inicio DFS: ");
                String i = scanner.nextLine();
                System.out.println(DFS.recorrido(grafo, i));
            }
            case 4 -> System.out.println("¿Ciclo? " + CicloDetector.tieneCiclo(grafo));
            case 5 -> {
                List<Set<String>> comp = ComponentesConexas.obtenerComponentes(grafo);
                int idx = 1;
                for (Set<String> c : comp) System.out.println("Componente " + idx++ + ": " + c);
            }
            default -> System.out.println("Opción inválida.");
        }
    }

    // Submenú de simulaciones (cierre de rutas y crecimiento del inventario)
    private static void menuSimulacion() {
        System.out.println("\n--- Simulación ---");
        System.out.println("1. Cierre temporal (eliminar ruta)");
        System.out.println("2. Aumento de inventario");
        System.out.print("Opción: ");
        int op = scanner.nextInt(); scanner.nextLine();

        switch (op) {
            case 1 -> {
                System.out.print("Ruta origen: ");
                String o = scanner.nextLine();
                System.out.print("Ruta destino: ");
                String d = scanner.nextLine();
                grafo.eliminarArista(o, d);
                System.out.println("Ruta eliminada.");
            }
            case 2 -> {
                System.out.print("Ubicación: ");
                String u = scanner.nextLine();
                BTree arbol = arbolesPorUbicacion.get(u);
                if (arbol != null) {
                    for (int i = 1; i <= 5; i++) {
                        arbol.insertar("Prod" + i, new Producto("Prod" + i, "Auto", "L" + i));
                    }
                    System.out.println("Inventario simulado insertado.");
                }
            }
            default -> System.out.println("Opción inválida.");
        }
    }

    // Imprime nodos, rutas y árboles para visualización en consola
    private static void mostrarEstructuras() {
        System.out.println("\n--- Visualización texto ---");
        System.out.println("Ubicaciones:");
        for (String id : grafo.obtenerNodos()) System.out.println(" • " + id);

        System.out.println("\nRutas:");
        for (String id : grafo.obtenerNodos()) {
            List<Arista> ady = grafo.obtenerAdyacencias(id);
            for (Arista a : ady) System.out.println(" - " + a);
        }

        System.out.println("\nÁrboles B+ por ubicación:");
        for (String id : arbolesPorUbicacion.keySet()) {
            System.out.println("Ubicación: " + id);
            arbolesPorUbicacion.get(id).imprimir();
        }
    }
}
