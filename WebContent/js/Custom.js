
$(document).ready(function () {
    $(".option-click").click(function () {
        if ($(this).find(".badge").hasClass("button-color-blue"))
            $(this).find(".badge").removeClass("button-color-blue").addClass("bg-blue");
        else
            $(this).find(".badge").removeClass("bg-blue").addClass("button-color-blue");
     });
    $('#close-modal').click(function () {
      
        $('#Modal').modal('hide');
        $('#exampleModal').modal('hide');
    });

});


$('#exampleModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget) 
})
$('#Modal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget)
})

$("#realtime").click(function () {
    if ($(this).find(".btn").hasClass("btn-xs"))
        $(this).find(".badge").removeClass("btn-xs").addClass("btn-xs-active");
    else
        $(this).find(".badge").removeClass("btn-xs-active").addClass("btn-xs");
});







$(document).ready(function () {
$(window).load(function () {
    $('#TimeOutModal').modal('show');
});
});

$(document).ready(function () {
    $(".bookmark-click-green").click(function () {
        if ($(this).find(".fa-bookmark").hasClass("color-mainblue"))
            $(this).find(".fa-bookmark").removeClass("color-mainblue").addClass("color-green");
        else
            $(this).find(".fa-bookmark").removeClass("color-green").addClass("color-mainblue");
    });
});

