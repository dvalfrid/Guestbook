
var bookId;

$(document).ready(function() {

    //TODO: Load Initial Content


});



function requestEntries(numberOfEntries, oldestEntryId) {
    return $.get("/home/" + bookId + "/request?" + numberOfEntries + "?" + oldestEntryId,
        function( entries ) {
            return entries;
        });
}

function requestEntry(entryId) {
    return $.get("/home/" + bookId + "/request?" + entryId,
        function ( entry ) {
            return entry;
        });
}

function fuzzySearch(str) {
    return $.get("/home/" + bookId + "/search?" + str,
        function ( objects ) {
            return objects;
        });
}


function addEntrytoDB(entryContent) {
    $.ajax({
        type: "POST",
        url: "/home/"+ bookId + "/create",
        data: JSON.stringify(entryContent),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data) { alert(data); },
        failure: function(errMsg) {
            alert(errMsg);
        }
    });
}



var entries = {};

function loadMessageInfo(paraph) {
   var entry = entries[$(paraph).attr(" id ").split("_")[1]];

   $(paraph).text(entry.email);


}





