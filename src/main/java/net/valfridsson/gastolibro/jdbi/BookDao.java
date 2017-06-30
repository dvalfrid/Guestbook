package net.valfridsson.gastolibro.jdbi;

import net.valfridsson.gastolibro.core.Book;
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

@RegisterMapper(BookDao.BookMapper.class)
public abstract class BookDao implements GetHandle {

    @SingleValueResult
    @SqlQuery("SELECT * FROM book WHERE id = :id")
    public abstract Optional<Book> findById(@Bind("id") long id);

    public static class BookMapper implements ResultSetMapper<Book> {

        @Override
        public Book map(int index, ResultSet rs, StatementContext ctx) throws SQLException {
            return new Book(rs.getLong("id"), rs.getString("name"), rs.getBoolean("view_able"), "Some description");
        }
    }
}
