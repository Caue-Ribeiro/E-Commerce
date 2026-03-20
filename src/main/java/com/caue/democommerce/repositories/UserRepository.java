package com.caue.democommerce.repositories;

import com.caue.democommerce.entities.User;
import com.caue.democommerce.projections.UserDetailsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    @Query(nativeQuery = true,value = """
            
            SELECT
                u.id,
                u.email,
                u.name,
                u.password,
                r.id,
                r.authority
            FROM
                tb_user u
            JOIN tb_user_role ur ON ur.user_id = u.id
            JOIN tb_role r ON ur.role_id = r.id
            WHERE u.email = :email
            
            """)
    List<UserDetailsProjection> searchUserAndRolesByEmail(String email);

    Optional<User> findByEmail(String email);
}
