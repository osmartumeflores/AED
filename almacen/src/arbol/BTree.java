// BTree.java
package arbol;

import modelo.Producto;
import java.util.*;

/**
 * Implementación simplificada de un Árbol B+ para clasificar productos.
 */
public class BTree {
    private BTreeNode raiz;
    private int orden;

    public BTree(int orden) {
        this.orden = orden;
        this.raiz = new BTreeNode(orden, true);
    }

    /**
     * Inserta un producto en el árbol con una clave dada.
     */
    public void insertar(String clave, Producto producto) {
        BTreeNode r = raiz;
        if (r.claves.size() == 2 * orden - 1) {
            BTreeNode nuevo = new BTreeNode(orden, false);
            nuevo.hijos.add(r);
            dividirHijo(nuevo, 0, r);
            raiz = nuevo;
            insertarNoLleno(nuevo, clave, producto);
        } else {
            insertarNoLleno(r, clave, producto);
        }
    }

    private void insertarNoLleno(BTreeNode nodo, String clave, Producto producto) {
        int i = nodo.claves.size() - 1;

        if (nodo.hoja) {
            while (i >= 0 && clave.compareTo(nodo.claves.get(i)) < 0) {
                i--;
            }
            nodo.claves.add(i + 1, clave);
            nodo.valores.add(i + 1, producto);
        } else {
            while (i >= 0 && clave.compareTo(nodo.claves.get(i)) < 0) {
                i--;
            }
            i++;
            if (nodo.hijos.get(i).claves.size() == 2 * orden - 1) {
                dividirHijo(nodo, i, nodo.hijos.get(i));
                if (clave.compareTo(nodo.claves.get(i)) > 0) {
                    i++;
                }
            }
            insertarNoLleno(nodo.hijos.get(i), clave, producto);
        }
    }

    private void dividirHijo(BTreeNode padre, int i, BTreeNode y) {
        BTreeNode z = new BTreeNode(orden, y.hoja);
        for (int j = 0; j < orden - 1; j++) {
            z.claves.add(y.claves.remove(orden));
            if (y.hoja) {
                z.valores.add(y.valores.remove(orden));
            }
        }

        if (!y.hoja) {
            for (int j = 0; j < orden; j++) {
                z.hijos.add(y.hijos.remove(orden));
            }
        }

        padre.hijos.add(i + 1, z);
        padre.claves.add(i, y.claves.remove(orden - 1));
    }

    /**
     * Busca un producto por su clave.
     */
    public Producto buscar(String clave) {
        return buscar(raiz, clave);
    }

    private Producto buscar(BTreeNode nodo, String clave) {
        int i = 0;
        while (i < nodo.claves.size() && clave.compareTo(nodo.claves.get(i)) > 0) {
            i++;
        }

        if (i < nodo.claves.size() && clave.equals(nodo.claves.get(i))) {
            return nodo.hoja ? nodo.valores.get(i) : buscar(nodo.hijos.get(i + 1), clave);
        }

        return nodo.hoja ? null : buscar(nodo.hijos.get(i), clave);
    }

    /**
     * Imprime toda la estructura del árbol en consola.
     */
    public void imprimir() {
        raiz.imprimir("");
    }
}
