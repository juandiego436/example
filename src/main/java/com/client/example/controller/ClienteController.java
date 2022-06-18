package com.client.example.controller;

import com.client.example.dto.ExceptionResponse;
import com.client.example.dto.ResponseDTO;
import com.client.example.entity.Cliente;
import com.client.example.service.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/clientes")
@Api(value = "commons", produces = "application/json", tags = {"Controlador Servicio Cliente"})
public class ClienteController {

    @Autowired
    ClienteService service;

    @ApiOperation(value = "Realiza la creacion de Cliente", tags = {"Controlador Servicio Cliente"})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = ResponseDTO.class),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Error en el Servidor", response = ExceptionResponse.class)
    })
    @PostMapping(path = "/crea", produces = "application/json", consumes = "application/json")
    public ResponseEntity<ResponseDTO> creaCliente(@RequestBody Cliente cliente) {
        ResponseDTO response = service.create(cliente);
        return ResponseEntity.status(response.getStatus()).body(new ResponseDTO<>(response.getData(), response.getMessage(), response.getStatus()));
    }

    @ApiOperation(value = "Realiza la KPI", tags = {"Controlador Servicio Cliente"})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = ResponseDTO.class),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Error en el Servidor", response = ExceptionResponse.class)
    })
    @GetMapping(path = "/kpi", produces = "application/json")
    public ResponseEntity<ResponseDTO> kpiClientes() {
        ResponseDTO response = service.kpi();
        return ResponseEntity.status(response.getStatus()).body(new ResponseDTO<>(response.getData(), response.getMessage(), response.getStatus()));
    }

    @ApiOperation(value = "Se obtiene la lista de Clientes", tags = {"Controlador Servicio Cliente"})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = ResponseDTO.class),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Error en el Servidor", response = ExceptionResponse.class)
    })
    @GetMapping(path = "/list", produces = "application/json")
    public ResponseEntity<ResponseDTO> list() {
        ResponseDTO response = service.list();
        return ResponseEntity.status(response.getStatus()).body(new ResponseDTO<>(response.getData(), response.getMessage(), response.getStatus()));
    }
}
