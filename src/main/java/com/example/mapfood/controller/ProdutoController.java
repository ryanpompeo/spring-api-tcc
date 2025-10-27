package com.example.mapfood.controller;

import com.example.mapfood.model.Produto;
import com.example.mapfood.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiProduto")
@CrossOrigin(origins = "*")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/todosProdutos")
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }
    @GetMapping("/loja/{lojaId}")
    public List<Produto> listarProdutosPorLoja(@PathVariable int lojaId) {
        return produtoRepository.findByLojaId(lojaId);
    }

    @GetMapping("/buscarPorNome/{nome}")
    public List<Produto> buscarPorNome(@PathVariable String nome) {
        return produtoRepository.findByNomeContainingIgnoreCase(nome);
    }

    @GetMapping("/buscarPorCategoria/{categoria}")
    public List<Produto> buscarPorCategoria(@PathVariable String categoria) {
        return produtoRepository.buscarPorCategoria(categoria);
    }


    @PostMapping("/criarProduto")
    public Produto criarProduto(@RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }

    @PutMapping("/atualizarProduto/{id}")
    public Produto atualizarProduto(@PathVariable int id, @RequestBody Produto dadosAtualizados) {
        Produto produto = produtoRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Produto não encontrado"));

        produto.setNome(dadosAtualizados.getNome());
        produto.setPreco(dadosAtualizados.getPreco());
        produto.setDescricao(dadosAtualizados.getDescricao());
        produto.setCategoria(dadosAtualizados.getCategoria());

        return produtoRepository.save(produto);
    }

    @DeleteMapping("/deletarProduto/{id}")
    public void deletarProduto(@PathVariable int id) {
        produtoRepository.deleteById(id);
    }
}
