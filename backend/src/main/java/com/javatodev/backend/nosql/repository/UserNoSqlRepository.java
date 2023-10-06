package com.javatodev.backend.nosql.repository;

import com.javatodev.backend.nosql.entity.UserNoSql;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserNoSqlRepository extends MongoRepository<UserNoSql,Long> {
}
