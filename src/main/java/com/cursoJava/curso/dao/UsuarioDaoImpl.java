package com.cursoJava.curso.dao;

import com.cursoJava.curso.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
@Transactional
public class UsuarioDaoImpl implements UsuarioDao{
   @PersistenceContext
   private EntityManager entityManager;

   @Override
   public List<Usuario> getUsuarios() {
      String query = "from Usuario";
      return entityManager.createQuery(query).getResultList();
   }
   @Override
   public void eliminar(long id) {
      Usuario usuario = entityManager.find(Usuario.class,id);
      entityManager.remove(usuario);
   }

   @Override
   public void registrar(Usuario usuario) {
      entityManager.merge(usuario);
   }
   @Override
   public Usuario obtenerUsuarioPorCredenciales(Usuario usuario) {
      String query = "from Usuario where email = :email";
      List<Usuario> lista = entityManager.createQuery(query)
            .setParameter("email", usuario.getEmail())
            .getResultList();

      Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
      return lista.isEmpty() ? null :                                   //si no hay usuarios con esa mail devuelve null
            argon2.verify(lista.get(0).getPassword(), usuario.getPassword()) ? //controla la password
                  lista.get(0) : null;                                      //si es correcta devuelve el usuario, sinò null
   }

}
