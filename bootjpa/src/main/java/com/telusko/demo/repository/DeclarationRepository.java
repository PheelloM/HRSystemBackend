package com.telusko.demo.repository;

import com.telusko.demo.model.Declaration;
import org.springframework.data.repository.CrudRepository;

public interface DeclarationRepository extends CrudRepository<Declaration, Long> {
}
