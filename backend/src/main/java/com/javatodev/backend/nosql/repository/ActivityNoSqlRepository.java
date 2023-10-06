package com.javatodev.backend.nosql.repository;

import com.javatodev.backend.nosql.entity.ActivityNoSql;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityNoSqlRepository extends MongoRepository<ActivityNoSql,Long> {
}
