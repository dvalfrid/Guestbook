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
/******/ 	return __webpack_require__(__webpack_require__.s = 4);
/******/ })
/************************************************************************/
/******/ ([
/* 0 */
/***/ (function(module, exports, __webpack_require__) {

var templates = __webpack_require__(2);
var exports = module.exports = {};

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

exports.clearChildren = function(element) {
    while(element.firstChild) {
        element.removeChild(element.firstChild);
    }
}

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

/***/ }),
/* 1 */
/***/ (function(module, exports, __webpack_require__) {

var utils = __webpack_require__(0);

var exports = module.exports = {};

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

exports.requesCountries = function() {
    return $.get("api/countries", function(objects) {
        return objects;
    });
}

exports.requestCities = function(config, country) {
    return $.get("api/books/" + utils.urlKeys("bookId") + "/search/?country=" + country, function (objects){
        return objects;
    });
}

exports.addEntrytoDB = function(config, entry) {
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

/***/ }),
/* 2 */
/***/ (function(module, exports) {

var exports = module.exports = {};
exports.createEntry = function(entry) {

    if (entry.comment == null) {
        entry.comment = "";
    }

    return `
        <div class='div-entry'>
            <div class='div-title-date'>
                <h3 class='display-entry-title'><em>${entry.header}</em></h3>
                <h5 class='display-entry-date'>${entry.date}</h5>
            </div>
            <p class='display-entry-message'>${entry.message}</p>
            <div class='div-contact-response'>
                <div class='div-entry-contact'>
                    <p class='entry-contact-name'> name: ${entry.name} </p>
                    <p class='entry-contact-country'> country: ${entry.country}</p>
                    <p class='entry-contact-email' data-email='${entry.email}'> email: ${entry.email} </p>
                </div>
                <p class='display-entry-response'>${entry.comment}</p>
            </div>
        </div>
      `
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
/* 3 */
/***/ (function(module, exports, __webpack_require__) {

var utilities = __webpack_require__ (0);
var validations = __webpack_require__ (5);
var templates = __webpack_require__ (2);
var api = __webpack_require__ (1);

var entries = [];
$(document).ready(function() {
    try {
        book = api.requestBookContent();
        $(".page-title").text(book.title);
        $(".title-text").text(book.description);
        book.entries.forEach(dyncont.loadMainPageEntry);

        entries = book.entries;
    } catch(err) {
        utilities.crash(0);
    }
});


$(".div-input-button").on('click', function() {

    entry = utilities.getSubmissionContent();

    $(".form-entry").children().removeClass("input-error");

    if (validations.entry(entry)) {
        api.addEntrytoDB(entry);
        location.reload(); //Reload content to view input
    }

}); 

$(".div-right-button").on('click', function(){
    $(".div-entries").fadeOut(200);
    $(".div-entries").css("width", "0%");

    $(".div-entry-input").fadeIn(200);
    $(".div-entry-input").css("width", "100%");
})

$(".div-left-button").on('click', function() {
    $(".div-entry-input").fadeOut(200);
    $(".div-entries").css("width", "100%");
    $(".div-entries").fadeIn(200)
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

$(function($) {
    $(".div-entries").on('scroll', function() {
        if($(this).scrollTop() + $(this).innerHeight() >= $(this)[0].scrollHeight - 60) {
            requestEntries(5, entries[-1].id).forEach(utilities.loadMainPageEntry);
        }
    })

});







/***/ }),
/* 4 */
/***/ (function(module, exports, __webpack_require__) {

__webpack_require__ (1)
__webpack_require__ (0);
__webpack_require__ (3);
__webpack_require__ (7);


















/***/ }),
/* 5 */
/***/ (function(module, exports, __webpack_require__) {

var config = __webpack_require__(6);

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

    }
}

/***/ }),
/* 6 */
/***/ (function(module, exports) {


module.exports = {
    emailRegex: new RegExp("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")
}

/***/ }),
/* 7 */
/***/ (function(module, exports, __webpack_require__) {

var api = __webpack_require__(1);
var dyncont = __webpack_require__(3);
var utils = __webpack_require__(0);

var cities = [];
var countries = [];
$(document).ready(function() {
    countries = api.requesCountries;
})

$(function() {

    $(".search-bar").autocomplete({
        source: function(request, response) {
            utils.clearChildren(document.getElementById("search-results"));
            entry_autocompl_sugg = api.fuzzySearch(request.term);
            entry_autocompl_sugg["entries"].forEach(dyncont.loadSearchPageEntry);

            response($.map(entry_autocompl_sugg["suggestions"], function(suggestion) {
                console.log(suggestion);
                return {
                    label: suggestion, /*TODO: FIX HIGHLIGHT*/
                    value: suggestion
                };
            }));
        },

        minLength: 2
    })
});

$(".entry-city").focus(function() {
    country = $(".entry-country").val();
    if (country != "") {
        cities = api.requestCities(country);
    }
});

$(".entry-country").autocomplete({
    source: countries
});

$(".entry-city").autocomplete({
    source: cities
})


/***/ })
/******/ ]);