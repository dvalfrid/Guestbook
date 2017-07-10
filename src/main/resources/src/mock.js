var entry = {
    name:"mr smith", 
    country:"sweden" ,
    email:"Cool@s00permail.com", 
    id:"0",
    header: "S00perHeader",
    date: "1970:00:00",
    message: "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
    comment: "that's a great entry, i really like that entry, please more like these entries"
};

var entries = [];

for (i = 0; i < 10; i++) {
    entries[i] = entry;
}

module.exports = {
    book: book = {
        id: 7,
        entries:entries,
        title:"Gastolibro",
        description:"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
    },

    countries: ["sweden", "swiss", "islamabad", "cookieland"],
    cities: ["stockholm", "luleå", "kiruna"],

    entries:entries,

    fuzzySearch:"You're bad and you should feel bad.",

    entry:entry



}