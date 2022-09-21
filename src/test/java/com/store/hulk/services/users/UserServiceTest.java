package com.store.hulk.services.users;

import com.store.hulk.models.users.UserHulk;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService service;

    @Test
    void save() {

        UserHulk user_admin=new UserHulk(
                999,
                "amosquera",
                "Aldair",
                "Mosquera",
                "Murillo",
                "123");
        user_admin=service.save(user_admin);
        assertThat(user_admin.getId()).isGreaterThan(0);

    }

    @Test
    void findByUserName() {
        UserHulk hulk = service.findByUserName("amosquera");
        assertThat(hulk).isNotNull();
    }

    @Test
    void getAll() {
        Collection<UserHulk> all = (Collection<UserHulk>) service.getAll();
        assertThat(all.size()).isGreaterThan(0);
    }

    @Test
    void typeHeadSearch() {
        Collection<UserHulk> all = (Collection<UserHulk>) service.typeHeadSearch("aldair");
        assertEquals(all.size(),1);
    }
}