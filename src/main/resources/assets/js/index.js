//Global Variables
var bookId;
var entries = {};
var countries = ["sweden", "swizz", "sri lanka", "s00perLand", "super marioland"];
var cities = ["derpington", "rainbowislands"];
var emailRegex = new RegExp("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");

//Mock Data
entries[0] = {name:"mr smith", country:"sweden" ,email:"Cool@s00permail.com", comment:"Coolaste inlägget ever!", date:"1337-12-13 13:37", id:"0", header:"S00perDynamicHeader", message:"AS000000perDynamicMessage"};


//Parsing Queries
function urlKeys(key) {
    key = key.replace(/[*+?^$.\[\]{}()|\\\/]/g, "\\$&"); // escape RegEx meta chars
    var match = location.search.match(new RegExp("[?&]"+key+"=([^&]+)(&|$)"));
    return match && decodeURIComponent(match[1].replace(/\+/g, " "));
}

//TODO: Make sure input is correctly formated. NEED to choose a mail REGEX

//--------------------------------------------------------------------------------------------------------------------------------------------

//ONLOAD Functions

$(document).ready(function() {
    //$(".title-text").text(" Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.");
    //$(".page-title").text("Gastolibro");

    //for(i = 0; i < 10; i++) {
        //createEntry(entries[0]);
    //}

    bookId = urlKeys("bookId");

    $.get("/api/books/" + bookId, function(book, status) {
        $(".page-title").text(book.title);
        $(".title-text").text(book.description);
        book.entries.forEach(createEntry);
        countries = book.countries;
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

function requestCities(country) {
    return $.get("api/books/" + bookId + "/search/?country=" + country, function (objects){
        return objects;
    });
}

function addEntrytoDB(entry) {
    $.ajax({
    type: "POST",
    url: "/api/books/" + bookId + "/entries",
    data: JSON.stringify(entry),
    success: function(data, status) {
        if (status == 201)
            return true;
        else
            return false;
    },
    contentType: "application/json",
    dataType: 'json'
    })
}


//--------------------------------------------------------------------------------------------------------------------------------------------
//Scrolling function

$(function($) {
    $(".div-entries").on('scroll', function() {
        if($(this).scrollTop() + $(this).innerHeight() >= $(this)[0].scrollHeight - 60) {
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

    removeErrors();

    if (validations(entry)) {
        addEntrytoDB(entry);
        //location.reload(); //Reload content to view input
    }
}

function getSubmissionContent() {
    var entry = {};
    $("form#entry-input :input").each(function() {
        entry[$(this).attr("id")] = $(this).val();
    });

    return entry;
}


//--------------------------------------------------------------------------------------------------------------------------------------------

//DYNAMIC CONTENT

function createEntry(entry) {
    entries[entry.id] = entry;

    if (entry.comment == null) {
        entry.comment = "";
    }

    $(".div-entries").append(`
        <div class='div-entry'>
            <div class='div-title-date'>
                <h3 class='display-entry-title'><em>${entry.header}</em></h3>
                <h5 class='display-entry-date'>${entry.date}</h5>
            </div>
            <p class='display-entry-message'>${entry.message}</p>
            <div class='div-contact-response'>
                <p class='display-entry-contact' data-view-state='true' data-id=${entry.id} onclick='loadMessageInfo(this)'>${entry.name}</p>
                <p class='display-entry-response'>${entry.comment}</p>
            </div>
        </div>
      `)
}

function contactInfoDiv(contactInfo) {
    return `
        <div class='div-entry-contact' data-id=${contactInfo.id} data-view-state='false'>
            <p class='entry-contact-country'> country: ${contactInfo.country}</p>
            <p class='entry-contact-email' data-email='${contactInfo.email}' onclick='openEmail(this)'> email: ${contactInfo.email} </p>
            <p class='div-contact-hide' onclick='loadMessageInfo(this.parentElement)'>hide</p>
        </div>
    `
}

function contactInfoParagraph(contactInfo) {
    return  `
        <p class='display-entry-contact' data-view-state='true' data-id=${contactInfo.id} onclick='loadMessageInfo(this)'>${contactInfo.name}</p>
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

function email_error(msg) {
    return ` <p class="email-error">${msg}</p> `
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
//INPUT

function removeErrors() {
    $(".form-entry").children().removeClass("input-error");
}

function validations(entry) {
    validation = true;
    if (entry.email.match(emailRegex) == null) {
        $(".entry-email").addClass("input-error");
        validation = false;
    }

    if (entry.name == "") {
        $(".entry-name").addClass("input-error");
        validation = false;
    }

    if (entry.country == "") {
        $(".entry-country").addClass("input-error");
        validation = false;
    }

    if (entry.city == "") {
        $(".entry-city").addClass("input-error");
        validation = false;
    }

    if (entry.headline == "") {
        $(".entry-headline").addClass("input-error");
        validation = false;
    }

    if (entry.message == "") {
        $(".entry-message").addClass("input-error");
        validation = false;
    }

    return validation;

}

//--------------------------------------------------------------------------------------------------------------------------------------------

//AutoCompletion

$(".entry-city").focus(function() {
    country = $(".entry-country").val();
    if (country != "") {
        cities = requestCities(country);
    }
});

$(".entry-country").autocomplete({
    source: countries
});

$(".entry-city").autocomplete({
    source: cities
})

//$(".gastro-tools-search-bar").autocomplete({
    //source:autocompletion
//});

//--------------------------------------------------------------------------------------------------------------------------------------------





//--------------------------------------------------------------------------------------------------------------------------------------------
//EVERYTHING SEARCH





