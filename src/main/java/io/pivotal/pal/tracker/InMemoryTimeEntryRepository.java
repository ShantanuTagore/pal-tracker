package io.pivotal.pal.tracker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private Map<Long, TimeEntry> timeEntryMap = new HashMap<>();
    private long id = 1;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        timeEntry.setId(this.id);
        timeEntryMap.put(this.id, timeEntry);
        this.id++;
        return timeEntry;
    }

    @Override
    public TimeEntry find(long id) {
        return timeEntryMap.get(id);
    }

    @Override
    public List<TimeEntry> list() {
        return timeEntryMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        if (timeEntryMap.get(id) != null) {
            timeEntry.setId(id);
            timeEntryMap.put(id, timeEntry);
            return timeEntryMap.get(id);
        } else {
            return null;
        }
    }

    @Override
    public void delete(long id) {
        timeEntryMap.remove(id);
    }
}
