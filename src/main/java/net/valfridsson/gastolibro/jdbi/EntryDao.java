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

@SuppressWarnings("WeakerAccess")
@RegisterMapper(EntryDao.EntryMapper.class)
public abstract class EntryDao implements GetHandle {

    @SqlQuery("SELECT * FROM entry e ORDER BY e.createTime DESC")
    public abstract ImmutableList<Entry> findAll();

    public Entry insert(CreateEntry createEntry, long guestBookId, String ip) {
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
                .build(), guestBookId);
            return findById(id).orElse(null);
        });
    }

    @SqlQuery("SELECT nextval('entrySequence')")
    abstract long nextId();

    @SingleValueResult
    @SqlQuery("SELECT * FROM entry WHERE id = :id")
    abstract Optional<Entry> findById(@Bind("id") long id);

    @SqlUpdate("INSERT INTO entry (id,name,ip,headline,email,city,country,message,createTime,viewAble,guestBookId) " +
        "values (:e.id,:e.name,:e.ip,:e.headline,:e.email,:e.city,:e.country,:e.message,:e.createTime,:e.viewAble,:guestBookId)")
    abstract void doInsert(@BindFields("e") Entry entry, @Bind("guestBookId") long guestBookId);

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
                .createTime(rs.getTimestamp("createTime").toLocalDateTime())
                .viewAble(rs.getBoolean("viewAble"))
                .build();
        }
    }
}
