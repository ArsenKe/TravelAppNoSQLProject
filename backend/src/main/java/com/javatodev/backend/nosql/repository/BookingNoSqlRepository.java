package com.javatodev.backend.nosql.repository;

import com.javatodev.backend.nosql.entity.BookingNoSql;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingNoSqlRepository extends MongoRepository<BookingNoSql,Long> {
}
