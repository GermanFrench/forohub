package com.forohub.foroparaalura.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GenerarHush {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hash = encoder.encode("123456");
        System.out.println("HASH GENERADO: " + hash);
    }
}
