package com.creditsuisse.assignment.repository;

import com.creditsuisse.assignment.entity.EventEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<EventEntity, String> {
}
