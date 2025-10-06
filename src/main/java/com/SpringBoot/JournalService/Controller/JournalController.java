package com.SpringBoot.JournalService.Controller;

import com.SpringBoot.JournalService.Entity.Journal;
import com.SpringBoot.JournalService.Entity.User;
import com.SpringBoot.JournalService.Service.JournalService;
import com.SpringBoot.JournalService.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalController {

    @Autowired
    private JournalService journalService;

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<?> getAllJournalsOfUser(@PathVariable String username) {
        User user = userService.findByUserName(username);
        List<Journal> journalList = user.getJournals();
        if(journalList != null) {
            return new ResponseEntity<>(journalList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getJournalById(@PathVariable ObjectId id) {
        Optional<Journal> journal = journalService.findJournalById1(id);
        if(journal.isPresent()) {
            return new ResponseEntity<>(journal, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{username}")
    public ResponseEntity<?> addJournal(@RequestBody Journal newJournal, @PathVariable String username) {
        try {
            journalService.saveJournal(newJournal, username);
            return new ResponseEntity<>(newJournal, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{username}/{id}")
    public ResponseEntity<?> updateJournal(@RequestBody Journal updatedJournal, @PathVariable String username, @PathVariable ObjectId id) {
        Optional<Journal> journal = journalService.findJournalById1(id);
        if(journal.isPresent()) {
            Journal oldJournal = journal.get();
            oldJournal.setTitle(!updatedJournal.getTitle().isEmpty() ? updatedJournal.getTitle() : oldJournal.getTitle());
            oldJournal.setContent(!updatedJournal.getContent().isEmpty() ? updatedJournal.getContent() : oldJournal.getContent());
            journalService.saveJournal(oldJournal);
            return new ResponseEntity<>(oldJournal, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{username}/{id}")
    public ResponseEntity<?> deleteJournal(@PathVariable String username, @PathVariable ObjectId id) {
        try {
            journalService.deleteJournalOfUser(username, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
