
var entriesOnDisplay;
var oldestEntry;
var booddk;

/*
document.addEventListener("DOMContentLoaded", function() {
  var elements = document.getElementById("entry-date").innerHTML;

  this.entriesOnDisplay = elements.size();
  
  for (var i = 0; i < elements.size(); i++) {
    this.oldestEntry = Math.min(Date.parse(elements[i]));
  }
});
*/

$('.entry-form').on('submit', function() {
    
  var $form_input = $('#entry-form : input');

  var associative_array = {};

  $inputs.each(function() {
      associative_array[this.name] = $(this).val();
  });

  $.ajax({
    type: "POST",
    url: "localhost:3000/?bookId=10",
    data: JSON.stringify({ Entries : associative_array }),
    contentType: "application/json; charset=utf-8",
    dataType: "json",
    success: function(data){alert(data);},
    failure: function(errMsg) {
        alert(errMsg);
    }
  })

});

function BookNotViewable() {
  document.body.innerHTML = "<p> Book not Viewable :( </p>";
}



