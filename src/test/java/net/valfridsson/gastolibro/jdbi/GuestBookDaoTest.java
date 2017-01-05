package net.valfridsson.gastolibro.jdbi;

import org.junit.Rule;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GuestBookDaoTest {

    @Rule
    public TestDB testDB = new TestDB();

    @Test
    public void findById_exists() throws Exception {
        assertThat(testDB.getDbi().onDemand(GuestBookDao.class).findById(10)).isPresent();
    }

    @Test
    public void findById_notExists() throws Exception {
        assertThat(testDB.getDbi().onDemand(GuestBookDao.class).findById(0)).isNotPresent();
    }
}