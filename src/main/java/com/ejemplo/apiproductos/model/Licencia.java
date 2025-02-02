package com.ejemplo.apiproductos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "licencias")  // renombra la tabla en la BD
public class Licencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombreSoftware;  

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(nullable = false)
    private Double precio;
    
    @Column(nullable = true)
    private String rutaImagen;

    // Si manejas un límite de licencias disponibles, mantén "stock".
    // O renómbralo a algo como "cantidadDisponible" si aplica.
    @Column(nullable = false)
    private Integer stock;

    // Nuevo campo para la clave de activación
    @Column(nullable = true) // se puede generar tras la compra
    private String claveActivacion;
}