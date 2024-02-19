package com.reto.tecnico.tc.reto_tecnico_tc.data;

import com.reto.tecnico.tc.reto_tecnico_tc.data.utils.SearchStatement;
import com.reto.tecnico.tc.reto_tecnico_tc.model.pojo.TipoCambio;
import com.reto.tecnico.tc.reto_tecnico_tc.data.utils.SearchCriteria;
import com.reto.tecnico.tc.reto_tecnico_tc.data.utils.SearchOperation;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TipoCambioRepository {

    private final TipoCambioJpaRepository repository;

    public List<TipoCambio> getTipoCambio() {
        return repository.findAll();
    }

    public TipoCambio getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public TipoCambio save(TipoCambio tipoCambio) {
        return repository.save(tipoCambio);
    }

    public void delete(TipoCambio tipoCambio) {
        repository.delete(tipoCambio);
    }

    public List<TipoCambio> search(String origen, String destino, BigDecimal tasa) {
        SearchCriteria<TipoCambio> spec = new SearchCriteria<>();
        if (StringUtils.isNotBlank(origen)) {
            spec.add(new SearchStatement("origen", origen, SearchOperation.MATCH));
        }

        if (StringUtils.isNotBlank(destino)) {
            spec.add(new SearchStatement("destino", destino, SearchOperation.EQUAL));
        }

        if(tasa!=null){
            spec.add(new SearchStatement("tasa", tasa, SearchOperation.EQUAL));
        }
        return repository.findAll(spec);
    }

    public TipoCambio search2(String origen, String destino) {
        SearchCriteria<TipoCambio> spec = new SearchCriteria<>();
        if (StringUtils.isNotBlank(origen)) {
            spec.add(new SearchStatement("origen", origen, SearchOperation.MATCH));
        }

        if (StringUtils.isNotBlank(destino)) {
            spec.add(new SearchStatement("destino", destino, SearchOperation.EQUAL));
        }
        return repository.findByOrigenAndDestino(origen,destino);
    }

}
