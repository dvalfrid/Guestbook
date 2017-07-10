var utils = require("./utilities");

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
    return $.get("/api/countries", function(objects) {
        return objects;
    });
}

exports.requestCities = function(config, country) {
    return $.get("/api/?country=" + country, function (objects){
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