package com.SpringBoot.JournalService.Service;

import com.SpringBoot.JournalService.Entity.Journal;
import com.SpringBoot.JournalService.Entity.User;
import com.SpringBoot.JournalService.Repository.JournalRepository;
import com.SpringBoot.JournalService.Repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalService {

    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private UserService userService;

    public List<Journal> getAllEntries() {
        return journalRepository.findAll();
    }

    public void saveJournal(Journal journal, String username) {
        User user = userService.findByUserName(username);
        Journal savedJournal = journalRepository.save(journal);
        user.getJournals().add(savedJournal);
        userService.saveUser(user);
    }

    public void saveJournal(Journal journal) {
        journalRepository.save(journal);
    }

    public Optional<Journal> findJournalById1(ObjectId id) {
        return journalRepository.findById(id);
    }

    public Journal findJournalById2(ObjectId id) {
        for (Journal journal : journalRepository.findAll()) {
            if (journal.getId() == id) {
                return journal;
            }
        }
        return null;
    }

    public void deleteJournalOfUser(String username, ObjectId id) {
        User user = userService.findByUserName(username);
        user.getJournals().removeIf(journal -> journal.getId().equals(id));
        userService.saveUser(user); //saving the updated user
        journalRepository.deleteById(id);
    }
}