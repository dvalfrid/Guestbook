var utils = require("./utilities");

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
var mock = require("./mock");

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

