package com.bakery_shop.repository;

import com.bakery_shop.model.ProductDTO;
import com.bakery_shop.model.ProductEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    List<ProductEntity> products = new ArrayList<>();
    public ProductRepository() {
        products.add(new ProductEntity("1", "Bánh mì", "/images/banhmi.jpg", "Bánh mì Việt Nam giòn thơm", 10000));
        products.add(new ProductEntity("2", "Bánh kem", "/images/banhkem.jpg", "Bánh kem socola ngọt ngào", 150000));
        products.add(new ProductEntity("3", "Donut", "/images/donut.jpg", "Bánh donut phủ đường", 20000));
        products.add(new ProductEntity("4", "Croissant", "/images/croissant.jpg", "Bánh sừng bò bơ Pháp", 30000));
        products.add(new ProductEntity("5", "Cupcake", "/images/cupcake.jpg", "Bánh cupcake nhỏ xinh nhiều vị", 25000));
        products.add(new ProductEntity("6", "Macaron", "/images/macaron.jpg", "Bánh macaron Pháp nhiều màu sắc", 40000));
        products.add(new ProductEntity("7", "Tart Trái cây", "/images/tart.jpg", "Bánh tart phủ trái cây tươi ngon", 50000));
        products.add(new ProductEntity("8", "Cheesecake", "/images/cheesecake.jpg", "Bánh cheesecake vị phô mai béo ngậy", 80000));
        products.add(new ProductEntity("9", "Muffin", "/images/muffin.jpg", "Bánh muffin mềm xốp, nhiều hương vị", 35000));

    }
    public ProductDTO getProduct(int id) {
        ProductDTO product = new ProductDTO();
        return product;
    }
    public List<ProductDTO> getProducts(int page) {
        List<ProductDTO> products = new ArrayList<ProductDTO>();
        return products;
    }
}
