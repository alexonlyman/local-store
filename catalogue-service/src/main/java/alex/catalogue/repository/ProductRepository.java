package alex.catalogue.repository;

import alex.catalogue.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product,Integer> {
    Iterable<Product> findAllByTitleLikeIgnoreCase(String filter);

}
