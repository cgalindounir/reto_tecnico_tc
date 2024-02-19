package com.reto.tecnico.tc.reto_tecnico_tc.model.pojo;

import java.math.BigDecimal;

public class TipoCambioRespuesta {

    private BigDecimal monto;
    private String origen;
    private String destino;
    private BigDecimal monto_cambiado;
    private BigDecimal tipo_cambio;

    public TipoCambioRespuesta(BigDecimal monto, String origen, String destino, BigDecimal monto_cambiado, BigDecimal tipo_cambio) {
        this.monto = monto;
        this.origen = origen;
        this.destino = destino;
        this.monto_cambiado = monto_cambiado;
        this.tipo_cambio = tipo_cambio;
    }

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

    public BigDecimal getMonto_cambiado() {
        return monto_cambiado;
    }

    public void setMonto_cambiado(BigDecimal monto_cambiado) {
        this.monto_cambiado = monto_cambiado;
    }

    public BigDecimal getTipo_cambio() {
        return tipo_cambio;
    }

    public void setTipo_cambio(BigDecimal tipo_cambio) {
        this.tipo_cambio = tipo_cambio;
    }

    @Override
    public String toString() {
        return "TipoCambioRespuesta{" +
                "monto=" + monto +
                ", origen='" + origen + '\'' +
                ", destino='" + destino + '\'' +
                ", monto_cambiado=" + monto_cambiado +
                ", tipo_cambio=" + tipo_cambio +
                '}';
    }
}
