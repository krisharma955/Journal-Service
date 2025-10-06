package com.SpringBoot.JournalService.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "journalServiceDB")
public class Journal {

    @Id
    private ObjectId id;

    @NonNull
    private String title;
    @NonNull
    private String content;

    public Journal(ObjectId id, @NonNull String title, @NonNull String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

}
