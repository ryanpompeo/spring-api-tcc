package com.example.mapfood.controller;

import com.example.mapfood.model.Loja;
import com.example.mapfood.repository.LojaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/apiLoja")
@CrossOrigin(origins = "*")
public class LojaController {

    @Autowired
    private LojaRepository lojaRepository;

    @GetMapping("/lojaPorVendedor/{cpf}")
    public ResponseEntity<Loja> buscarPorVendedor(@PathVariable String cpf) {
        Loja loja = lojaRepository.findByVendedorCpf(cpf);
        if (loja == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(loja);
    }

    @GetMapping("/explorar")
    public List<Loja> explorarLojas() {
        return lojaRepository.findTop10ByOrderByIdDesc();
    }


    @PostMapping("/criarLoja")
    public Loja criarLoja(@RequestBody Loja loja) {
        try {
            return lojaRepository.save(loja);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Erro ao criar loja");
        }
    }

    @GetMapping("/buscarLoja")
    public List<Loja>   buscar(@RequestParam String nome, @RequestParam String categoria) {

        List<Loja> lojas = lojaRepository.findAll();

        if (nome != null && !nome.isEmpty()) {
            lojas = lojas.stream()
                    .filter(l -> l.getNome().toLowerCase().contains(nome.toLowerCase()))
                    .toList();
        }

        if (categoria != null && !categoria.isEmpty()) {
            lojas = lojas.stream()
                    .filter(l -> l.getDescricao().toLowerCase().contains(categoria.toLowerCase()))
                    .toList();
        }

        return lojas;
    }

    @PutMapping("/atualizarLoja/{id}")
    public ResponseEntity<Loja> atualizarLoja(@PathVariable int id, @RequestBody Loja dadosAtualizados) {
        Loja loja = lojaRepository.findById(id).orElse(null);
        if (loja == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        loja.setNome(dadosAtualizados.getNome());
        loja.setTelefone(dadosAtualizados.getTelefone());
        loja.setDescricao(dadosAtualizados.getDescricao());
        loja.setLinkIfood(dadosAtualizados.getLinkIfood());
        loja.setCep(dadosAtualizados.getCep());
        loja.setNumero(dadosAtualizados.getNumero());

        return ResponseEntity.ok(lojaRepository.save(loja));
    }
}
