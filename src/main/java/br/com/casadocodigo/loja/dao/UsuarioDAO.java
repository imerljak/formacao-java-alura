package br.com.casadocodigo.loja.dao;

import br.com.casadocodigo.loja.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class UsuarioDAO implements UserDetailsService {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<Usuario> findByEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            return Optional.empty();
        }

        return Optional.ofNullable(manager.find(Usuario.class, email));
    }

    public Usuario loadUserByUsername(String email) {
        List<Usuario> usuarios = manager.createQuery("select u from Usuario u where email = :email", Usuario.class)
                .setParameter("email", email)
                .getResultList();

        if (usuarios.isEmpty()) {
            throw new UsernameNotFoundException("Usuario " + email + " não foi encontrado");
        }

        return usuarios.get(0);
    }

    public void gravar(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        manager.persist(usuario);
    }

    public List<Usuario> listar() {
        return manager.createQuery("SELECT u FROM Usuario u", Usuario.class)
                .getResultList();
    }
}