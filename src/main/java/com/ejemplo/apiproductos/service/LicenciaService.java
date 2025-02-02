package com.ejemplo.apiproductos.service;

import com.ejemplo.apiproductos.model.Licencia;
import java.util.List;

public interface LicenciaService {
    Licencia guardarLicencia(Licencia producto);
    Licencia obtenerLicenciaPorId(Long id);
    void eliminarLicencia(Long id);
	List<Licencia> listarLicencias();
	Licencia comprarLicencia(Long id);
}
