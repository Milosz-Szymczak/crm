package pl.crm.service;

import org.springframework.stereotype.Service;
import pl.crm.model.Product;

import java.util.List;

@Service
public interface ProductService {


    List<Product> getProducts();

    void save(Product product);

    Product getProductById(Long productId);

    void updateProduct(Product product);

}
