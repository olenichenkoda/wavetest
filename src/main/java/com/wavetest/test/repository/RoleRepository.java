package com.wavetest.test.repository;

import com.wavetest.test.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("Select new com.wavetest.test.entity.Role(r.id, r.nameRole) from Role r where r.nameRole = :nameRole")
    Role getByNameRole(@Param("nameRole") String nameRole);
}
