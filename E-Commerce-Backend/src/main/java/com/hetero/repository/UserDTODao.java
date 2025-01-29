package com.hetero.repository;


import com.hetero.models.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDTODao extends JpaRepository<UserDTO, Long> {

    UserDTO findByUsername(String username);


}
