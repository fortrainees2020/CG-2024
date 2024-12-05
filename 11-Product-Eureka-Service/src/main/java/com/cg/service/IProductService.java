package com.cg.service;
 
import java.util.List;
import java.util.Optional;

import com.cg.entity.Product;
 
public interface IProductService {
	List<Product> findAllProducts() ;
	public Optional<Product> findProductById(int id);
	public Product createProduct(Product prod);
	public void deleteProduct(int id);
	public Product updateProduct(int id,Product p1);
	public List<Product> getProductByname(String name);
	public int getCountByProductname(String name);
}