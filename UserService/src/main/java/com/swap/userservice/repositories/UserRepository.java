package com.swap.userservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.swap.userservice.entities.User;

//Note that we do not need to add @Repository annotation because Spring Data JPA internally takes care of it.
public interface UserRepository extends JpaRepository<User, String> {

	//if you want to create any custom method or query write.
}
