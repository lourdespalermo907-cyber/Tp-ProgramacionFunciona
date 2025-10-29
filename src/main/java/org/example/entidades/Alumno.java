package org.example.entidades;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Alumno {
    String nombre;
    double nota;
    String curso;

    @Override
    public String toString() {
        return String.format("%s (curso %s, nota %.1f)", nombre, curso, nota);
    }
}
