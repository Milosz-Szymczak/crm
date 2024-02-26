package pl.crm.repository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import pl.crm.model.Product;

@Repository
public interface ProductRepository extends ListCrudRepository<Product, Long> {
}
