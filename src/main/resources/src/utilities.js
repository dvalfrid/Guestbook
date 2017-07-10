var templates = require("./templates");
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