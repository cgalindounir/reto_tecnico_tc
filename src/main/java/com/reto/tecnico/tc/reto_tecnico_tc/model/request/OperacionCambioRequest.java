package com.reto.tecnico.tc.reto_tecnico_tc.model.request;

import java.math.BigDecimal;

public class OperacionCambioRequest {

    private BigDecimal monto;
    private String origen;
    private String destino;

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
}
