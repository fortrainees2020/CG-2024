package com.cg.controller;
 
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Product;
import com.cg.exception.ResourceNotFound;
import com.cg.service.IProductService;
 
@RequestMapping("/api")
@RestController
public class ProductController {
	@Autowired
	IProductService productService;
	
	
	
	@GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.findAllProducts()
                            .stream()
                            //.map(ProductDTO::fromEntity)  // Convert to ProductDTO
                            .collect(Collectors.toList());
    }
	 @Value("${app.name}")
	 private String appName;
	 
	 @GetMapping(path = "/products/{id}")
	    public String getProductById(@PathVariable int id) {
	        Optional<Product> product = productService.findProductById(id);

	        if (product.isPresent()) {
	            return product.get().toString();  
	        } else {
	            System.out.println(appName); 
	            return appName; 
	        }
	    }

	
	@GetMapping(path = "/products/myProduct")
	public Optional<Product> getProductByParamId(@RequestParam int id) {
	
		return	productService.findProductById(id);
		
	}
	
	
	@GetMapping("/productsexception/{id}")
	public
	Optional<Product>findByProductIdFromDBWithException(@PathVariable int id) throws ResourceNotFound
	{	Optional<Product> product = productService.findProductById(id);
	
    	product.orElseThrow(() -> new ResourceNotFound("Product not found for this id :: " + id));
    	System.out.println(id);
    return product;	
	}
	
	
	@PostMapping("/prodpost")
	public Product CreateProduct( @RequestBody Product product) {
        return productService.createProduct(product);
	}
	
	@DeleteMapping("/products/{id}")
	public void DeleteProduct(@PathVariable int id) {
		 productService.deleteProduct(id);
	}
	@PutMapping("/prodput")
	public Product UpdateProduct(@RequestBody Product p1) throws ResourceNotFound{
		int id = p1.getId();
		return productService.updateProduct(id,p1);
    	
	}
	
	@GetMapping(path="/products/getproductbyName/{name}")
	public List<Product> getProductByname(@PathVariable String name) {
		return productService.getProductByname(name);
	}
	@GetMapping(path="/products/count/{name}")
	public int getCountByProduct(@PathVariable String name){
		return productService.getCountByProductname(name);
	}
	
}