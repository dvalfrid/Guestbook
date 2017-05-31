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

    @SqlQuery("SELECT * FROM entry e WHERE e.book_id = :bookId ORDER BY e.create_time DESC")
    public abstract ImmutableList<Entry> findAll(@Bind("bookId") long bookId);


    @SqlQuery("SELECT * FROM entry e where e.book_id = :bookId AND e.create_time < :from ORDER BY e.create_time DESC limit :limit ")
    public abstract ImmutableList<Entry> findX(@Bind("bookId") long bookId,
                                               @Bind("limit") long limit,
                                               @Bind("from") LocalDateTime from);

    public Entry insert(CreateEntry createEntry, long bookId, String ip) {
        return getHandle().inTransaction((conn, status) -> {
            long id = nextId();
            doInsert(Entry.newBuilder()
                .id(id)
                .name(createEntry.name)
                .message(createEntry.message)
                .city(createEntry.city)
                .country(createEntry.country)
                .email(createEntry.email)
                .headline(createEntry.headline)
                .ip(ip)
                .viewAble(true)
                .createTime(LocalDateTime.now())
                .build(), bookId);
            return findById(id).orElse(null);
        });
    }

    @SqlQuery("SELECT nextval('entry_sequence')")
    abstract long nextId();

    @SingleValueResult
    @SqlQuery("SELECT * FROM entry WHERE id = :id")
    abstract Optional<Entry> findById(@Bind("id") long id);

    @SqlUpdate("INSERT INTO entry (id,name,ip,headline,email,city,country,message,create_time,view_able,book_id) " +
        "values (:e.id,:e.name,:e.ip,:e.headline,:e.email,:e.city,:e.country,:e.message,:e.createTime,:e.viewAble,:bookId)")
    abstract void doInsert(@BindFields("e") Entry entry, @Bind("bookId") long bookId);

    public static class EntryMapper implements ResultSetMapper<Entry> {

        @Override
        public Entry map(int index, ResultSet rs, StatementContext ctx) throws SQLException {
            return Entry.newBuilder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .ip(rs.getString("ip"))
                .headline(rs.getString("headline"))
                .email(rs.getString("email"))
                .city(rs.getString("city"))
                .country(rs.getString("country"))
                .message(rs.getString("message"))
                .createTime(rs.getTimestamp("create_time").toLocalDateTime())
                .viewAble(rs.getBoolean("view_able"))
                .build();
        }
    }
}
