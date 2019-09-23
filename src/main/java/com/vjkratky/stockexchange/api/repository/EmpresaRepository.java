package com.vjkratky.stockexchange.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vjkratky.stockexchange.api.entity.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

}
