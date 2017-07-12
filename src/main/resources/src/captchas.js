var templates = require("./templates");
var utilities = require("./utilities");
var validations = require("./validations");
var api = require("./api");
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
    
    dyncont = require("./dynamicContent");
}


$(document).on('keyup', '#input-captcha', function(evt) {
    if (evt.keyCode == 13 &&
        validations.captcha($("#input-captcha").val(),
        $("#input-captcha").data("id"))) {
            console.log("Submission-Passed");
            dyncont.submissionHandler();
    }
});
