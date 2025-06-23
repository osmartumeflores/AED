// BTreeNode.java
package arbol;

import modelo.Producto;
import java.util.*;

/**
 * Nodo del árbol B+ que almacena claves y valores (productos).
 */
public class BTreeNode {
    public boolean hoja;
    public List<String> claves;
    public List<Producto> valores;
    public List<BTreeNode> hijos;
    public int orden;

    public BTreeNode(int orden, boolean hoja) {
        this.orden = orden;
        this.hoja = hoja;
        this.claves = new ArrayList<>();
        this.valores = new ArrayList<>();
        this.hijos = new ArrayList<>();
    }

    /**
     * Imprime recursivamente el contenido del nodo para visualización en consola.
     */
    public void imprimir(String prefijo) {
        System.out.println(prefijo + "Nodo (hoja=" + hoja + ") Claves: " + claves);
        if (!hoja) {
            for (BTreeNode hijo : hijos) {
                hijo.imprimir(prefijo + "  ");
            }
        } else {
            for (int i = 0; i < claves.size(); i++) {
                System.out.println(prefijo + "  → " + claves.get(i) + ": " + valores.get(i));
            }
        }
    }
}