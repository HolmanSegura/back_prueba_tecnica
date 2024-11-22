package com.sunbelt.clientemanager.cliente;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "http://localhost:4200")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/consultar")
    public ResponseEntity<?> consultarCliente(@RequestBody @Valid ClienteRequest request) {
        if (request.getTipoDocumento() == null || request.getNumeroDocumento().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El tipo o número de documento no pueden estar vacíos");
        }

        Optional<Cliente> cliente = clienteService.consultarCliente(
                request.getTipoDocumento(), request.getNumeroDocumento());

        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró un cliente con el documento proporcionado.");
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Cliente>> obtenerTodosLosClientes() {
        return ResponseEntity.ok(clienteService.obtenerTodosLosClientes());
    }
}
