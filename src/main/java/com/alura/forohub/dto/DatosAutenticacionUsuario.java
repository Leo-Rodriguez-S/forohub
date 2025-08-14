package com.alura.forohub.dto;

import jakarta.validation.constraints.NotBlank;

// DTO para autenticación
public record DatosAutenticacionUsuario(
        @NotBlank String login,
        @NotBlank String clave
) {}