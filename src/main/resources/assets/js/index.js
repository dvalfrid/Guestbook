/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};
/******/
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/
/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId]) {
/******/ 			return installedModules[moduleId].exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			i: moduleId,
/******/ 			l: false,
/******/ 			exports: {}
/******/ 		};
/******/
/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);
/******/
/******/ 		// Flag the module as loaded
/******/ 		module.l = true;
/******/
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/
/******/
/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;
/******/
/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;
/******/
/******/ 	// define getter function for harmony exports
/******/ 	__webpack_require__.d = function(exports, name, getter) {
/******/ 		if(!__webpack_require__.o(exports, name)) {
/******/ 			Object.defineProperty(exports, name, {
/******/ 				configurable: false,
/******/ 				enumerable: true,
/******/ 				get: getter
/******/ 			});
/******/ 		}
/******/ 	};
/******/
/******/ 	// getDefaultExport function for compatibility with non-harmony modules
/******/ 	__webpack_require__.n = function(module) {
/******/ 		var getter = module && module.__esModule ?
/******/ 			function getDefault() { return module['default']; } :
/******/ 			function getModuleExports() { return module; };
/******/ 		__webpack_require__.d(getter, 'a', getter);
/******/ 		return getter;
/******/ 	};
/******/
/******/ 	// Object.prototype.hasOwnProperty.call
/******/ 	__webpack_require__.o = function(object, property) { return Object.prototype.hasOwnProperty.call(object, property); };
/******/
/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";
/******/
/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(__webpack_require__.s = 6);
/******/ })
/************************************************************************/
/******/ ([
/* 0 */
/***/ (function(module, exports, __webpack_require__) {

var templates = __webpack_require__(3);
var exports = module.exports = {};

function clearChildren(element) {
    while(element.firstChild) {
        element.removeChild(element.firstChild);
    }
}

exports.urlKeys = function(key) {
    key = key.replace(/[*+?^$.\[\]{}()|\\\/]/g, "\\$&"); // escape RegEx meta chars
    var match = location.search.match(new RegExp("[?&]"+key+"=([^&]+)(&|$)"));
    return match && decodeURIComponent(match[1].replace(/\+/g, " "));
}

exports.__highlight = function(s, t) {
  var matcher = new RegExp("("+$.ui.autocomplete.escapeRegex(t)+")", "ig" );
  return s.replace(matcher, "<strong>$1</strong>");
}

exports.openEmail = function(email) {
    //for Lulz atm
    console.log($(email).data("email"));
    console.log("hello");
    redirect("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
}

exports.redirect = function(url) {
    window.location.replace(url);
}

exports.clearChildren = clearChildren;


exports.getSubmissionContent = function() {
    var entry = {};
    $("form#entry-input :input").each(function() {
        entry[$(this).attr("id")] = $(this).val();
    });

    return entry;
}

exports.crash = function(code) {
 switch(code) {
    case 0:
        document.documentElement.innerHTML = templates.crashHtml("Could not find book, or book not defined :(");
        break;
    default:
        console.log("unknown Error :(");
 }
}

exports.loadSearchPageEntry = function(entry) {
    $(".div-search-results").append(templates.createEntry(entry));
}

exports.loadMainPageEntry = function(entry) {
    $(".div-entries").append(templates.createEntry(entry));
}

exports.animate_popup = function(popup) {
    clearChildren(document.getElementById("popup"));
    $("#popup").removeClass();

    $("#popup").addClass(popup.classes);
    $("#popup").append(popup.innerElements);
    $("#popup").fadeIn(300).delay(5000).fadeOut(300);
}

/***/ }),
/* 1 */
/***/ (function(module, exports, __webpack_require__) {

var utils = __webpack_require__(0);

var exports = module.exports = {};
/*
exports.requestBookContent = function() {
    return $.get("/api/books" + utils.urlKeys("bookId"), function(book) {
        return book;
    })
}

exports.requestEntries = function(numberOfEntries, oldestEntryId) {
    return $.get("/api/books/" + utils.urlKeys("bookId") + "/entries/" + "?numberOfEntries=" + numberOfEntries  + "&?afterId=" + oldestEntryId,
        function( entries ) {
            return entries;
        });
}

exports.requestEntry = function(entryId) {
    return $.get("/api/books/" + utils.urlKeys("bookId") + "entries/" + entryId,
        function ( entry ) {
            return entry;
        });
}

exports.fuzzySearch = function(str) {
   return $.get("/api/books/" + utils.urlKeys("bookId") + "/search/?snippet=" + str,

        function ( objects ) {
            return objects;
        });
}

exports.requestCountries = function(term) {
    return $.get("/api/countries?term=" + term, function(objects) {
        return objects;
    });
}

exports.requestCities = function(term, country) {
    return $.get("/api/cities?country=" + country + "&term=" + term, function (objects){
        return objects;
    });
}

exports.addEntrytoDB = function(entry) {
    $.ajax({
    type: "POST",
    url: "/api/books/" + utils.urlKeys("bookId") + "/entries",
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

*/ 

//MOCK STARTS HERE, COMMENT THIS OUT FOR REAL TESTING
var mock = __webpack_require__(7);

exports.requestBookContent = function() {
    return mock.book;
}

exports.requestEntries = function(numberOfEntries, oldestEntryId) {
    return mock.entries;
}

exports.requestEntry = function(entryId) {
    return mock.entry;
}

exports.fuzzySearch = function(str) {
    return mock.fuzzySearch;
}

exports.requestCountries = function(term) {
    return mock.countries;
}

exports.requestCities = function(term, country) {
    return mock.cities;
}

exports.addEntrytoDB = function(entry) {
    console.log("Totally inserted something right now.");
    console.log("inserted:");
    console.log(entry);
    return true;
}

exports.submissionCheck = function() {
    if (Math.random() > 0.5) {
        return true;
    } else {
        return false;
    }
}

exports.submissionVerification = function(answer, id) {
    if (Math.random() > 0.5) {
        return true;
    } else {
        return false;
    }
}

exports.getCaptcha = function() {
    var captcha = {
        id: 0,
        url: "http://joshpoehlein.com/Pages/Projects/Captcha2/Images/CaptchaText.jpg"
    }

    return captcha;
}



/***/ }),
/* 2 */
/***/ (function(module, exports, __webpack_require__) {

var utilities = __webpack_require__ (0);
var validations = __webpack_require__ (4);
var templates = __webpack_require__ (3);
var api = __webpack_require__ (1);
var config = __webpack_require__ (5);
var captchas = __webpack_require__ (8);

$(document).ready(function() {
    try {
        book = api.requestBookContent();
        $(".page-title").text(book.title);
        $(".title-text").text(book.description);
        book.entries.forEach(utilities.loadMainPageEntry);
    } catch(err) {
        console.log(err);
        utilities.crash(0);
    }
});

function submissionHandler() {
    entry = utilities.getSubmissionContent();

    $(".form-entry").children().removeClass("input-error");

    if (api.submissionCheck()) {
        if (validations.entry(entry) 
            && api.addEntrytoDB(entry)) {
                utilities
                .animate_popup
                (templates.submit_popup(
                    config.submit_popup
                ));

        }
    } else {
        captchas.enableCaptcha()
    }
}

$("#input-button").click(function() {submissionHandler()}); 

$(".div-right-button").on('click', function(){
    $(".div-entries").fadeOut(200);
    $(".div-right-button").fadeOut(200);

    $(".div-entry-input").fadeIn(200);
})

$(".div-left-button").on('click', function() {
    $(".div-entry-input").fadeOut(200);

    $(".div-entries").fadeIn(200);
    $(".div-right-button").fadeIn(200);
    $("#popup").fadeOut(100);
})


$(".div-search-icon").on('click', function() {
    $(".div-top").fadeOut(200);
    $(".div-main-content-wrapper").fadeOut(200);
    $(".div-search-page").fadeIn(400);
})

$(".div-mainpage-icon").on('click', function() {
    $(".div-search-page").fadeOut(200);
    $(".div-main-content-wrapper").fadeIn(400);
    $(".div-top").fadeIn(400);
})

$(".div-entries").on('scroll', function() {
    if($(this).scrollTop() + $(this).innerHeight() >= $(this)[0].scrollHeight - 100) {
        recent_entry_id = document.getElementById("entries").lastElementChild.getAttribute("id");
        api.requestEntries(5, recent_entry_id).forEach(utilities.loadMainPageEntry);
    }
})

module.exports = {
    submissionHandler:submissionHandler
}






/***/ }),
/* 3 */
/***/ (function(module, exports) {

var exports = module.exports = {};
exports.createEntry = function(entry) {

    if (entry.comment == null) {
        entry.comment = "";
    }

    return `
        <div class='div-entry' id=${entry.id}>
            <div class='div-title-date'>
                <h3 class='display-entry-title'><em>${entry.header}</em></h3>
                <h5 class='display-entry-date'>${entry.date}</h5>
            </div>
            <p class='display-entry-message'>${entry.message}</p>
            <div class='div-contact-response'>
                <div class='div-entry-contact'>
                    <p class='entry-contact-name'>${entry.name}</p>
                    <p class='entry-contact-country'>${entry.country}</p>
                    <p class='entry-contact-email' data-email='${entry.email}'>${entry.email}</p>
                </div>
                <p class='display-entry-response'>${entry.comment}</p>
            </div>
        </div>
      `
}

exports.submit_popup = function(message) {
    var popup = {
        classes: "popup-submit-position popup-submit-design",
        innerElements: ` <p id="sumbit-text">${message}</p> `
    }
    return popup;
    
}

exports.captcha_popup = function(captcha) {
    var popup = { 
        classes: "popup-captcha-position popup-captcha-design",
        innerElements: `
        <div class="div-captcha-image">
            <img src="${captcha.url}" style="width:100%; heigth:auto;"/>
        </div>
        <input type="text" class="input-captcha" id="input-captcha" data-id=${captcha.id} placeholder="U a Bot??"/>
    `
    }

   return popup; 
}

exports.crashHtml = function(string) {
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

/***/ }),
/* 4 */
/***/ (function(module, exports, __webpack_require__) {

var config = __webpack_require__(5);
var api = __webpack_require__ (1);

module.exports = {
     entry:  function(entry) {
        validation = true;
        if (entry.email.match(config.emailRegex) == null) {
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

    },

    captcha: function(response, id) {
        if (api.submissionVerification(response, id)) {
            return true;
        } else {
            $(".div-captcha-image").addClass("input-error");
            return false;
        }
    }
}



/***/ }),
/* 5 */
/***/ (function(module, exports) {


module.exports = {
    emailRegex: new RegExp("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?"),
    submit_popup: "Thanks For Your Submission! Refresh The Page To View It",
}

/***/ }),
/* 6 */
/***/ (function(module, exports, __webpack_require__) {

__webpack_require__ (2);
__webpack_require__ (9);


















/***/ }),
/* 7 */
/***/ (function(module, exports) {

var entry = {
    name:"mr smith", 
    country:"sweden" ,
    email:"Cool@s00permail.com", 
    id:0,
    header: "S00perHeader",
    date: "1970:00:00",
    message: "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
    comment: "that's a great entry, i really like that entry, please more like these entries"
};

var entries = [];

for (i = 0; i < 10; i++) {
    entries[i] = entry;
    entry.id++;
}

module.exports = {
    book: book = {
        id: 7,
        entries:entries,
        title:"Gastolibro",
        description:"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
    },

    countries: ["sweden", "swiss", "islamabad", "cookieland"],
    cities: ["stockholm", "luleå", "kiruna"],

    entries:entries,

    fuzzySearch: search = {
        entries: entries,
        suggestions: ["entry1", "entry2", "entry3", "entry4", "entry5", "entry6", "entry7", "entry8", "entry9", "entry10"] 
    },

    entry:entry
}

/***/ }),
/* 8 */
/***/ (function(module, exports, __webpack_require__) {

var templates = __webpack_require__(3);
var utilities = __webpack_require__(0);
var validations = __webpack_require__(4);
var api = __webpack_require__(1);
var dyncont;

exports = module.exports = {}


exports.enableCaptcha = function() {
    popin_captcha(api.getCaptcha())
}

function popin_captcha(captcha) {
    utilities.clearChildren(document.getElementById("popup"));
    temp = templates.captcha_popup(captcha);


    $("#popup").removeClass();
    $("#popup").addClass(temp.classes);
    $("#popup").append(temp.innerElements);
    $("#popup").fadeIn(400);
    
    dyncont = __webpack_require__(2);
}


$(document).on('keyup', '#input-captcha', function(evt) {
    if (evt.keyCode == 13 &&
        validations.captcha($("#input-captcha").val(),
        $("#input-captcha").data("id"))) {
            console.log("Submission-Passed");
            dyncont.submissionHandler();
    }
});


/***/ }),
/* 9 */
/***/ (function(module, exports, __webpack_require__) {

var api = __webpack_require__(1);
var dyncont = __webpack_require__(2);
var utils = __webpack_require__(0);

$(function() {

    $("#search-bar").autocomplete({
        source: function(request, response) {
            utils.clearChildren(document.getElementById("search-results"));
            entry_autocompl_sugg = api.fuzzySearch(request.term);
            entry_autocompl_sugg["entries"].forEach(utils.loadSearchPageEntry);

            response($.map(entry_autocompl_sugg["suggestions"], function(suggestion) {
                return {
                    label: suggestion, /*TODO: FIX HIGHLIGHT*/
                    value: suggestion
                };
            }));
        },

        minLength: 2,
        autoFocus: true
    })
});

var country;
$("#city").focus(function() {
    country = $("#country").val();
});

$("#country").autocomplete({
    source: function(request, response) {
        response(api.requestCountries(request));
    },
    autoFocus: true
});

$("#city").autocomplete({
    source: function(request, response) {
        response(api.requestCities(request, country))
    },
    autoFocus: true
})


/***/ })
/******/ ]);