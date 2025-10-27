package com.example.mapfood.repository;

import com.example.mapfood.model.Loja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LojaRepository extends JpaRepository<Loja, Integer> {

    List<Loja> findByNomeContainingIgnoreCase(String nome);

    @Query("SELECT l FROM Loja l WHERE l.vendedor.cpf = :cpf")
    Loja findByVendedorCpf(@Param("cpf") String cpf);

    List<Loja> findTop10ByOrderByIdDesc();
}
