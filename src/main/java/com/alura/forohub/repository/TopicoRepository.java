package com.alura.forohub.repository;

import com.alura.forohub.model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// Repositorio para Topico
public interface TopicoRepository extends JpaRepository<Topico, Long> {

    // Verificar si existe un tópico con el mismo título y mensaje
    boolean existsByTituloAndMensaje(String titulo, String mensaje);

    // Buscar tópicos por curso y año
    @Query("SELECT t FROM Topico t WHERE t.curso.nombre = :nombreCurso " +
            "AND YEAR(t.fechaCreacion) = :anio")
    Page<Topico> findByCursoAndAnio(@Param("nombreCurso") String nombreCurso,
                                    @Param("anio") int anio,
                                    Pageable pageable);

    // Buscar tópicos ordenados por fecha de creación
    @Query("SELECT t FROM Topico t ORDER BY t.fechaCreacion ASC")
    Page<Topico> findAllOrderByFechaCreacion(Pageable pageable);
}