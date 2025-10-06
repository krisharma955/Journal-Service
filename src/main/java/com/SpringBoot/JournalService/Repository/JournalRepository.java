package com.SpringBoot.JournalService.Repository;

import com.SpringBoot.JournalService.Entity.Journal;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface JournalRepository extends MongoRepository<Journal, ObjectId> {
}
