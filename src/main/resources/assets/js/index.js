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


//FUNCTIONS FOR ONE GASTRO ENTRY

function loadMessageInfo(contact) {

    var entry = entries[$(contact).attr("id").split("_")[1]];

    if ($(contact).data("view-state")) {
        $(contact).replaceWith(contactInfoDiv(entry.name, entry.country, entry.email, entry.id));
    } else {
        $(contact).replaceWith(contactInfoParagraph(entry.id));
    }
}


//Dynamic HTML elements
function contactInfoDiv(name, country, email, id) {

    console.log(email);
    var elem1 = "<p class='message-info-expand-name'> name: " + name + "</p>";
    var elem2 = "<p class='message-info-expand-country'> country: " + country + "</p>";
    var elem3 = "<p class='message-info-expand-email' data-email='" + email + "' onclick='openEmail(this)'> email: " + email + "</p>";

    var returnToContactOption = "<p class='message-info' onclick='loadMessageInfo(this.parentElement)'>minimize PS kommer formattera detta mkt bättre senare...</p>";

    return "<div class='message-info-expand' id='" + id + "' data-view-state='false'> " + elem1 + elem2 + elem3 + returnToContactOption + "</div>";

}

function contactInfoParagraph(id) {
    return "<p class='message-info' data-view-state='true' id='" + id + "' onclick='loadMessageInfo(this)'>Contact</p>";
}




//Utillities 


function openEmail(email) {
    //for Lulz atm
    console.log($(email).data("email"));
    console.log("hello");
    redirect("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
}

function redirect(url) {
    window.location.replace(url);
}



//Mock Data

entries["0"] = {name:"mr smith", country:"sweden" ,email:"Cool@s00permail.com", id:"entry_0"};

