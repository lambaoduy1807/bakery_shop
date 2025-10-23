package com.bakery_shop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EnvLoadingTest {

    @Value("${MONGO_USER:NOT_FOUND}")
    private String mongoUser;

    @Value("${MONGO_PASS:NOT_FOUND}")
    private String mongoPass;

    @Value("${MONGO_DB:NOT_FOUND}")
    private String mongoDb;

    @Test
    void shouldLoadEnvVariables() {
        System.out.println("MONGO_USER = " + mongoUser);
        System.out.println("MONGO_PASS = " + mongoPass);
        System.out.println("MONGO_DB = " + mongoDb);

        // Kiểm tra xem biến có thực sự được load không
        assertThat(mongoUser).isNotEqualTo("NOT_FOUND");
        assertThat(mongoPass).isNotEqualTo("NOT_FOUND");
        assertThat(mongoDb).isNotEqualTo("NOT_FOUND");
    }
}
