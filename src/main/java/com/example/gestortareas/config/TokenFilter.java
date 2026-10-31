package com.example.gestortareas.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class TokenFilter implements Filter {

    private static final String TOKEN_VALIDO = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhZG1pbmlzdHJhZG9yIiwibmFtZSI6IkVzdHVkaWFudGUiLCJyb2xlIjoiVVNVQVJJT19QUkFDVElDQSJ9.c2VjcmV0LWtleS1zaW11bGFkby0yMDI2";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // Configuración global de CORS para permitir la comunicación con Angular
        res.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        res.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
        res.setHeader("Access-Control-Allow-Credentials", "true");

        // Permitir las verificaciones OPTIONS del navegador sin pedir token
        if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
            res.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        // Validar el token simulado solicitado por la actividad
        String authHeader = req.getHeader("Authorization");
        if (authHeader == null || !authHeader.equals(TOKEN_VALIDO)) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.getWriter().write("Acceso Denegado: Token invalido o ausente");
            return;
        }

        chain.doFilter(request, response);
    }
}
