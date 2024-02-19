package com.reto.tecnico.tc.reto_tecnico_tc.data;

import java.math.BigDecimal;
import java.util.List;

import com.reto.tecnico.tc.reto_tecnico_tc.model.pojo.TipoCambio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

interface TipoCambioJpaRepository extends JpaRepository<TipoCambio, Long>, JpaSpecificationExecutor<TipoCambio> {

	List<TipoCambio> findByOrigen(String origen);

	List<TipoCambio> findByDestino(String destino);

	List<TipoCambio> findByTasa(BigDecimal tasa);

	TipoCambio findByOrigenAndDestino(String origen, String destino);

}
