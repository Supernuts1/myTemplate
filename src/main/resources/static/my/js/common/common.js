var Common = function() {
    this.defaults = [{}, {}, {}, {}, {}, {}, {}],
        this.groupHeaders = {},
        this.quantityDecimalDigits = 0,
        this.zkl = 16
};
Common.prototype = {
    ajax: function(type,url,data) {
        // $.ajax({
        //     type: type,
        //     url: url,
        //     headers: {
        //         'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
        //     },
        //     dataType: "json",
        //     data: data,
        //     async:async,
        //     success: function (result) {
        //         return result;
        //     },
        //     error: function (e) {
        //         alert(e);
        //     }
        // });
        $.ajax({
            type: type,
            url: url,
            dataType: "json",
            data: data
        })
    }
}
$Common = new Common();
