package net.valfridsson.gastolibro.jdbi;

import net.valfridsson.gastolibro.api.CreateEntry;
import net.valfridsson.gastolibro.core.Entry;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EntryDaoTest {

    @Rule
    public TestDB testDB = new TestDB();
    private EntryDao entryDao;

    @Before
    public void before() {
        entryDao = testDB.getDbi().onDemand(EntryDao.class);
    }

    @Test
    public void findAll() throws Exception {
        Entry entry1 = entryDao.insert(getCreateEntry(), 1L, "192.168.10.10");
        Entry entry2 = entryDao.insert(getCreateEntry(), 1L, "192.168.10.10");
        Entry entry3 = entryDao.insert(getCreateEntry(), 1L, "192.168.10.10");
        Entry entry4 = entryDao.insert(getCreateEntry(), 1L, "192.168.10.10");

        assertThat(entryDao.findAll()).containsOnly(entry1, entry2, entry3, entry4);
    }

    @Test
    public void insert() throws Exception {
        Entry insert = entryDao.insert(getCreateEntry(), 1L, "192.168.10.10");
        assertThat(insert.ip).isEqualTo("192.168.10.10");
    }

    @Test
    public void nextId() throws Exception {
        assertThat(entryDao.nextId()).isPositive();
    }

    private CreateEntry getCreateEntry() {
        return new CreateEntry("name", "headline", "name@mail.com", "city", "country", "message");
    }
}