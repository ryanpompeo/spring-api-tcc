package com.example.mapfood.repository;

import com.example.mapfood.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    List<Produto> findByLojaId(int lojaId);
    List<Produto> findByNomeContainingIgnoreCase(String nome);


    @Query("SELECT p FROM Produto p WHERE p.categoria LIKE %?1%")
    List<Produto> buscarPorCategoria(String categoria);
}
