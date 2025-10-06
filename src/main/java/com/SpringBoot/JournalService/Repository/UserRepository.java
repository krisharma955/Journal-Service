package com.SpringBoot.JournalService.Repository;

import com.SpringBoot.JournalService.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {

        User findByUsername(String username);
}
