package com.vjkratky.stockexchange.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vjkratky.stockexchange.api.entity.Monitoramento;

@Repository
public interface MonitoramentoRepository extends JpaRepository<Monitoramento, Long> {

}
