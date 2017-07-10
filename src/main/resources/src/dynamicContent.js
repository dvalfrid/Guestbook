var utilities = require ("./utilities");
var validations = require ("./validations");
var templates = require ("./templates");
var api = require ("./api");

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





