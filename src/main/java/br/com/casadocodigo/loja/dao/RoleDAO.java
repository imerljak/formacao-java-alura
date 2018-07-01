package br.com.casadocodigo.loja.dao;

import br.com.casadocodigo.loja.models.Role;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class RoleDAO {

    @PersistenceContext
    private EntityManager manager;

    public void gravar(Role role) {
        manager.persist(role);
    }

    public List<Role> listar() {
        return manager.createQuery("SELECT r FROM Role r", Role.class)
                .getResultList();
    }
}
