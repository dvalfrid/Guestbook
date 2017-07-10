var api = require("./api");
var dyncont = require("./dynamicContent");
var utils = require("./utilities");

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
