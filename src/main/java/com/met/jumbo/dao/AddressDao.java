package com.met.jumbo.dao;

import com.met.jumbo.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressDao extends JpaRepository<Address, Integer> {
    @Query("SELECT zipcode FROM Address")
    Optional<List<String>> findByZipcodeLike(String zipcode);

    @Query("SELECT city FROM Address")
    Optional<List<String>> findByCityLike(String city);
}
