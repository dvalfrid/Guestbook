
var entriesOnDisplay;
var oldestEntry;



document.addEventListener("DOMContentLoaded", function() {
  var elements = document.getElementById("entry-date").elements;

  this.entriesOnDisplay = elements.size();
  
  for (var i = 0; i < elements.size(); i++) {
    this.oldestEntry = Math.min(Date.parse(elements[i]));
  }
});


function LoadEntries() {
  var elements = document.getElementById("gastro-input").elements;
  var str = {};

  for (var i = 0; i < elements.size(); i++) {
    str[elements[i].name] = elements[i].value;
  }

  
  document.getElementById("input").innerHTML = str;

  var json = JSON.stringify(str);


  $.ajax({
  type : "POST",
  url : "http://localhost:3000/",
  contentType :"application/json; charSet=UTF-8",
  data : json,
  dataType : "json"
      })
  .done(function(data){
      console.log(data);
      })
  .fail(function(data) {
      console.log(data);
      })

}

function BookNotViewable() {
  document.body.innerHTML = "<p> Book not Viewable :( </p>";
}



