package com.ejemplo.apiproductos.service.impl;

import com.ejemplo.apiproductos.model.Licencia;
import com.ejemplo.apiproductos.repository.LicenciaRepository;
import com.ejemplo.apiproductos.service.LicenciaService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class LicenciaServiceImpl implements LicenciaService {
    
    private final LicenciaRepository licenciaRepository;

    public LicenciaServiceImpl(LicenciaRepository licenciaRepository) {
        this.licenciaRepository = licenciaRepository;
    }

    @Override
    public List<Licencia> listarLicencias() {
        return licenciaRepository.findAll();
    }

    @Override
    public Licencia guardarLicencia(Licencia licencia) {
        return licenciaRepository.save(licencia);
    }
    
    public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String mensaje) {
            super(mensaje);
        }
    }


    @Override
    public Licencia obtenerLicenciaPorId(Long id) {
        return licenciaRepository.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundException("Licencia no encontrado con id " + id));
    }

    @Override
    public void eliminarLicencia(Long id) {
        licenciaRepository.deleteById(id);
    }
    
    @Override
    public Licencia comprarLicencia(Long id) {
        Licencia licencia = licenciaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Licencia no encontrada"));

        // Generador Key
        String claveGenerada = UUID.randomUUID().toString();
        licencia.setClaveActivacion(claveGenerada);

        if (licencia.getStock() > 0) {
             licencia.setStock(licencia.getStock() - 1);
        } else {
             throw new RuntimeException("No hay licencias disponibles");
        }

        return licenciaRepository.save(licencia);
    }
}
