package net.valfridsson.gastolibro.jdbi;

import com.google.common.collect.ImmutableList;
import net.valfridsson.gastolibro.core.Entry;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.mixins.GetHandle;

public abstract class EntryDao implements GetHandle {

    @SqlQuery("SELECT * FROM entry e ORDER BY e.createTime DESC")
    public abstract ImmutableList<Entry> findAll();

    @SqlQuery("SELECT COUNT(*) FROM entry")
    public abstract long count();




}
