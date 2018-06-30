package br.com.casadocodigo.loja.controllers;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.infra.StringToCalendarConverter;
import br.com.casadocodigo.loja.models.RelatorioProdutos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RelatorioProdutosController {

    /**
     * Couldn't make Spring use this converter automatically..
     * (yep, i tried registering with WebMvcConfigurerAdapter.addFormatters).
     */
    private final StringToCalendarConverter converter = new StringToCalendarConverter("yyyy-MM-dd");
    private final ProdutoDAO produtoDAO;

    @Autowired
    public RelatorioProdutosController(ProdutoDAO produtoDAO) {this.produtoDAO = produtoDAO;}

    @RequestMapping(value = "relatorio-produtos", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<?> getRelatorioProdutos(
            @RequestParam(value = "data", required = false)
                    String date) {

        return ResponseEntity.ok(
                RelatorioProdutos
                        .from(produtoDAO.getProdutosByDataLancamento(converter.convert(date)))
        );
    }

}
