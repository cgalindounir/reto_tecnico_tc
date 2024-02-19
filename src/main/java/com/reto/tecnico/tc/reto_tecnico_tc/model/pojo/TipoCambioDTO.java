package com.reto.tecnico.tc.reto_tecnico_tc.model.pojo;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TipoCambioDTO {
	
	private String origen;
	private String destino;
	private BigDecimal tasa;

}
