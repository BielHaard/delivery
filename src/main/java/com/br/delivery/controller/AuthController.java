package com.br.delivery.controller;

import com.br.delivery.dto.UsuarioDTO;
import com.br.delivery.entity.Usuario;
import com.br.delivery.service.UsuarioService;
import com.br.delivery.utils.APIPaths;
import com.br.delivery.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(APIPaths.LOGIN)
public class AuthController {

    private final UsuarioService usuarioService;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AuthController(UsuarioService usuarioService, JwtUtil jwtUtil, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usuarioService = usuarioService;
        this.jwtUtil = jwtUtil;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping
    @ResponseBody
    public String login(@RequestBody UsuarioDTO usuarioDTO) throws Exception {
        String username = usuarioDTO.getUsername();
        String password = usuarioDTO.getPassword();

        Usuario usuario = usuarioService.findByUsername(username);
        if (usuario != null && bCryptPasswordEncoder.matches(password, usuario.getPassword())) {
            return jwtUtil.generateToken(username);
        } else {
            return "Credenciais inválidas";
        }
    }
}

