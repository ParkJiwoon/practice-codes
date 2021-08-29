package com.example.springjpa.batch_size;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepository extends JpaRepository<Parent, Long> {
}
