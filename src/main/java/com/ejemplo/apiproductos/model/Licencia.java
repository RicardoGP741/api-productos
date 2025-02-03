package com.ejemplo.apiproductos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "licencias") 
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

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = true) 
    private String claveActivacion;
}