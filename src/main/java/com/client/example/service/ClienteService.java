
package com.client.example.service;

import com.client.example.dto.KpiClientesDTO;
import com.client.example.dto.ResponseDTO;
import com.client.example.entity.Cliente;


public interface ClienteService {
    
    ResponseDTO create(Cliente c);
    KpiClientesDTO kpiCliente();
    ResponseDTO kpi();
    ResponseDTO list();
}
