package com.cursoJava.curso.controllers;

import com.cursoJava.curso.dao.UsuarioDao;
import com.cursoJava.curso.models.Usuario;
import com.cursoJava.curso.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/login")
public class AuthController {
   @Autowired
   private UsuarioDao usuarioDao;
   @Autowired
   private JWTUtil jwtUtil;
   @PostMapping()
   public String login(@RequestBody Usuario usuario){
      Usuario usuarioLogueado = usuarioDao.obtenerUsuarioPorCredenciales(usuario);
      System.out.println(usuarioLogueado);
//      if (usuarioLogueado != null){
//         String tokenJWT = jwtUtil.create(String.valueOf(usuarioLogueado.getId()), usuarioLogueado.getEmail());
//         return tokenJWT;
//      }
//      return "fail";
      return usuarioLogueado != null ?
            jwtUtil.create(String.valueOf(usuarioLogueado.getId()), usuarioLogueado.getEmail()) :  //devuelve el token con key id y value email
            "fail";
   }
}
