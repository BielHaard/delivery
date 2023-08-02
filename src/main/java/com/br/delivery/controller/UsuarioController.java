package com.br.delivery.controller;

import com.br.delivery.dto.UsuarioDTO;
import com.br.delivery.entity.Usuario;
import com.br.delivery.service.UsuarioService;
import com.br.delivery.utils.APIPaths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(APIPaths.SIGNUP)
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioController(UsuarioService usuarioService, BCryptPasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    @ResponseBody
    public String signUp(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setUsername(usuarioDTO.getUsername());

        boolean usuarioExiste = usuarioService.existsByUsername(usuario.getUsername());
        if (usuarioExiste){
            return "Usuario já está cadastrado na base";
        }else {
            usuario.setPassword(usuarioDTO.getPassword());
            usuarioService.salvarUsuario(usuario);
            return "Usuário cadastrado com sucesso!";
        }
    }

}
