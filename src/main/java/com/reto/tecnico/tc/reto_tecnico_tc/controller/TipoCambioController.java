package com.reto.tecnico.tc.reto_tecnico_tc.controller;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import com.reto.tecnico.tc.reto_tecnico_tc.model.pojo.TipoCambio;
import com.reto.tecnico.tc.reto_tecnico_tc.model.pojo.TipoCambioDTO;
import com.reto.tecnico.tc.reto_tecnico_tc.model.pojo.TipoCambioRespuesta;
import com.reto.tecnico.tc.reto_tecnico_tc.model.request.CreateTipoCambioRequest;
import com.reto.tecnico.tc.reto_tecnico_tc.model.request.OperacionCambioRequest;
import com.reto.tecnico.tc.reto_tecnico_tc.service.TipoCambioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "TipoCambio Controller", description = "Microservicio encargado de exponer operaciones CRUD sobre tipo de cambio alojados en una base de datos en memoria.")
public class TipoCambioController {

    private final TipoCambioService service;

    @PostMapping("/operacion_cambio")
    @Operation(
            operationId = "Obtener el resultado de una operación de tipo de cambio",
            description = "Operacion de lectura",
            summary = "Se devuelve el resultado de un tipo de cambio.",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos del tipo de cambio a crear.",
            required = true,
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperacionCambioRequest.class))))
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TipoCambioRespuesta.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se ha encontrado el tipo de cambio con el identificador indicado.")
    public ResponseEntity<TipoCambioRespuesta> getTipoCambio2(@RequestBody OperacionCambioRequest request) {

        //log.info("Request received for product {}", monto+"_"+origen+"_"+destino);
        TipoCambio tipoCambio = service.getTipoCambio2(request.getOrigen(), request.getDestino());

        if (tipoCambio != null) {
            BigDecimal emonto = request.getMonto();
            BigDecimal monto_cambiado = emonto.multiply(tipoCambio.getTasa());
            TipoCambioRespuesta tipoCambioRespuesta = new TipoCambioRespuesta(emonto, tipoCambio.getOrigen(), tipoCambio.getDestino(), monto_cambiado,tipoCambio.getTasa());
            return ResponseEntity.ok(tipoCambioRespuesta);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/tipo_cambio")
    @Operation(
            operationId = "Obtener lista de tipos de cambio registrados",
            description = "Operacion de lectura",
            summary = "Se devuelve una lista de todos los tipos de cambio almacenados en la base de datos.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TipoCambio.class)))
    public ResponseEntity<List<TipoCambio>> getProducts(
            @RequestHeader Map<String, String> headers,
            @Parameter(name = "moneda_origen", description = "Moneda del país de origen, es decir lo que queremos cambiar", example = "PEN", required = false)
            @RequestParam(required = false) String origen,
            @Parameter(name = "moneda_destino", description = "Moneda del país de destino, es decir lo que vamos a recibir luego de aplicar la tasa", example = "USD", required = false)
            @RequestParam(required = false) String destino,
            @Parameter(name = "tipo_cambio", description = "Tasa de cambio o tipo de cambio", example = "3.85", required = false)
            @RequestParam(required = false) BigDecimal tasa) {

        log.info("headers: {}", headers);
        List<TipoCambio> tipoCambios = service.getTipoCambio(origen,destino,tasa);

        if (tipoCambios != null) {
            return ResponseEntity.ok(tipoCambios);
        } else {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    @GetMapping("/tipo_cambio/{tipoCambioId}")
    @Operation(
            operationId = "Obtener un tipo de cambio",
            description = "Operacion de lectura",
            summary = "Se devuelve un tipo de cambio a partir de su identificador.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TipoCambio.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se ha encontrado el tipo de cambio con el identificador indicado.")
    public ResponseEntity<TipoCambio> getTipoCambio(@PathVariable String tipoCambioId) {

        log.info("Request received for product {}", tipoCambioId);
        TipoCambio tipoCambio = service.getTipoCambio(tipoCambioId);

        if (tipoCambio != null) {
            return ResponseEntity.ok(tipoCambio);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/tipo_cambio/{tipoCambioId}")
    @Operation(
            operationId = "Eliminar un tipo de cambio",
            description = "Operacion de escritura",
            summary = "Se elimina un tipo de cambio a partir de su identificador.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se ha encontrado el tipo de cambio con el identificador indicado.")
    public ResponseEntity<Void> deleteTipoCambio(@PathVariable String tipoCambioId) {

        Boolean removed = service.removeTipoCambio(tipoCambioId);

        if (Boolean.TRUE.equals(removed)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/tipo_cambio")
    @Operation(
            operationId = "Insertar un tipo de cambio",
            description = "Operacion de escritura",
            summary = "Se crea un tipo de cambio a partir de sus datos.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del tipo de cambio a crear.",
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateTipoCambioRequest.class))))
    @ApiResponse(
            responseCode = "201",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TipoCambio.class)))
    @ApiResponse(
            responseCode = "400",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "Datos incorrectos introducidos.")
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se ha encontrado el tipo de cambio con el identificador indicado.")
    @ApiResponse(
            responseCode = "500",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se ha creado el tipo de cambio dado que se repiten los valores de origen y destino.")
    public ResponseEntity<TipoCambio> addTipoCambio(@RequestBody CreateTipoCambioRequest request) {

        TipoCambio createdTipoCambio = service.createTipoCambio(request);

        if (createdTipoCambio != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTipoCambio);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    @PostMapping("/tipo_cambio/{tipoCambioId}")
    @Operation(
            operationId = "Modificar parcialmente un tipo de cambio",
            description = "RFC 7386. Operacion de escritura",
            summary = "RFC 7386. Se modifica parcialmente un tipo de cambio.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del tipo de cambio a crear.",
                    required = true,
                    content = @Content(mediaType = "application/merge-patch+json", schema = @Schema(implementation = String.class))))
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TipoCambio.class)))
    @ApiResponse(
            responseCode = "400",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "Tipo de cambio inválido o datos incorrectos introducidos.")
    public ResponseEntity<TipoCambio> patchTipoCambio(@PathVariable String tipoCambioId, @RequestBody String patchBody) {

        TipoCambio patched = service.updateTipoCambio(tipoCambioId, patchBody);
        if (patched != null) {
            return ResponseEntity.ok(patched);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    @PutMapping("/tipo_cambio/{tipoCambioId}")
    @Operation(
            operationId = "Modificar totalmente un tipo de cambio",
            description = "Operacion de escritura",
            summary = "Se modifica totalmente un tipo de cambio.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del tipo de cambio a actualizar.",
                    required = true,
                    content = @Content(mediaType = "application/merge-patch+json", schema = @Schema(implementation = TipoCambioDTO.class))))
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TipoCambio.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "Tipo de cambio no encontrado.")
    public ResponseEntity<TipoCambio> updateTipoCambio(@PathVariable String productId, @RequestBody TipoCambioDTO body) {

        TipoCambio updated = service.updateTipoCambio(productId, body);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
