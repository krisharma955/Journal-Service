package com.SpringBoot.JournalService.Entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "userDB")
public class User {

    @Id
    private ObjectId id;

    @NonNull
    @Indexed(unique = true)
    private String username;
    @NonNull
    private String password;

    @DBRef
    private List<Journal> journals = new ArrayList<>();

    private List<String> roles;

}
