package br.com.casadocodigo.loja.controllers;

import br.com.casadocodigo.loja.dao.RoleDAO;
import br.com.casadocodigo.loja.dao.UsuarioDAO;
import br.com.casadocodigo.loja.models.Usuario;
import br.com.casadocodigo.loja.validation.UsuarioValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioDAO usuarioDAO;
    private final RoleDAO roleDAO;

    @Autowired
    public UsuarioController(UsuarioDAO usuarioDAO, RoleDAO roleDAO) {
        this.usuarioDAO = usuarioDAO;
        this.roleDAO = roleDAO;
    }

    @InitBinder(value = {"usuarioModel"})
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new UsuarioValidator(usuarioDAO));
    }

    @RequestMapping(value = "/editar", method = RequestMethod.POST)
    public ModelAndView atualizarUsuario(@ModelAttribute Usuario usuario, RedirectAttributes redirectAttributes) {

        System.out.println("Email: " + usuario.getEmail());

        /*
            Algum bug fantasma fez com que o valor do email do usuário vindo do formulário de Roles
            viesse com uma vírgula no inicio. ex: ",email@mail.com.br", fazendo com que o usuário não
            fosse encontrado para atualizar na base.

            O valor na view era populado corretamente, no entanto ao chegar no Controller tinha essa virgula,
            para 'corrigir' isto, adicionei um replaceAll() removendo qualquer virgula do valor de email.
         */
        Optional<Usuario> optional = usuarioDAO.findByEmail(usuario.getEmail().replaceAll(",", ""));

        if (optional.isPresent()) {
            Usuario u = optional.get();
            u.setRoles(usuario.getRoles());
            usuarioDAO.atualizar(u);
        }

        redirectAttributes.addFlashAttribute("message", "messages.user.updated-permissions");
        return new ModelAndView("redirect:/usuarios");
    }

    @RequestMapping(value = "/editar", method = RequestMethod.GET)
    public ModelAndView editar(@RequestParam String email, RedirectAttributes redirectAttributes) {

        Optional<Usuario> usuario = usuarioDAO.findByEmail(email);

        if (usuario.isPresent()) {
            return new ModelAndView("usuarios/editar")
                    .addObject("usuario", usuario.get())
                    .addObject("roles", roleDAO.listar());
        }

        redirectAttributes.addFlashAttribute("message", "messages.inexistent.user");
        return new ModelAndView("redirect:/usuarios");
    }


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listar() {

        return new ModelAndView("usuarios/lista")
                .addObject("usuarios", usuarioDAO.listar());
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public ModelAndView form(Usuario usuario) {

        if (usuario == null) {
            usuario = new Usuario();
        }

        return new ModelAndView("usuarios/form")
                .addObject("usuarioModel", usuario);
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public ModelAndView gravar(@ModelAttribute("usuarioModel") @Valid Usuario usuarioModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return form(usuarioModel);
        } else {
            usuarioDAO.gravar(usuarioModel);
            redirectAttributes.addFlashAttribute("message", "messages.success.new-user");
        }

        return new ModelAndView("redirect:/usuarios");
    }


}
