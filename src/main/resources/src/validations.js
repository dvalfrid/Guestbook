var config = require("./config");
var api = require ("./api");

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

