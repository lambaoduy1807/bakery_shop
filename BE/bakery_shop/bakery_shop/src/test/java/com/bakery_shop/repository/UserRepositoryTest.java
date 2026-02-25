package com.bakery_shop.repository;

import com.bakery_shop.model.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void testSaveAndFind() {
        UserEntity user = new UserEntity(null,"name","email","pass");
        UserEntity saved = userRepository.save(user);

        assertThat(saved.getId()).isNotNull();

        UserEntity found = userRepository.findById(saved.getId()).orElse(null);
        assertThat(found).isNotNull();
        assertThat(found.getUserName()).isEqualTo("name");
    }
}
