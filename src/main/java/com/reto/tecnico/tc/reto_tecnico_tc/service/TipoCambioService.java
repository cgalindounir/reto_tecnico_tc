package com.reto.tecnico.tc.reto_tecnico_tc.service;

import java.math.BigDecimal;
import java.util.List;

import com.reto.tecnico.tc.reto_tecnico_tc.model.pojo.TipoCambio;
import com.reto.tecnico.tc.reto_tecnico_tc.model.pojo.TipoCambioDTO;
import com.reto.tecnico.tc.reto_tecnico_tc.model.request.CreateTipoCambioRequest;

public interface TipoCambioService {
	
	List<TipoCambio> getTipoCambio(String origen, String destino, BigDecimal tasa);
	
	TipoCambio getTipoCambio(String tipoCambioId);

	TipoCambio getTipoCambio2(String origen, String destino);

	Boolean removeTipoCambio(String tipoCambioId);
	
	TipoCambio createTipoCambio(CreateTipoCambioRequest request);

	TipoCambio updateTipoCambio(String tipoCambioId, String updateRequest);

	TipoCambio updateTipoCambio(String tipoCambioId, TipoCambioDTO updateRequest);

}
