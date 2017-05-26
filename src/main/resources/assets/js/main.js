function LoadEntries() {
  var elements = document.getElementById("gastro-input").elements;
  var str = {};

  for (var i = 0; i < elements.size(); i++) {
    str[elements[i].name] = elements[i].value;
  }

  
  document.getElementById("input").innerHTML = str;

  var json = JSON.stringify(str);

  var xhr = new XMLHttpRequest();
  xhr.open(document.getElementById('gastro-input').method,
          document.getElementById('gastro-input').action);
  xhr.setRequestHeader("Content-type", "application/json");
  xhr.setRequestHeader("Content-Length",json.length);
  xhr.setRequestHeader('Accept', 'application/json');


  xhr.send(json);


}
