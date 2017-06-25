
var bookId;

$(document).ready(function() {
    //TODO: Load Initial Content
    for(i = 0; i < 10; i++) {
        CreateEntry(entries[0]);
    }
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


//DYNAMIC HTML

//entry html

function loadMessageInfo(contact) {

    var entry = entries[$(contact).attr("id").split("_")[1]];

    if ($(contact).data("view-state")) {
        $(contact).replaceWith(contactInfoDiv(entry.name, entry.country, entry.email, entry.id));
    } else {
        $(contact).replaceWith(contactInfoParagraph(entry.id));
    }
}


//Dynamic HTML elements entry
function contactInfoDiv(name, country, email, id) {

    console.log(email);
    var elem1 = "<p class='message-info-expand-name'> name: " + name + "</p>";
    var elem2 = "<p class='message-info-expand-country'> country: " + country + "</p>";
    var elem3 = "<p class='message-info-expand-email' data-email='" + email + "' onclick='openEmail(this)'> email: " + email + "</p>";

    var returnToContactOption = "<p class='message-info' onclick='loadMessageInfo(this.parentElement)'>hide</p>";

    return "<div class='message-info-expand' id='entry_" + id + "' data-view-state='false'> " + elem1 + elem2 + elem3 + returnToContactOption + "</div>";

}

function contactInfoParagraph(id) {
    return "<p class='message-info' data-view-state='true' id='entry_" + id + "' onclick='loadMessageInfo(this)'>Contact</p>";
}


toolsVisible = true;

function toolsManagement() {
    if (toolsVisible) {
        $(".gastro-tools").fadeOut(200);
        $(".gastro-entries").css("width", "100%");
        $(".gastro-tools-management").fadeOut(100).text("show").fadeIn(100);

        toolsVisible = false;
    } else {
        $(".gastro-entries").css("width", "80%");

        $(".gastro-tools").fadeIn(2000);
        $(".gastro-tools-management").fadeOut(100).text("hide").fadeIn(100);
        toolsVisible = true;
    }

}




function openEmail(email) {
    //for Lulz atm
    console.log($(email).data("email"));
    console.log("hello");
    redirect("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
}

function redirect(url) {
    window.location.replace(url);
}




//ENTRY CREATING



function CreateEntry(entry) {

    title = "<h3 class='message-header'><em> " + entry.header + "</em></h3>";
    message = "<p class='message'>" + entry.message + "</p>";
    contact = "<p class='message-info' data-view-state='true' id='entry_" + entry.id + "' onclick='loadMessageInfo(this)'>Contact</p>";

    holder = "<div class='gastro-entry'>" + title + message + contact + "</div>";

    $(".gastro-entries").append(holder);
}


jQuery(function($) {
    $(".gastro-entries").on('scroll', function() {
        if($(this).scrollTop() + $(this).innerHeight() >= $(this)[0].scrollHeight) {
            //TODO Dynamically generate more Entries.
            for (i = 0; i < 5; i++) {
                CreateEntry(entries[0]);
            }
        }
    })

});





//Mock Data


var autocompletion = ["gastrolibro", "ranodom", "javaisNotAsCoolAsC++", "HaskellIsWierd"];

entries[0] = {name:"mr smith", country:"sweden" ,email:"Cool@s00permail.com", id:"0", header:"S00perDynamicHeader", message:"AS000000perDynamicMessage"};


//FUZZY SEARCHING


$(".gastro-tools-search-bar").autocomplete({
    source:autocompletion
});
