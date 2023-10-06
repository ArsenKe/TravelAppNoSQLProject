package com.javatodev.backend.nosql.repository;

import com.javatodev.backend.nosql.entity.RatingNoSql;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingNoSqlRepository extends MongoRepository<RatingNoSql,Long> {
}
