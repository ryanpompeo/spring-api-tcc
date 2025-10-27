package com.example.mapfood.repository;

import com.example.mapfood.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendedorRepository extends JpaRepository<Vendedor, String> {

    Vendedor findByEmail(String email);

}
