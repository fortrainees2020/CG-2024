package com.cg.service;
 

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.Product;
import com.cg.repository.ProductRepository;
 
@Service
public class ProductImpl implements IProductService{
	@Autowired
	ProductRepository prd;
	@Override
	public List<Product> findAllProducts() {
		return prd.findAll();
	}
	
	public Optional<Product> findProductById(int id) {
		return prd.findById(id);
	}

	@Override
	public Product createProduct(Product prod) {
		// TODO Auto-generated method stub
		return prd.save(prod);
	}

	public void deleteProduct(int id) {
		// TODO Auto-generated method stub
		prd.deleteById(id);
	}

	@Override
	public Product updateProduct(int id,Product p1) {
		// TODO Auto-generated method stub
		 Optional<Product> p= findProductById(id);
		 Product product= p.get();
		 product.setId(p1.getId());
		 product.setName(p1.getName());
		 product.setPrice(p1.getPrice());
		 return prd.save(p1);
	}
	
	public List<Product> getProductByname(String name){
		return prd.findAllByname(name);
	}

	@Override
	public int getCountByProductname(String name) {
		// TODO Auto-generated method stub
		return prd.getCountByProductname(name);
	}

	
	
	
 
}

