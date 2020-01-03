package com.github.ivos.lightairspringsample.customer;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	@Query("select c from Customer c"
			+ " where (:search is null"
			+ "  or c.name like concat(cast(:search as text), '%')"
			+ "  or c.taxNumber like concat(cast(:search as text), '%')"
			+ "  or c.mobile like concat(cast(:search as text), '%')"
			+ "  or c.email like concat(cast(:search as text), '%')"
			+ "  or c.web like concat(cast(:search as text), '%')"
			+ " )"
			+ " order by c.name, c.id")
	List<Customer> list(@Param("search") String search, Pageable pageable);
}
