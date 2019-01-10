
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
                keywordRank = data.keywordRank;
                var source = $('#list-template').html();
                var template = Handlebars.compile(source);
                var html = template(data);
                $('tbody').empty().append(html);


                main.makeClickEvent();
                main.viewKeywordRank();

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
    },
    viewKeywordRank : function (keywordRank) {
        $.ajax({
            type: 'POST',
            url: '/keywordrank',
            contentType : "application/json",
            success: function (data) {

                var source = $('#keywordRank-template').html();
                var template = Handlebars.compile(source);
                var html = template({keywordRankDtoList:data});
                $('#keywordSection').empty().append(html);

            },
            error: function (request,status, error) {
                alert(error);
            }
        })

    },
    makeClickEvent : function () {
        $(document).on("click", "a.place" , function(event) {
            var id = event.target.getAttribute("id");

            $.each(documents, function( index, value ) {
                if (value.id = id){
                    var source = $('#detail-template').html();
                    var template = Handlebars.compile(source);
                    var html = template(value);
                    $('.modal-content').empty().append(html);
                    $('.modal').modal('show');
                    return false;
                }
            });
        });
    }


}