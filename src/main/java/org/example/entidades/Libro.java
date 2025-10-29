package org.example.entidades;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Libro {
    String titulo;
    String autor;
    int paginas;
    double precio;

    @Override
    public String toString() {
        return String.format("\"%s\" - %s, %d páginas ($%.2f)", titulo, autor, paginas, precio);
    }
}
