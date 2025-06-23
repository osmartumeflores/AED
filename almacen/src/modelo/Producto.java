// Producto.java
package modelo;

/**
 * Clase que representa un producto con nombre, categoría y número de lote.
 */
public class Producto {
    public String nombre;
    public String categoria;
    public String lote;

    public Producto(String nombre, String categoria, String lote) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.lote = lote;
    }

    @Override
    public String toString() {
        return nombre + " (" + categoria + ", Lote: " + lote + ")";
    }
}
