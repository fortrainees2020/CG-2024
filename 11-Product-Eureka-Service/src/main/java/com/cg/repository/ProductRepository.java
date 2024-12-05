package com.cg.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	public List<Product> findAllByname(String name);//as it is interact with db, this list would be here
	//findByname== name is property of product
	@Query("select count(*) from Product where name=:name")
	public int getCountByProductname(String name);
}
