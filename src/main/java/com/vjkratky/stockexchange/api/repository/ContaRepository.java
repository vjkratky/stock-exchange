package com.vjkratky.stockexchange.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vjkratky.stockexchange.api.entity.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

}
