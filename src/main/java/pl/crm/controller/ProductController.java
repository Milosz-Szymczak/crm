package pl.crm.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.crm.model.Product;
import pl.crm.service.ProductService;

import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/form-add-product")
    public String addNewProduct(Model model) {
        model.addAttribute("product", new Product());
        return "form-add-product";
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/form-add-product")
    public String saveProduct(@ModelAttribute("product") Product product) {
        productService.save(product);
        return "redirect:/products";
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/products")
    public String product(Model model) {
        List<Product> products = productService.getProducts();
        model.addAttribute("product", products);
        return "products";
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/form-update-product/{productId}")
    public String showUpdateForm(@PathVariable("productId") Long productId, Model model) {
        Product product = productService.getProductById(productId);
        model.addAttribute("product", product);
        return "form-update-product";
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("/update-product")
    public String updateProduct(@ModelAttribute("product") Product product) {
        productService.updateProduct(product);
        return "redirect:/products";
    }
}