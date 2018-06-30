package br.com.casadocodigo.loja.controllers;

import br.com.casadocodigo.loja.dao.UsuarioDAO;
import br.com.casadocodigo.loja.models.UsuarioFormModel;
import br.com.casadocodigo.loja.validation.UsuarioFormModelValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioDAO usuarioDAO;

    @Autowired
    public UsuarioController(UsuarioDAO usuarioDAO) {this.usuarioDAO = usuarioDAO;}

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new UsuarioFormModelValidation(usuarioDAO));
    }


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listar() {

        return new ModelAndView("usuarios/lista")
                .addObject("usuarios", usuarioDAO.listar());
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public ModelAndView form(UsuarioFormModel usuario) {

        if (usuario == null) {
            usuario = new UsuarioFormModel();
        }

        return new ModelAndView("usuarios/form")
                .addObject("usuarioModel", usuario);
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public ModelAndView gravar(@ModelAttribute("usuarioModel") @Valid UsuarioFormModel usuarioModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {



        if (bindingResult.hasErrors()) {
            return form(usuarioModel);
        } else {
            usuarioDAO.gravar(usuarioModel.asUsuario());
            redirectAttributes.addFlashAttribute("message", "messages.success.new-user");
        }

        return new ModelAndView("redirect:/usuarios");
    }


}
