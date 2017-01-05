package net.valfridsson.gastolibro.jdbi;

import net.valfridsson.gastolibro.core.GuestBook;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.sqlobject.customizers.SingleValueResult;
import org.skife.jdbi.v2.sqlobject.mixins.GetHandle;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@RegisterMapper(GuestBookDao.GuestBookMapper.class)
public abstract class GuestBookDao implements GetHandle {

    @SingleValueResult
    @SqlQuery("SELECT * FROM guestBook WHERE id = :id")
    public abstract Optional<GuestBook> findById(@Bind("id") long id);

    public static class GuestBookMapper implements ResultSetMapper<GuestBook> {

        @Override
        public GuestBook map(int index, ResultSet rs, StatementContext ctx) throws SQLException {
            return new GuestBook(rs.getLong("id"), rs.getString("name"), rs.getBoolean("viewAble"));
        }
    }
}
