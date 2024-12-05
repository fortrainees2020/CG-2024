package com.cg.test;

import com.cg.controller.ProductController;
import com.cg.entity.Product;
import com.cg.exception.ResourceNotFound;
import com.cg.service.IProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private IProductService productService;

    @InjectMocks
    private ProductController productController;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product(1, "Product1", 100.0);
        
    }
   
    @Test
    void testGetAllProducts() {
        when(productService.findAllProducts()).thenReturn(Arrays.asList(product));

        var products = productController.getAllProducts();
        assertFalse(products.isEmpty());
        assertEquals(1, products.size());
        assertEquals("Product1", products.get(0).getName());
    }
   
   
    @Test
    void testGetProductByParamId() {
        when(productService.findProductById(1)).thenReturn(Optional.of(product));

        var result = productController.getProductByParamId(1);
        assertTrue(result.isPresent());
        assertEquals("Product1", result.get().getName());
    }

    @Test
    void testFindByProductIdFromDBWithException_ProductNotFound() {
        when(productService.findProductById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFound.class, () -> productController.findByProductIdFromDBWithException(1));
    }

   
    @Test
    void testCreateProduct() {
        when(productService.createProduct(product)).thenReturn(product);

        Product createdProduct = productController.CreateProduct(product);
        assertNotNull(createdProduct);
        assertEquals("Product1", createdProduct.getName());
    }

    // Test for DeleteProduct
    @Test
    void testDeleteProduct() {
        doNothing().when(productService).deleteProduct(1);

        productController.DeleteProduct(1);

        verify(productService, times(1)).deleteProduct(1);
    }

    
    @Test
    void testUpdateProduct() throws ResourceNotFound {
        when(productService.updateProduct(1, product)).thenReturn(product);

        Product updatedProduct = productController.UpdateProduct(product);
        assertNotNull(updatedProduct);
        assertEquals("Product1", updatedProduct.getName());
    }

 
    @Test
    void testGetProductByName() {
        when(productService.getProductByname("Product1")).thenReturn(Arrays.asList(product));

        var products = productController.getProductByname("Product1");
        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals("Product1", products.get(0).getName());
    }

    @Test
    void testGetCountByProduct() {
        when(productService.getCountByProductname("Product1")).thenReturn(5);

        int count = productController.getCountByProduct("Product1");
        assertEquals(5, count);
    }
  
}
