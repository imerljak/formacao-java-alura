package br.com.casadocodigo.loja.validation;

import br.com.casadocodigo.loja.dao.UsuarioDAO;
import br.com.casadocodigo.loja.models.Usuario;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UsuarioValidator implements Validator {

    private UsuarioDAO usuarioDAO;

    public UsuarioValidator(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Usuario.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "senha", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmaSenha", "field.required");

        Usuario u = (Usuario) o;
        if (u.getSenha() != null) {
            if (u.getSenha().length() < 5) {
                errors.rejectValue("senha", "field.length.password");
            }

            if (!u.getSenha().equals(u.getConfirmaSenha())) {
                errors.rejectValue("senha", "field.mismatch.password");
                errors.rejectValue("confirmaSenha", "field.mismatch.password");
            }
        }

        if (usuarioDAO.findByEmail(u.getEmail()).isPresent()) {
            errors.rejectValue("email", "field.unique.email");
        }

    }
}
