$(document).ready(function() {
    var buttonCommon = {
        exportOptions: {
            format: {
                body: function ( data, column, row ) {
                    // Strip $ from salary column to make it numeric
                    return column === 5 ?
                        data.replace( /[$,]/g, '' ) :
                        data;
                }
            }
        }
    };
    });