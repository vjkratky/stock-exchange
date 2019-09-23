package com.vjkratky.stockexchange.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vjkratky.stockexchange.api.entity.Negociacao;

@Repository
public interface NegociacaoRepository extends JpaRepository<Negociacao, Long> {

}
