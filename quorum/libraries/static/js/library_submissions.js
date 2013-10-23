var updateRoute = function (page) {
    window.location.hash = "/" + page;
}

if (window.location.hash == "") {
    updateRoute(page);
}


App = Ember.Application.create({});

var libraries = [{
    library_id: '11111-1111-1111-11111',
    library_name: "Library 1",
    library_description: "There are lots of à la carte software environments in this world. Places where in order to eat, you must first carefully look over the menu of options to order exactly what you want.",
    date_submitted: new Date('12-27-2012'),
    submission_url: "http://google.com",
    upvotes: 5,
    downvotes: 4
}, {
    library_id: '22222-2222-2222-22222',
    library_name: "Library 2",
    library_description: "There are lots of à la carte software environments in this world. Places where in order to eat, you must first carefully look over the menu of options to order exactly what you want.",
    date_submitted: new Date('12-22-2012'),
    submission_url: "http://yahoo.com",
    upvotes: 5,
    downvotes: 4
}
];

App.Router.map(function () {
    this.resource('libraries', { path: "/:page" });
});

App.LibrariesRoute = Ember.Route.extend({
    model: function (params) {
        console.log(params);
        return libraries;
    }
});

App.LibrariesController = Ember.ObjectController.extend({
    page: 0,
    sortBy: "upvotes",
    filter: "",

    actions: {
        nextPage: function () {
            this.set("page", this.get("page") + 1);
            updateRoute(this.get("page"));
            console.log("Go to page #" + this.get("page"));
        },
        prevPage: function () {
            if (this.get("page") == 0) { return; }
            this.set("page", this.get("page") - 1);
            updateRoute(this.get("page"));
            console.log("Go to page #" + this.get("page"));
        },
        sortBy: function (sorter) {
            this.set("sortBy", sorter);
            console.log("Sort by column " + this.get("sortBy"));
        },
        upvote: function (libraryId) {
            console.log("Upvote " + libraryId);
            // make an ajax call
        },
        downvote: function (libraryId) {
            console.log("Downvote " + libraryId);
            // make an ajax call
        }
    }
});

var showdown = new Showdown.converter();

Ember.Handlebars.helper('format-markdown', function(input) {
  return new Handlebars.SafeString(showdown.makeHtml(input));
});

Ember.Handlebars.helper('format-date', function(date) {
  return moment(date).fromNow();
});


