var utilities = require ("./utilities");
var validations = require ("./validations");
var templates = require ("./templates");
var api = require ("./api");
var config = require ("./config");
var captchas = require ("./captchas");

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




