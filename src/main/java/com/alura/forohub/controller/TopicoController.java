package com.alura.forohub.controller;

import com.alura.forohub.dto.*;
import com.alura.forohub.model.Curso;
import com.alura.forohub.model.Topico;
import com.alura.forohub.model.Usuario;
import com.alura.forohub.repository.CursoRepository;
import com.alura.forohub.repository.TopicoRepository;
import com.alura.forohub.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    // Crear nuevo tópico - POST /topicos
    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(
            @RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
            UriComponentsBuilder uriComponentsBuilder) {

        // Validar que no exista un tópico duplicado
        if (topicoRepository.existsByTituloAndMensaje(datosRegistroTopico.titulo(), datosRegistroTopico.mensaje())) {
            return ResponseEntity.badRequest().build();
        }

        // Buscar usuario y curso
        Usuario autor = usuarioRepository.findById(datosRegistroTopico.autorId()).orElse(null);
        Curso curso = cursoRepository.findById(datosRegistroTopico.cursoId()).orElse(null);

        if (autor == null || curso == null) {
            return ResponseEntity.badRequest().build();
        }

        // Crear y guardar el tópico
        Topico topico = new Topico(
                datosRegistroTopico.titulo(),
                datosRegistroTopico.mensaje(),
                autor,
                curso
        );

        topicoRepository.save(topico);

        DatosRespuestaTopico datosRespuesta = new DatosRespuestaTopico(topico);
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(url).body(datosRespuesta);
    }

    // Listar todos los tópicos - GET /topicos
    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listarTopicos(
            @PageableDefault(size = 10, sort = "fechaCreacion") Pageable paginacion) {

        Page<DatosListadoTopico> topicos = topicoRepository.findAllOrderByFechaCreacion(paginacion)
                .map(DatosListadoTopico::new);

        return ResponseEntity.ok(topicos);
    }

    // Obtener tópico específico - GET /topicos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> retornarDatosTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        var datosTopico = new DatosRespuestaTopico(topico);
        return ResponseEntity.ok(datosTopico);
    }

    // Actualizar tópico - PUT /topicos/{id}
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(
            @PathVariable Long id,
            @RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {

        Topico topico = topicoRepository.getReferenceById(id);
        topico.actualizar(datosActualizarTopico.titulo(), datosActualizarTopico.mensaje());

        return ResponseEntity.ok(new DatosRespuestaTopico(topico));
    }

    // Eliminar tópico - DELETE /topicos/{id}
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        topicoRepository.delete(topico);
        return ResponseEntity.noContent().build();
    }

    // Buscar tópicos por curso y año (opcional)
    @GetMapping("/buscar")
    public ResponseEntity<Page<DatosListadoTopico>> buscarPorCursoYAnio(
            @RequestParam String curso,
            @RequestParam int anio,
            @PageableDefault(size = 10, sort = "fechaCreacion") Pageable paginacion) {

        Page<DatosListadoTopico> topicos = topicoRepository.findByCursoAndAnio(curso, anio, paginacion)
                .map(DatosListadoTopico::new);

        return ResponseEntity.ok(topicos);
    }
}