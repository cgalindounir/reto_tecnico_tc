package com.reto.tecnico.tc.reto_tecnico_tc.service;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.reto.tecnico.tc.reto_tecnico_tc.data.TipoCambioRepository;
import com.reto.tecnico.tc.reto_tecnico_tc.model.pojo.TipoCambio;
import com.reto.tecnico.tc.reto_tecnico_tc.model.pojo.TipoCambioDTO;
import com.reto.tecnico.tc.reto_tecnico_tc.model.request.CreateTipoCambioRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class TipoCambioServiceImpl implements TipoCambioService {

	@Autowired
	private TipoCambioRepository repository;

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public List<TipoCambio> getTipoCambio(String origen, String destino, BigDecimal tasa) {

		if (StringUtils.hasLength(origen) || StringUtils.hasLength(destino) || tasa!=null) {
			return repository.search(origen,destino,tasa);
		}

		List<TipoCambio> tipoCambios = repository.getTipoCambio();
		return tipoCambios.isEmpty() ? null : tipoCambios;
	}

	@Override
	public TipoCambio getTipoCambio(String tipoCambioId) {
		return repository.getById(Long.valueOf(tipoCambioId));
	}

	@Override
	public TipoCambio getTipoCambio2(String origen,String destino) {
		return repository.search2(origen,destino);
	}

	@Override
	public Boolean removeTipoCambio(String tipoCambioId) {

		TipoCambio tipoCambio = repository.getById(Long.valueOf(tipoCambioId));

		if (tipoCambio != null) {
			repository.delete(tipoCambio);
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	@Override
	public TipoCambio createTipoCambio(CreateTipoCambioRequest request) {

		//Otra opcion: Jakarta Validation: https://www.baeldung.com/java-validation
		if (request != null && StringUtils.hasLength(request.getOrigen().trim())
				&& StringUtils.hasLength(request.getDestino().trim())
				&& request.getTasa() != null) {

			TipoCambio tipoCambio = TipoCambio.builder().origen(request.getOrigen()).destino(request.getDestino())
					.tasa(request.getTasa()).build();

			return repository.save(tipoCambio);
		} else {
			return null;
		}
	}

	@Override
	public TipoCambio updateTipoCambio(String tipoCambioId, String request) {

		//PATCH se implementa en este caso mediante Merge Patch: https://datatracker.ietf.org/doc/html/rfc7386
		TipoCambio tipoCambio = repository.getById(Long.valueOf(tipoCambioId));
		if (tipoCambio != null) {
			try {
				JsonMergePatch jsonMergePatch = JsonMergePatch.fromJson(objectMapper.readTree(request));
				JsonNode target = jsonMergePatch.apply(objectMapper.readTree(objectMapper.writeValueAsString(tipoCambio)));
				TipoCambio patched = objectMapper.treeToValue(target, TipoCambio.class);
				repository.save(patched);
				return patched;
			} catch (JsonProcessingException | JsonPatchException e) {
				log.error("Error updating tipo de cambio {}", tipoCambioId, e);
                return null;
            }
        } else {
			return null;
		}
	}

	@Override
	public TipoCambio updateTipoCambio(String tipoCambioId, TipoCambioDTO updateRequest) {
		TipoCambio tipoCambio = repository.getById(Long.valueOf(tipoCambioId));
		if (tipoCambio != null) {
			tipoCambio.update(updateRequest);
			repository.save(tipoCambio);
			return tipoCambio;
		} else {
			return null;
		}
	}

}
