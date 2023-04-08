package com.cursoJava.curso.controllers;

import com.cursoJava.curso.dao.UsuarioDao;
import com.cursoJava.curso.models.Usuario;
import com.cursoJava.curso.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/usuarios")
public class UsuarioController {
   @Autowired
   private UsuarioDao usuarioDao;
   @Autowired
   private JWTUtil jwtUtil;
   @GetMapping(value = "/{id}")
   public Usuario getUsuario(@PathVariable long id){
      Usuario usuario = new Usuario();
      usuario.setId(id);
      usuario.setApellido("Yolo");
      usuario.setEmail("yolo_oloy@hotmail.ht");
      usuario.setNombre("yoNoYolo");
      usuario.setPassword("123");
      usuario.setTelefono("1234567890");
      return usuario;
   }
   @GetMapping()
   public List<Usuario> getUsuarios(@RequestHeader(value = "Authorization") String token){
      return !validarToken(token) ? null : usuarioDao.getUsuarios();
   }
   private boolean validarToken(String token){
      return jwtUtil.getKey(token) != null;
   }
   @PostMapping()
   public void registrarUsuario(@RequestBody Usuario usuario){
      Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
      String hash = argon2.hash(1,1024, 1, usuario.getPassword());
      usuario.setPassword(hash);
      usuarioDao.registrar(usuario);
   }
   @PutMapping(value = "usuaridrfgo1")
   public Usuario editar(){
      Usuario usuario = new Usuario();
      usuario.setApellido("Yolo");
      usuario.setEmail("yolo_oloy@hotmail.ht");
      usuario.setNombre("yoNoYolo");
      usuario.setPassword("123");
      usuario.setTelefono("1234567890");
      return usuario;
   }
   @DeleteMapping(value = "{id}")
   public void eliminar(@RequestHeader(value = "Authorization") String token,
                        @PathVariable long id){
      if(!validarToken(token)) return;
      usuarioDao.eliminar(id);
   }
}
