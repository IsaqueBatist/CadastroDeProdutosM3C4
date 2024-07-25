package com.abutua.productbackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.abutua.productbackend.models.Category;
import com.abutua.productbackend.models.Product;
import com.abutua.productbackend.repositories.CategoryRepository;
import com.abutua.productbackend.repositories.ProductRepository;

@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  public Product getbyId(int id) {
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    return product;
  }

  public List<Product> getAll() {
    return productRepository.findAll();
  }

  public Product save(Product product) {
    return productRepository.save(product);
  }

  public void deleteById(int id) {
    Product product = getbyId(id);
    productRepository.delete(product);
  }

  public void update(int id, Product productUpdate) {
    Product product = getbyId(id);
    if (productUpdate.getIdcategory() == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category is required");
    }
    Category category = categoryRepository.findById(productUpdate.getIdcategory().getId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

    product.setName(productUpdate.getName());
    product.setDescription(productUpdate.getDescription());
    product.setPrice(productUpdate.getPrice());
    product.setPromotion(productUpdate.isPromotion());
    product.setNewProduct(productUpdate.isNewProduct());
    product.setIdcategory(category);

    productRepository.save(product);
  }
}
