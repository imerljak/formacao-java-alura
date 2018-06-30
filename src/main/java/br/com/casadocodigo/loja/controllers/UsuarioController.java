package br.com.casadocodigo.loja.controllers;

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

    @Autowired
    public UsuarioController(UsuarioDAO usuarioDAO) {this.usuarioDAO = usuarioDAO;}

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new UsuarioValidator(usuarioDAO));
    }

    @RequestMapping(value = "/editar", method = RequestMethod.GET)
    public ModelAndView editar(@RequestParam String email, RedirectAttributes redirectAttributes) {

        System.out.println("UsuarioController.editar");
        System.out.println("email = " + email);

        Optional<Usuario> usuario = usuarioDAO.findByEmail(email);
        System.out.println("usuario = " + usuario);

        if (usuario.isPresent()) {
            return new ModelAndView("usuarios/editar")
                    .addObject("usuario", usuario.get());
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
