package com.alura.forohub.repository;

import com.alura.forohub.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

// Repositorio para Curso
public interface CursoRepository extends JpaRepository<Curso, Long> {
}