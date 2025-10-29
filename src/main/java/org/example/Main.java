package org.example;

import org.example.entidades.*;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        //Cargamos datos
        List<Alumno> alumnos = List.of(
                Alumno.builder()
                        .nombre("Ana")
                        .nota(9.0)
                        .curso("3K10")
                        .build(),
                Alumno.builder()
                        .nombre("Bruno")
                        .nota(6.5)
                        .curso("3K10")
                        .build(),
                Alumno.builder()
                        .nombre("Carla")
                        .nota(7.2)
                        .curso("3K9")
                        .build(),
                Alumno.builder()
                        .nombre("Diego")
                        .nota(8.0)
                        .curso("3K9")
                        .build(),
                Alumno.builder()
                        .nombre("Elena")
                        .nota(4.0)
                        .curso("3K10")
                        .build()
        );

        List<Producto> productos = List.of(
                Producto.builder()
                        .nombre("Teclado")
                        .categoria("Electronica")
                        .precio(150)
                        .stock(20)
                        .build(),
                Producto.builder()
                        .nombre("Mouse")
                        .categoria("Electronica")
                        .precio(8000)
                        .stock(35)
                        .build(),
                Producto.builder()
                        .nombre("Silla")
                        .categoria("Muebles")
                        .precio(72000)
                        .stock(5)
                        .build(),
                Producto.builder()
                        .nombre("Mesa")
                        .categoria("Muebles")
                        .precio(350000)
                        .stock(2)
                        .build()
        );

        List<Libro> libros = List.of(
                Libro.builder()
                        .titulo("Harry Potter")
                        .autor("JK")
                        .paginas(395)
                        .precio(5500)
                        .build(),
                Libro.builder()
                        .titulo("Como cambiarse de carrera")
                        .autor("yo")
                        .paginas(520)
                        .precio(6300)
                        .build(),
                Libro.builder()
                        .titulo("Banana Fish 2")
                        .autor("Akimi chinito")
                        .paginas(464)
                        .precio(7000)
                        .build(),

                Libro.builder()
                        .titulo("Banana Fish")
                        .autor("Akimi chinito")
                        .paginas(600)
                        .precio(2000)
                        .build()
        );

        List<Empleado> empleados = List.of(
                Empleado.builder()
                        .nombre("Ana")
                        .departamento("Contabilidad")
                        .salario(250000)
                        .edad(26)
                        .build(),
                Empleado.builder()
                        .nombre("Bruno")
                        .departamento("Ventas")
                        .salario(190000)
                        .edad(32)
                        .build(),
                Empleado.builder()
                        .nombre("Carla")
                        .departamento("Contabilidad")
                        .salario(320000)
                        .edad(22)
                        .build(),
                Empleado.builder()
                        .nombre("Diego")
                        .departamento("RRHH")
                        .salario(510000)
                        .edad(41)
                        .build()
        );


        System.out.println("-----------1er CASO PRACTICO: ALUMNOS-----------");

        // 1. Aprobados con nota igual o mayor a 7 -> en mayuscula y ordenados
        List<String> alumAprobados = alumnos.stream()
                .filter(a -> a.getNota() >= 7)
                .map(a -> a.getNombre().toUpperCase())
                .sorted()
                .toList();
        System.out.println("-Nombre de alumnos aprobados " + alumAprobados);

        // 2. Promedio de notas
        double promedioGeneral = alumnos.stream()
                .collect(Collectors.averagingDouble(Alumno::getNota));
        System.out.println("-Promedio general de notas: " + promedioGeneral);

        // 3. Agrupar por curso -> usar Collectors.groupingBy()
        Map<String, List<String>> agrupadosPorCurso = alumnos.stream()
                .collect(Collectors.groupingBy(
                        Alumno::getCurso,
                        Collectors.mapping(Alumno::getNombre, Collectors.toList())
                ));
        agrupadosPorCurso.forEach((curso, lista) ->
                System.out.println("-Curso " + curso + "->" + lista));

        // 4. Top 3 por nota
        List<String> top3 = alumnos.stream()
                .sorted(Comparator.comparingDouble(Alumno::getNota).reversed())
                .limit(3)
                .map(Alumno::getNombre)
                .toList();
        System.out.println("-Top 3 por nota: " + top3);

        System.out.println("\n----------- 2do CASO PRACTICO: PRODUCTOS -----------");

        // 1. Precio > 100 ordenado, ordenado de + a -
        List<Producto> carosDesc = productos.stream()
                .filter(p -> p.getPrecio() > 100)
                .sorted(Comparator.comparingDouble(Producto::getPrecio).reversed())
                .toList();
        System.out.println("-Productos con precio mayor a 100, de mayor a menor:");
        carosDesc.forEach(p -> System.out.println("   " + p.getNombre() + " - $" + p.getPrecio()));

        // 2. Stock total por categoría
        Map<String, Integer> stockPorCat = productos.stream()
                .collect(Collectors.groupingBy(
                        Producto::getCategoria,
                        Collectors.summingInt(Producto::getStock)
                ));
        System.out.println("-Stock total por categoria:");
        stockPorCat.forEach((cat, total) -> System.out.println("   " + cat + ": " + total));

        // 3. Cadena de nombre precio separada por ';' -> usar  map + collect(joining).
        String nombrePrecio = productos.stream()
                .map(p -> p.getNombre() + " $" + p.getPrecio())
                .collect(Collectors.joining("; "));
        System.out.println("- Cadena nombre-precio: " + nombrePrecio);

        // 4) Promedio general y por categoría
        double precioProm = productos.stream()
                .collect(Collectors.averagingDouble(Producto::getPrecio));
        System.out.println("-Precio promedio general:" + precioProm);

        Map<String, Double> promPorCat = productos.stream()
                .collect(Collectors.groupingBy(
                        Producto::getCategoria,
                        Collectors.averagingDouble(Producto::getPrecio)
                ));
        System.out.println("- Precio promedio por categoria:");
        promPorCat.forEach((cat, prom) -> System.out.printf("   %s: %.2f%n", cat, prom));

        System.out.println("\n----------- 3er CASO PRACTICO: LIBROS -----------");

        // 1. libros con más de 300 paginas
        List<String> titulos300 = libros.stream()
                .filter(l -> l.getPaginas() > 300)
                .map(Libro::getTitulo)
                .sorted()
                .toList();
        System.out.println("- Titulos con mas de 300 paginas: " + titulos300);

        // 2. Promedio de páginas
        double promPaginas = libros.stream()
                .collect(Collectors.averagingInt(Libro::getPaginas));
        System.out.printf("- Promedio de paginas: %.2f%n", promPaginas);

        //3. Contar libros por autor
        Map<String, Long> librosPorAutor = libros.stream()
                .collect(Collectors.groupingBy(Libro::getAutor, Collectors.counting()));
        System.out.println("- Cantidad de libros por autor:");
        librosPorAutor.forEach((autor, cant) -> System.out.println("   " + autor + ": " + cant));

        // 4. Libro más caro
        Optional<Libro> masCaro = libros.stream()
                .max(Comparator.comparingDouble(Libro::getPrecio));
        System.out.println("- Libro mas caro: " +
                masCaro.map(Libro::getTitulo).orElse("No disponible"));


        System.out.println("\n----------- 4to CASO PRACTICO: EMPLEADOS -----------");

        // 1. Salario > 2000 (desc)
        List<String> salarioMayor = empleados.stream()
                .filter(e -> e.getSalario() > 2000)
                .sorted(Comparator.comparingDouble(Empleado::getSalario).reversed())
                .map(Empleado::getNombre)
                .toList();
        System.out.println("-empleados con salario mayor a 2000: " + salarioMayor);

        // 2. Salario promedio
        double salarioProm = empleados.stream()
                .collect(Collectors.averagingDouble(Empleado::getSalario));
        System.out.println("-Salario promedio: " + salarioProm);

        // 3. Suma de salarios por departamento
        Map<String, Double> sumaPorDepto = empleados.stream()
                .collect(Collectors.groupingBy(
                        Empleado::getDepartamento,
                        Collectors.summingDouble(Empleado::getSalario)
                ));
        System.out.println("- Suma de salarios por departamento:");
        sumaPorDepto.forEach((depa, suma) ->
                System.out.println("   " + depa + ": " + suma));

        // 4. Nombres de los 2 empleados mass jóvenes
        List<String> dosMasJovenes = empleados.stream()
                .sorted(Comparator.comparingInt(Empleado::getEdad))
                .limit(2)
                .map(Empleado::getNombre)
                .toList();
        System.out.println("- Dos empleados mas jovenes: " + dosMasJovenes);

    }
}
