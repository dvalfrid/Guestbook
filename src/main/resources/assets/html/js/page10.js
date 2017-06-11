$(window).on('load', function() {
  bookId = $('meta[name=bookId]').attr("content");
});

var bookId;

function post_input() {

  entries = {}

  entries["name"] =  document.getElementById("name").value;
  entries["email"] = document.getElementById("email").value;
  entries["message"] = document.getElementById("message").value;

  $.ajax({
    type: "POST",
    url: "/books/" + bookId + "/entries",
    data: JSON.stringify(entries),
    contentType: "application/json; charset=utf-8",
    dataType: "json",
    success: function(data){alert(data);},
    failure: function(errMsg) {
      alert(errMsg);
    }
  });

}

function show_entry(entry) {
  $(".entries")
    .append("<div id='" + entry.id + "' class='container-fluid' class='entry'></div>");

  $("#" + entry.id.toString())
    .append("<h3 id='headline'>" + entry.headline + "</h3>")
    .append("<p id='p-message'>" + entry.message + "</p>")
    .append("<p id='p-name'>" + entry.name + "</p>")
    .append("<p id='p-create'>" + entry.createTime + "</p>")
}


function updateEntries(entries) {
  for (entry in entries) {
    show_entry(entry);
  }
}

function requestEntries(numberOfEntries) {

  updateRequest ={}
  entries["id"] = bookId;
  entries["x"] = numberOfEntries;
  entries["date"] = Date.now;

  $.ajax({
    type: "POST"
    url: "/books/"+ bookId + "/entries";
    data: JSON.stringify(updateRequest),
    contentType: "application/json; charset=utf-8",
    dataType: "json",
    success: updateEntries(data),
    failure: function(errMsg) {
      alert(errMsg);
    }
  });

}

