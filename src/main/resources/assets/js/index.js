
var bookId;
var entries = {};

//Parsing Queries
function urlKeys(key) {
    key = key.replace(/[*+?^$.\[\]{}()|\\\/]/g, "\\$&"); // escape RegEx meta chars
    var match = location.search.match(new RegExp("[?&]"+key+"=([^&]+)(&|$)"));
    return match && decodeURIComponent(match[1].replace(/\+/g, " "));
}

//TODO: Make sure input is correctly formated. NEED to choose a mail REGEX
function validations(entry) {
    return true;
}

//--------------------------------------------------------------------------------------------------------------------------------------------

//ONLOAD Functions

$(document).ready(function() {
    //TODO: Load Initial Content
    //$(".title-text").text(" Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.");
    //$(".page-title").text("Gastolibro");

    //for(i = 0; i < 10; i++) {
        //createEntry(entries[0]);
    //}

    bookId = urlKeys("bookId");

    $.get("/api/books/" + bookId, function(book, status) {
        $(".page-title").text(book.title);
        $(".title-text").text(book.description);
        book.entries.forEach(CreateEntry);
    });

    if (bookId == null) {
        crash(0);
    }
});



//--------------------------------------------------------------------------------------------------------------------------------------------

//API Functions

function requestEntries(numberOfEntries, oldestEntryId) {
    return $.get("/api/books/" + bookId + "/entries/" + "?numberOfEntries=" + numberOfEntries  + "&?afterId=" + oldestEntryId,
        function( entries ) {
            return entries;
        });
}

function requestEntry(entryId) {
    return $.get("/api/books/" + bookId + "entries/" + entryId,
        function ( entry ) {
            return entry;
        });
}

function fuzzySearch(str) {
    return $.get("/api/books/" + bookId + "/search/?snippet=" + str,
        function ( objects ) {
            return objects;
        });
}

function addEntrytoDB(entry) {
    $.post("/api/books/" + bookId + "/entries", entry, function(data, status){
        if (status == 200)
            return true;
        else
            return false;
    });
}


//--------------------------------------------------------------------------------------------------------------------------------------------
//Scrolling function

$(function($) {
    $(".div-entries").on('scroll', function() {
        if($(this).scrollTop() + $(this).innerHeight() >= $(this)[0].scrollHeight - 60) { /*Generate Content before it hits bottom*/
            requestEntries(5, entries[-1].id).forEach(createEntry);

            //for (i = 0; i < 5; i++) {
                //createEntry(entries[0]);
            //}
        }
    })

});
//--------------------------------------------------------------------------------------------------------------------------------------------
//Adding Entry
function submitHandler() {
    entry = getSubmissionContent();

    if (validations(entry)) {
        addEntrytoDB(entry);
        //location.reload(); //Reload content to view input
    }
}

function getSubmissionContent() {
    var entry = {};
    $("form#entry-input :input").each(function() {
        entry[$(this).attr("id")] = $(this).attr("value");
    });

    return entry;
}


//--------------------------------------------------------------------------------------------------------------------------------------------

//DYNAMIC CONTENT

function createEntry(entry) {
    entries[entry.id] = entry;

    $(".div-entries").append(`
        <div class='div-entry'>
            <div class='div-title-date'>
                <h3 class='display-entry-title'><em>${entry.header}</em></h3>
                <h5 class='display-entry-date'>${entry.date}</h5>
            </div>
            <p class='display-entry-message'>${entry.message}</p>
            <div class='div-contact-response'>
                <p class='display-entry-contact' data-view-state='true' data-id=${entry.id} onclick='loadMessageInfo(this)'>Contact</p>
                <p class='display-entry-response'>${entry.comment}</p>
            </div>
        </div>
      `)
}

function contactInfoDiv(contactInfo) {
    return `
        <div class='div-entry-contact' data-id=${contactInfo.id} data-view-state='false'>
            <p class='entry-contact-name'> name: ${contactInfo.name}</p>
            <p class='entry-contact-country'> counter: ${contactInfo.country}</p>
            <p class='entry-contact-email' data-email='${contactInfo.email}' onclick='openEmail(this)'> email: ${contactInfo.email} </p>
            <p class='div-contact-hide' onclick='loadMessageInfo(this.parentElement)'>hide</p>
        </div>
    `
}

function contactInfoParagraph(contactInfo) {
    return  `
        <p class='display-entry-contact' data-view-state='true' data-id=${contactInfo.id} onclick='loadMessageInfo(this)'>Contact</p>
    `
}

function crashHtml(string) {
 return `
    <head>
      <link href="css/index.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <p>${string}</p>
        <div class='crash-image-div'>
            <img class='crash-image'>
        </div>
    </body>
 `
}


//--------------------------------------------------------------------------------------------------------------------------------------------

//Managing the Dynamic content

function loadMessageInfo(contact) {
    console.log($(contact).data("id"));
    var entry = entries[$(contact).data("id")];

    if ($(contact).data("view-state")) {
        $(contact).replaceWith(contactInfoDiv(entry));
    } else {
        $(contact).replaceWith(contactInfoParagraph(entry));
    }
}

toolsVisible = false; // If it works it ain't stupid #ThugLife
function toolsManagement() {
    if (toolsVisible) {
        $(".div-entry-input").fadeOut(200);
        $(".div-entries").css("width", "100%");
        $(".div-entries").fadeIn(200)
        toolsVisible = false;
    } else {
        $(".div-entries").fadeOut(200);
        $(".div-entries").css("width", "0%");

        $(".div-entry-input").fadeIn(200);
        $(".div-entry-input").css("width", "100%");
        toolsVisible = true;
    }
}

mainpage = true;
function pageHandler() {
    console.log("HELLO");
    if(mainpage) {
        mainpage = false;
        $(".div-top").fadeOut(200);
        $(".div-main-content-wrapper").fadeOut(200);
        $(".div-search-page").fadeIn(400);
    } else {
        mainpage = true;
        $(".div-search-page").fadeOut(200);
        $(".div-main-content-wrapper").fadeIn(400);
        $(".div-top").fadeIn(400);
    }
}

function crash(code) {
 switch(code) {
    case 0:
        document.documentElement.innerHTML = crashHtml("Could not find book, or book not defined :(");
        break;
    default:
        console.log("unknown Error :(");
 }
}



//--------------------------------------------------------------------------------------------------------------------------------------------

//Tooling

function openEmail(email) {
    //for Lulz atm
    console.log($(email).data("email"));
    console.log("hello");
    redirect("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
}

function redirect(url) {
    window.location.replace(url);
}

//--------------------------------------------------------------------------------------------------------------------------------------------

//AutoCompletion


//$(".gastro-tools-search-bar").autocomplete({
    //source:autocompletion
//});

//--------------------------------------------------------------------------------------------------------------------------------------------


//Mock Data

var autocompletion = ["gastrolibro", "ranodom", "javaisNotAsCoolAsC++", "HaskellIsWierd"];

entries[0] = {name:"mr smith", country:"sweden" ,email:"Cool@s00permail.com", comment:"Coolaste inlägget ever!", date:"1337-12-13 13:37", id:"0", header:"S00perDynamicHeader", message:"AS000000perDynamicMessage"};



//--------------------------------------------------------------------------------------------------------------------------------------------
//EVERYTHING SEARCH





