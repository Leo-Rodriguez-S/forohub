package com.alura.forohub.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Utilidad para generar contraseñas encriptadas
 * Usar solo para generar passwords para la base de datos
 */
public class PasswordGenerator {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // Generar hash para la contraseña "123456"
        String password = "123456";
        String hashedPassword = encoder.encode(password);

        System.out.println("Contraseña original: " + password);
        System.out.println("Contraseña encriptada: " + hashedPassword);

        // Verificar que la contraseña coincide
        boolean matches = encoder.matches(password, hashedPassword);
        System.out.println("¿Coincide?: " + matches);
    }
}