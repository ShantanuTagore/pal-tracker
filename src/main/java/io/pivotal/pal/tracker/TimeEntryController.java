package io.pivotal.pal.tracker;

import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping(value = "/time-entries", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry request) {
        TimeEntry timeEntryResponse = timeEntryRepository.create(new TimeEntry());
        return new ResponseEntity(timeEntryResponse, CREATED);
    }

    public ResponseEntity<TimeEntry> read(long id) {
        TimeEntry timeEntry = timeEntryRepository.find(id);
        return new ResponseEntity(timeEntry, timeEntry!=null? OK: NOT_FOUND);
    }

    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity<>(timeEntryRepository.list(), OK);
    }

    public ResponseEntity<TimeEntry> update(long id, TimeEntry timeEntry) {
        TimeEntry timeEntryResponse = timeEntryRepository.update(id, timeEntry);
        return new ResponseEntity(timeEntryResponse, timeEntryResponse!=null? OK: NOT_FOUND);
    }

    public ResponseEntity delete(long id) {
        timeEntryRepository.delete(id);
        return new ResponseEntity(NO_CONTENT);
    }
}
