package com.example.mapfood.controller;

import com.example.mapfood.model.Vendedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.mapfood.repository.VendedorRepository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/apiVendedor")
@CrossOrigin(origins = "*")
public class VendedorController {

    @Autowired
    VendedorRepository vendedorRepository;

    /*
    Buscar por CPF
     */
    @GetMapping("/buscarPorCpf/{cpf}")
    public ResponseEntity<Vendedor> buscarCpf(@PathVariable String cpf) {
        Vendedor vendedor = vendedorRepository.findById(cpf).orElse(null);

        if (vendedor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }

        return ResponseEntity.ok(vendedor);
    }

    /*
    Criar Vendedor
     */
    @PostMapping("/criarVendedor")
    public Vendedor criarVendedor(@RequestBody Vendedor vendedor) {
        try {
            return vendedorRepository.save(vendedor);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "CPF já cadastrado");
        }
    }

    /*
    Atualizar Vendedor
     */
    @PutMapping("/atualizarVendedor/{cpf}")
    public ResponseEntity<Vendedor> atualizarVendedor(@PathVariable String cpf, @RequestBody Vendedor dadosAtualizados) {
        try {
            Vendedor vendedor = vendedorRepository.findById(cpf).orElse(null);
            if (vendedor == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            vendedor.setNome(dadosAtualizados.getNome());
            vendedor.setEmail(dadosAtualizados.getEmail());
            vendedor.setSenha(dadosAtualizados.getSenha());

            vendedorRepository.save(vendedor);
            return ResponseEntity.ok(vendedor);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /*
    Deletar Vendedor
     */
    @DeleteMapping("/deletarVendedor/{cpf}")
    public ResponseEntity<Void> deletarVendedor(@PathVariable String cpf) {
        if (!vendedorRepository.existsById(cpf)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        vendedorRepository.deleteById(cpf);
        return ResponseEntity.noContent().build();
    }

    /*
    Verifica se existe outros vendedores cadastrados
     */
    @PostMapping("/login")
    public ResponseEntity<Vendedor> login(@RequestBody Vendedor login) {
        List<Vendedor> lista = vendedorRepository.findAll();
        for (Vendedor v : lista) {
            if (v.getEmail().equals(login.getEmail()) && v.getSenha().equals(login.getSenha())) {
                return ResponseEntity.ok(v);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }



}
