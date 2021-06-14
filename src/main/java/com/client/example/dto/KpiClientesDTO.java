package com.client.example.dto;

import com.client.example.entity.Cliente;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KpiClientesDTO {
    private List<Cliente> cliente;
    private Double media;
    private Double desviacion;
}
