package com.reto.tecnico.tc.reto_tecnico_tc.model.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Table(name = "tipo_cambio")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TipoCambio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "origen", unique = true)
	private String origen;
	
	@Column(name = "destino")
	private String destino;
	
	@Column(name = "tasa")
	private BigDecimal tasa;

	public void update(TipoCambioDTO tipoCambioDTO) {
		this.origen = tipoCambioDTO.getOrigen();
		this.destino = tipoCambioDTO.getDestino();
		this.tasa = tipoCambioDTO.getTasa();

	}

}
