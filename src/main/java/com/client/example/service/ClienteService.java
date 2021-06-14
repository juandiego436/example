package com.client.example.service;

import com.client.example.dto.KpiClientesDTO;
import com.client.example.dto.ResponseDTO;
import com.client.example.entity.Cliente;
import com.client.example.repository.ClienteRepository;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final Logger LOG = LoggerFactory.getLogger(ClienteService.class);

    @Autowired
    ClienteRepository repository;

    public ResponseDTO create(Cliente c) {
        try {
            LOG.info("Inicio de Crear Clientes");
            LocalDate ahora = LocalDate.now();
            Period p = Period.between(c.getFechaNacimiento(), ahora);
            if (p.getYears() != c.getEdad()) {
                return new ResponseDTO(null, "La edad no coincide con la fecha de nacimiento", HttpStatus.BAD_REQUEST);
            }
            if (c.getNombre().isEmpty() | c.getApellido().isEmpty()) {
                return new ResponseDTO(null, "Estos datos son requeridos", HttpStatus.BAD_REQUEST);
            }
            var data = repository.save(c);
            LOG.info("Cliente creado : " + data);
            return new ResponseDTO(data, "OK", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseDTO(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    public KpiClientesDTO kpiCliente(){
        try{
            int sumatoria = 0;
            double media = 0;
            double varianza = 0.0;
            double desviacion = 0.0;
            double rango = 0.0;
            List<Cliente> clientes = new ArrayList<>();
            clientes = (List<Cliente>) repository.findAll();
            if(clientes.isEmpty()){
                return null;
            }
            for (Cliente c : clientes){
                sumatoria = sumatoria + c.getEdad();
            }
            LOG.info("Sumatoria es ", sumatoria);
            
            media = sumatoria/clientes.size();
            LOG.info("Media ", media);
            
            rango = Math.pow(clientes.size() - media, 2f);
            LOG.debug("Ragon ", rango);
            
            varianza = varianza + rango;
            LOG.debug("Varianza ", varianza);
            
            varianza = varianza / 11f;
            LOG.debug("Varianza Obtenida", varianza);
            
            desviacion = Math.sqrt(varianza);
            LOG.debug("Desviacion ", desviacion);
            
            return new KpiClientesDTO(clientes,media,desviacion);
        }catch(NoResultException e){
            return null;
        }
    }

    public ResponseDTO kpi() {
        try {
            var data = kpiCliente();
            return new ResponseDTO(data, "Sucess", HttpStatus.OK);
        } catch (NoResultException e) {
            return new ResponseDTO(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseDTO list() {
        try {

            return new ResponseDTO(null, "OK", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseDTO(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
