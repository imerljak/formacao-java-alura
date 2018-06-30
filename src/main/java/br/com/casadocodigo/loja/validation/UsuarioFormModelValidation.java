package br.com.casadocodigo.loja.validation;

import br.com.casadocodigo.loja.dao.UsuarioDAO;
import br.com.casadocodigo.loja.models.UsuarioFormModel;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UsuarioFormModelValidation implements Validator {

    private UsuarioDAO usuarioDAO;

    public UsuarioFormModelValidation(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsuarioFormModel.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "senha", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmaSenha", "field.required");

        UsuarioFormModel ufm = (UsuarioFormModel) o;
        if (ufm.getSenha() != null) {
            if (ufm.getSenha().length() < 5) {
                errors.rejectValue("senha", "field.length.password");
            }

            if (!ufm.getSenha().equals(ufm.getConfirmaSenha())) {
                errors.rejectValue("senha", "field.mismatch.password");
                errors.rejectValue("confirmaSenha", "field.mismatch.password");
            }
        }

        if (usuarioDAO.findByEmail(ufm.getEmail()).isPresent()) {
            errors.rejectValue("email", "field.unique.email");
        }

    }
}
