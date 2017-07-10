var exports = module.exports = {};
exports.createEntry = function(entry) {

    if (entry.comment == null) {
        entry.comment = "";
    }

    return `
        <div class='div-entry' id=${entry.id}>
            <div class='div-title-date'>
                <h3 class='display-entry-title'><em>${entry.header}</em></h3>
                <h5 class='display-entry-date'>${entry.date}</h5>
            </div>
            <p class='display-entry-message'>${entry.message}</p>
            <div class='div-contact-response'>
                <div class='div-entry-contact'>
                    <p class='entry-contact-name'>${entry.name}</p>
                    <p class='entry-contact-country'>${entry.country}</p>
                    <p class='entry-contact-email' data-email='${entry.email}'>${entry.email}</p>
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