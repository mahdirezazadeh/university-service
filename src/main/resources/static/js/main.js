$(document).ready(function () {
    $('input[name="activateBut"]').click(function () {
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
                    .attr('class', 'btn btn-submit activate hidden')/*
                    .attr('id', `dac${userId}`)*/;

                $(`#dac${userId}`)
                    .attr('class', 'btn btn-submit activate')/*
                    .attr('id', `dac${userId}`)*/;

                $(`#pending${userId}`)
                    .attr('class', 'pending-hidden');
            }
        });
    });

    $('input[name="DeactivateBut"]').click(function () {
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
                    .attr('class', 'btn btn-submit activate hidden')/*
                    .attr('id', `act${userId}`)*/;

                $(`#act${userId}`)
                    .attr('class', 'btn btn-submit activate')/*
                    .attr('id', `act${userId}`)*/;

                $(`#pending${userId}`)
                    .attr('class', 'pending');
            }
        });
    });


})





