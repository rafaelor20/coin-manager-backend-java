package com.example.coinmanager.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.coinmanager.model.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class UsersRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository usersRepository;

    @Test
    public void testSaveUser() {
        // Create a new user
        UserEntity user = new UserEntity();
        user.setUsername("John Doe");
        user.setEmail("john.doe@example.com");

        // Save the user
        UserEntity savedUser = usersRepository.save(user);

        // Verify the user is saved with an ID
        assertNotNull(savedUser.getId());

        // Verify the saved user matches the original user
        assertEquals(user.getUsername(), savedUser.getUsername());
        assertEquals(user.getEmail(), savedUser.getEmail());
    }
}