package org.example.entidades;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Empleado {
    String nombre;
    String departamento;
    double salario;
    int edad;

    @Override
    public String toString() {
        return String.format("%s (%s) - $%.2f, %d años", nombre, departamento, salario, edad);
    }
}
