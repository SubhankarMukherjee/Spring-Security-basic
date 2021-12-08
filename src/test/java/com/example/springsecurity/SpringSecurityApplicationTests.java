package com.example.springsecurity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.jdbc.NamedParameterJdbcOperationsDependsOnPostProcessor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SpringSecurityApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testPasswordEncoder()
    {
        System.out.println(new BCryptPasswordEncoder().encode("ma"));
        // result: $2a$10$BGVNXVz7ISxx0fGaN4r9ceAGz8/

        System.out.println(new Pbkdf2PasswordEncoder().encode("ma"));

        //For Scrypt to work we need BouncyCastle Maven Dependecy
        System.out.println(new SCryptPasswordEncoder().encode("ma"));

    }
    @Test
    void delegatePasswordEncoderTest()
    {
        Map<String, PasswordEncoder> encoder= new HashMap<>();
        encoder.put("bcrypt", new BCryptPasswordEncoder());
        encoder.put("scrypt", new SCryptPasswordEncoder());
        encoder.put("pbk", new Pbkdf2PasswordEncoder());
        System.out.println(new DelegatingPasswordEncoder("bcrypt", encoder).encode("ma"));
    }

}
