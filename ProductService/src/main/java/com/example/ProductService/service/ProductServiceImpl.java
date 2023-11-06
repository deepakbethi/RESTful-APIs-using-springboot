package com.example.ProductService.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProductService.Repository.ProductRepository;
import com.example.ProductService.entity.Product;
import com.example.ProductService.exception.ProductServiceCoustomException;
import com.example.ProductService.model.ProductRequest;
import com.example.ProductService.model.ProductResponse;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductRepository repo;

	@Override
	public long addProduct(ProductRequest productRequest) {
		Product product=Product.builder()
				.productName(productRequest.getName())
				.quantity(productRequest.getQuantity())
				.price(productRequest.getPrice())
				.build();
		log.info("added");
		repo.save(product);
		return 0;
	}

	@Override
	public ProductResponse getProductById(long productId) {
		// TODO Auto-generated method stub
		Product product=repo.findById(productId)
				.orElseThrow(
						()-> new ProductServiceCoustomException("product not found","PRODUCT_NOT_FOUND"));
		ProductResponse productResponse=new ProductResponse();
		BeanUtils.copyProperties(product, productResponse);
		return productResponse;
		
	}

	
	

	
	@Override
    public void reduceQuantity(long productId, long quantity) {
        log.info("Reduce Quantity {} for Id: {}", quantity,productId);

        Product product
                = repo.findById(productId)
                .orElseThrow(()-> new ProductServiceCoustomException("product not found with given id", "PRODUCT_NOT_FOUND"));
        if(product.getQuantity()<quantity) {
        	throw new ProductServiceCoustomException("no enough quantity", "NO_QUANTITY");
        }
        
        product.setQuantity(product.getQuantity()-quantity);
        repo.save(product);
        log.info("quantity updated");

        

        
    }
	
	

}
