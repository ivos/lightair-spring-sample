package com.github.ivos.lightairspringsample.order_number;

import com.github.ivos.lightairspringsample.config.Logged;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Logged
public class OrderNumberService {

	private final OrderNumberRepository repo;

	public OrderNumberService(OrderNumberRepository repo) {
		this.repo = repo;
	}

	@Transactional
	public Integer getNextSequence(Integer year) {
		return repo.findById(year)
				.map(orderNumber -> {
					Integer lastSequence = orderNumber.getLastSequence();
					Integer nextSequence = lastSequence + 1;

					orderNumber.setLastSequence(nextSequence);

					return nextSequence;
				})
				.orElseGet(() -> {
					Integer sequence = 1;
					OrderNumber orderNumber = new OrderNumber();
					orderNumber.setYear(year);
					orderNumber.setLastSequence(sequence);

					repo.save(orderNumber);

					return sequence;
				});
	}
}
