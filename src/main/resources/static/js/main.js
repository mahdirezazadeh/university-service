$(document).ready(function () {
    $('.activateBut').click(function () {
        let userId = $(this).attr("id").substring(3);
        let token = $("meta[name='_csrf']").attr("content");
        let header = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            type: 'POST',
            url: `http://localhost:8080/user/confirm`,
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            data: {
                id: userId
            },
            success: function () {
                $(`#act${userId}`)
                    .attr('value', "Deactivate")
                    .attr('class', 'btn btn-submit selectSec DeactivateBut')/*
                    .attr('id', `dac${userId}`)*/;

                $(`#pending${userId}`)
                    .attr('class', 'pending-hidden');
            }
        });
    });

    $('.DeactivateBut').click(function () {
        let userId = $(this).attr("id").substring(3);
        let token = $("meta[name='_csrf']").attr("content");
        let header = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            type: 'POST',
            url: `http://localhost:8080/user/disprove`,
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            data: {
                id: userId
            },
            success: function () {
                $(`#dac${userId}`)
                    .attr('value', "Activate")
                    .attr('class', 'btn btn-submit selectSec activateBut')/*
                    .attr('id', `act${userId}`)*/;

                $(`#pending${userId}`)
                    .attr('class', 'pending');
            }
        });
    });
})





