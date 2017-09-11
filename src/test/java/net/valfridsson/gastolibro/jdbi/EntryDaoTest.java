package net.valfridsson.gastolibro.jdbi;

import com.google.common.collect.ImmutableList;
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
        Entry entry1 = entryDao.insert(getCreateEntry());
        Entry entry2 = entryDao.insert(getCreateEntry());
        Entry entry3 = entryDao.insert(getCreateEntry());
        Entry entry4 = entryDao.insert(getCreateEntry());

        assertThat(entryDao.findAll()).contains(entry1, entry2, entry3, entry4);
    }

//TODO:Fix this test, sometimes fails.. sometimes doesnt!:P
    @Test
    public void findX() throws Exception {
        entryDao.insert(getCreateEntry());
        entryDao.insert(getCreateEntry());
        entryDao.insert(getCreateEntry());
        entryDao.insert(getCreateEntry());
        entryDao.insert(getCreateEntry());
        entryDao.insert(getCreateEntry());
        Entry entry7 = entryDao.insert(getCreateEntry());
        Entry entry8 = entryDao.insert(getCreateEntry());

        ImmutableList<Entry> x = entryDao.findX(10, 6);
        assertThat(x).contains(entry8, entry7);
    }

    @Test
    public void insert() throws Exception {
        Entry insert = entryDao.insert(getCreateEntry());
        assertThat(insert.title).isEqualTo("title");
    }

    @Test
    public void nextId() throws Exception {
        assertThat(entryDao.nextId()).isPositive();
    }

    private static CreateEntry getCreateEntry() {
        return new CreateEntry("title", "text", "name", "name@mail.com", "city", "country");
    }
}
