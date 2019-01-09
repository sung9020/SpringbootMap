
var main = {
    search : function () {
        var jsonParam =  main.makeParam();

        $.ajax({
            type: 'POST',
            url: '/search',
            contentType : "application/json",
            data: jsonParam,
            success: function (data) {
                meta = data.meta;
                documents = data.documents;

                var source = $('#list-template').html();
                var template = Handlebars.compile(source);
                var html = template(data);
                $('#searchList').empty().append(html);

                $(document).on("click", "a.place" , function(event) {
                    var id = event.target.getAttribute("id");

                    $.each(documents, function( index, value ) {
                        if (value.id = id){
                            var source = $('#detail').html();
                            var template = Handlebars.compile(source);
                            var html = template(value);
                            $('#detailSection').empty().append(html);
                            $('.modal').modal('show');
                            return false;
                        }

                    });
                });

            },
            error: function (request,status, error) {
                alert(error);
            }
        })

    },
    makeParam : function () {
        var RequestDto = new Object();
        var query = $("#keyword").val();
        RequestDto.query =query;
        var jsonParam = JSON.stringify(RequestDto);
        return jsonParam;
    }


}