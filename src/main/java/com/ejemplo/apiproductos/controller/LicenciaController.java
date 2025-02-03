package com.ejemplo.apiproductos.controller;

import com.ejemplo.apiproductos.model.Licencia;
import com.ejemplo.apiproductos.service.LicenciaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/licencias")
@CrossOrigin(origins = "*")
public class LicenciaController {

    private final LicenciaService licenciaService;

    public LicenciaController(LicenciaService licenciaService) {
        this.licenciaService = licenciaService;
    }

    @GetMapping
    public List<Licencia> listarLicencias() {
        return licenciaService.listarLicencias();
    }

    @GetMapping("/{id}")
    public Licencia obtenerProducto(@PathVariable Long id) {
        return licenciaService.obtenerLicenciaPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable Long id) {
    	licenciaService.eliminarLicencia(id);
    }
    
    @PostMapping("/{id}/comprar")
    public Licencia comprarLicencia(@PathVariable Long id) {
        return licenciaService.comprarLicencia(id);
    }
    
    @PostMapping
    public Licencia guardarLicencia(@RequestBody Licencia licencia) {
        return licenciaService.guardarLicencia(licencia);
    }
    
}

