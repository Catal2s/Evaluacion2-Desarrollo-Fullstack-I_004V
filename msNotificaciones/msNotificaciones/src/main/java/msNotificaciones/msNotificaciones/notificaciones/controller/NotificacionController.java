package msNotificaciones.msNotificaciones.notificaciones.controller;

import msNotificaciones.msNotificaciones.notificaciones.dto.NotificacionDTO;
import msNotificaciones.msNotificaciones.notificaciones.model.Notificacion;
import msNotificaciones.msNotificaciones.notificaciones.service.NotificacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
@RequiredArgsConstructor
public class NotificacionController {

    private static final Logger logger = LoggerFactory.getLogger(NotificacionController.class);
    private final NotificacionService notificacionService;

    @GetMapping
    public ResponseEntity<List<Notificacion>> listarNotificaciones() {
        logger.info("GET /api/notificaciones");
        return ResponseEntity.ok(notificacionService.listarNotificaciones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notificacion> obtenerNotificacion(@PathVariable Long id) {
        logger.info("GET /api/notificaciones/{}", id);
        return ResponseEntity.ok(notificacionService.obtenerNotificacionPorId(id));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Notificacion>> listarPorEstado(@PathVariable String estado) {
        logger.info("GET /api/notificaciones/estado/{}", estado);
        return ResponseEntity.ok(notificacionService.listarPorEstado(estado));
    }

    @GetMapping("/socio/{socioId}")
    public ResponseEntity<List<Notificacion>> listarPorSocio(@PathVariable Long socioId) {
        logger.info("GET /api/notificaciones/socio/{}", socioId);
        return ResponseEntity.ok(notificacionService.listarPorSocio(socioId));
    }

    @PostMapping
    public ResponseEntity<Notificacion> crearNotificacion(@Valid @RequestBody NotificacionDTO dto) {
        logger.info("POST /api/notificaciones");
        return ResponseEntity.status(HttpStatus.CREATED).body(notificacionService.crearNotificacion(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notificacion> actualizarNotificacion(@PathVariable Long id, @Valid @RequestBody NotificacionDTO dto) {
        logger.info("PUT /api/notificaciones/{}", id);
        return ResponseEntity.ok(notificacionService.actualizarNotificacion(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarNotificacion(@PathVariable Long id) {
        logger.info("DELETE /api/notificaciones/{}", id);
        notificacionService.eliminarNotificacion(id);
        return ResponseEntity.noContent().build();
    }
}