var app = app || {};

app.LibraryView = Backbone.View.extend({
    el: '#content',

    events:{
        'click #addBookBtn':'addBook'
    },

    addBook: function( e ) {
        e.preventDefault();
        var formData = {};

        $( '#addBookForm div' ).children( 'input' ).each( function( i, el ) {
            if( $( el ).val() !== '' )
            {
                formData[ el.id ] = $( el ).val();
            }
        });
        this.collection.create( formData );
        debugger;
        alert("Book added successfully.")
        var frm=$("#addBookForm")[0];
        frm.reset();
    },

    initialize: function( initialBooks ) {
        this.collection = new app.BookCollection();
        // The models are fetched asynchronously after the page is rendered.
        // When the fetch completes, Backbone fires the reset event,
        // as requested by the reset: true option, and our listener re-renders the view.
        this.collection.fetch({reset: true});
        this.render();
        this.listenTo( this.collection, 'reset', this.render );
        // this.listenTo( this.collection, 'add', this.renderBook );
    },

    // render library by rendering each book in its collection
    render: function() {
        $("#content").empty();
        console.log("rendering library view...")
        this.collection.each(function( item ) {
            this.renderBook( item );
        }, this );
    },

    // render a book by creating a BookView and appending the
    // element it renders to the library's element
    renderBook: function( item ) {
        var bookView = new app.BookView({
            model: item
        });
        this.$el.append( bookView.render().el );
    }
});



