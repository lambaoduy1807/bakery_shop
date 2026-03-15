package com.bakery_shop.service;

import com.bakery_shop.model.Mapper;
import com.bakery_shop.model.dto.ProductDTO;
import com.bakery_shop.model.entity.ProductEntity;
import com.bakery_shop.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final Mapper mapper;

    // ========= READ =========
    public Page<ProductDTO> getProducts(int page) {
        return productRepository.findAll(PageRequest.of(page, 9))
                .map(mapper::toProductDTO);
    }

    public Page<ProductDTO> getProductsByCategories(int page, String category) {
        Pageable pageable = PageRequest.of(page, 9, Sort.by("name").ascending());
        Page<ProductEntity> entities = productRepository.findByCategory(category, pageable);

        return entities.map(mapper::toProductDTO);
    }

    public List<ProductDTO> getAll() {
        return productRepository.findAll()
                .stream()
                .map(mapper::toProductDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO getById(UUID id) {
        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
        return mapper.toProductDTO(entity);
    }

    // ========= CREATE / UPDATE =========
    public ProductDTO create(ProductDTO dto) {
        ProductEntity entity = mapper.toProductEntity(dto);
        ProductEntity saved = productRepository.save(entity);
        return mapper.toProductDTO(saved);
    }

    public ProductDTO update(UUID id, ProductDTO dto) {
        ProductEntity existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));

        // cập nhật các field cơ bản
        existing.setName(dto.getName());
        existing.setImg(dto.getImg());
        existing.setDetail(dto.getDetail());
        existing.setPrice(dto.getPrice());

        ProductEntity saved = productRepository.save(existing);
        return mapper.toProductDTO(saved);
    }

    // ========= DELETE =========
    public void delete(UUID id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id " + id);
        }
        productRepository.deleteById(id);
    }
}
