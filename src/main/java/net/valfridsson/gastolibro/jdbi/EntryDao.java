package net.valfridsson.gastolibro.jdbi;

import com.google.common.collect.ImmutableList;
import net.valfridsson.gastolibro.api.CreateEntry;
import net.valfridsson.gastolibro.core.Entry;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.sqlobject.customizers.SingleValueResult;
import org.skife.jdbi.v2.sqlobject.mixins.GetHandle;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

@RegisterMapper(EntryDao.EntryMapper.class)
public abstract class EntryDao implements GetHandle {

    @SqlQuery("SELECT * FROM entry e ORDER BY e.time DESC")
    abstract ImmutableList<Entry> findAll();


    @SqlQuery("SELECT * FROM entry e where e.id > :from ORDER BY e.time DESC limit :limit ")
    public abstract ImmutableList<Entry> findX(@Bind("limit") long limit,
                                               @Bind("from") long from);

    @SingleValueResult
    @SqlQuery("SELECT * FROM entry e WHERE e.book_id = :bookId AND e.id = :entryId")
    public abstract Optional<Entry> findBy(@Bind("bookId") long bookId,
                                           @Bind("entryId") long entryId);

    public Entry insert(CreateEntry createEntry) {
        return getHandle().inTransaction((conn, status) -> {
            long id = nextId();
            doInsert(Entry.newBuilder()
                .id(id)
                .name(createEntry.name)
                .message(createEntry.text)
                .city(createEntry.city)
                .country(createEntry.country)
                .email(createEntry.email)
                .headline(createEntry.title)
                .time(LocalDateTime.now())
                .build());
            return findById(id).orElse(null);
        });
    }

    @SqlQuery("SELECT nextval('entry_sequence')")
    abstract long nextId();

    @SingleValueResult
    @SqlQuery("SELECT * FROM entry WHERE id = :id")
    abstract Optional<Entry> findById(@Bind("id") long id);

    @SqlUpdate("INSERT INTO entry (id,name,title,email,city,country,text,time) " +
        "values (:e.id,:e.name,:e.title,:e.email,:e.city,:e.country,:e.text,:e.time)")
    abstract void doInsert(@BindFields("e") Entry entry);

    public static class EntryMapper implements ResultSetMapper<Entry> {

        @Override
        public Entry map(int index, ResultSet rs, StatementContext ctx) throws SQLException {
            return Entry.newBuilder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .headline(rs.getString("title"))
                .email(rs.getString("email"))
                .city(rs.getString("city"))
                .country(rs.getString("country"))
                .message(rs.getString("text"))
                .time(rs.getTimestamp("time").toLocalDateTime())
                .build();
        }
    }
}
