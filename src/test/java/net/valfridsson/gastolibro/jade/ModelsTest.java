package net.valfridsson.gastolibro.jade;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import jersey.repackaged.com.google.common.collect.ImmutableList;
import net.valfridsson.gastolibro.core.Book;
import net.valfridsson.gastolibro.core.Entry;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class ModelsTest {

  @Test
  public void kalle() throws IOException {

    ImmutableList<Entry> entry = ImmutableList
      .<Entry>builder()
      .add(Entry.newBuilder()
          .id(103)
          .name("Smith")
          .ip("IPSmith")
          .headline("HeadlineSmith")
          .email("EmailSmith")
          .city("CitySmith")
          .country("CountrySmith")
          .message("MessageSmith")
          .viewAble(true)
          .build())
          .build();
 
 
   Book book = new Book(103, "Smith", true);

    String html = Models.buildModel
      ("main.jade")
      .data(GastroJadeConfig.getInstance().EntryKey, entry)
      .data(GastroJadeConfig.getInstance().BookKey, book)
      .render();


    assertNotNull(html);
  }
}

