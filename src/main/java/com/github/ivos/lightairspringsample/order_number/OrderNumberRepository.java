package com.github.ivos.lightairspringsample.order_number;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderNumberRepository extends JpaRepository<OrderNumber, Integer> {
}
