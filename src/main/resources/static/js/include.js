
var main = {
    search : function () {
        var jsonParam =  main.makeParam();

        $.ajax({
            type: 'POST',
            url: '/search',
            contentType : "application/json",
            data: jsonParam,
            success: function (data) {
                var source = $("#list").html();
                var template = Handlebars.compile(source);
                var data = data;
                var html = template(data);
                $('#table').append(html);
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