package com.stanford.springjwt.repository;


import com.stanford.springjwt.models.UsersProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersProfileRepository extends JpaRepository<UsersProfile, Long> {
}
