var api = require("./api");
var dyncont = require("./dynamicContent");
var utils = require("./utilities");

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
