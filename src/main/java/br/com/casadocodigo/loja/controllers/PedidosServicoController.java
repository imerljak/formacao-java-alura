package br.com.casadocodigo.loja.controllers;

import br.com.casadocodigo.loja.models.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pedidos")
public class PedidosServicoController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listar() {
        ResponseEntity<Pedido[]> response = restTemplate
                .getForEntity("https://book-payment.herokuapp.com/orders", Pedido[].class);

        return new ModelAndView("pedidos/lista")
                .addObject("pedidos", response.getBody());
    }

}
