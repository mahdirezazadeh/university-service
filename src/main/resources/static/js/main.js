$(document).ready(function () {
    $('.activateBut').click(function () {
        console.log($(this).attr("id").substring(3));
        var userId = $(this).attr("id").substring(3);
        // $.ajax({
        //     uri: `http://localhost:8080/user/confirm-user/`,
        //     type: "put",
        //     // contentType: 'application/json',
        //     // data: JSON.stringify({id: userId}),
        //     data: {id: userId},
        //     success: function (response){
        //         $(this).css("display", "none");
        //         $(`#dac${userId}`).css("display", "block");
        //     },
        //     fail: function (err){
        //         alert(err)
        //     }
        $.post(
            `http://localhost:8080/user/confirm-user`,
            {
                id: userId
            },
            function (data, status) {
                alert("Data: " + data + "\nStatus: " + status);
            }
        );

    })

    $('.DisactivateBut').click(function (data) {
        console.log($(this).attr("id").substring(3));
    })
})





