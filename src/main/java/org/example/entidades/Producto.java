package org.example.entidades;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Producto {
    String nombre;
    String categoria;
    double precio;
    int stock;

    @Override
    public String toString() {
        return String.format("%s [%s] - $%.2f (stock %d)", nombre, categoria, precio, stock);
    }
}
