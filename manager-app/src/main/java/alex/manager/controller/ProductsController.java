package alex.manager.controller;

import alex.manager.client.BadRequestException;
import alex.manager.client.ProductsRestClient;
import alex.manager.controller.payLoad.NewProductPayload;
import alex.manager.entity.Product;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("catalogue/products")
@RequiredArgsConstructor
public class ProductsController {
    private final Logger loggerFactory = LoggerFactory.getLogger(ProductsController.class);

    private final ProductsRestClient client;

    @GetMapping("list")
    public String getProductsList(Model model) {
        model.addAttribute("products", this.client.findAllProducts());
        return "catalogue/products/list";
    }

    @GetMapping("create")
    public String getNewProductPage() {
        return "catalogue/products/new_product";
    }

    @PostMapping("create")
    public String createProduct(NewProductPayload payload,
                                Model model,
                                HttpServletResponse response
    ) {

        try {

            Product product = this.client.createProduct(payload.title(), payload.details());
            return "redirect:/catalogue/products/%d".formatted(product.id());
        } catch (BadRequestException exception) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            model.addAttribute("payload", payload);
            model.addAttribute("errors", exception.getErrors());
            return "catalogue/products/new_product";
        }
    }
}
