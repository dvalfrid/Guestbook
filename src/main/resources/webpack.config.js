var webpack = require("webpack");
var path = require("path");

const indexPath = __dirname + '/assets/js/';
const entryPath = __dirname + '/src/';
const configPath = __dirname + '/src/';

module.exports = {
    entry: entryPath + "entry.js",
    output : {
        path: indexPath,
        filename: 'index.js',
    }
}