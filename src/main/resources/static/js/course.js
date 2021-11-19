$(document).ready(function () {

    //js post function for choosing teacher
    $('input[name="teacherId"]').click(function () {
        let teacherId = $(this).attr("value")
        let courseId = $('input[name="courseIdd"]').attr("value");
        let token = $("meta[name='_csrf']").attr("content");
        let header = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            type: 'POST',
            url: `http://localhost:8080/course/set-teacher`,
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            data: {
                teacherId: teacherId,
                courseId: courseId
            },
            success: function () {
                console.log("teacher changed!");
            }
        });
    });

    //js function for deleting exam
    $('input[name="trashcan"]').click(function () {
        let examId = $(this).attr("id")
        console.log(examId)
        let token = $("meta[name='_csrf']").attr("content");
        let header = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            type: 'POST',
            url: `http://localhost:8080/exam/delete`,
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            data: {
                examId: examId
            },
            success: function () {
                $(`#exam${examId}`).remove();
                console.log("exam deleted!");
            }
        });
    });


    $('input[name="score"]').keyup(function () {
        console.log("score changes");
        if ($('input[name="score"]').val() < 0) {
            // console.log("score is lesser than 0")
            // $('input[name="score"]').val('');
            $('p[id="scoreError"]').html('Score can not be zero or negative!');
            $(':input[type="submit"]').prop('disabled', true);
        } else {
            $('p[id="scoreError"]').html('');
            $(':input[type="submit"]').prop('disabled', false);
        }
    });


    $('input[name="score"]').change(function () {
        console.log("score changes");
        if ($('input[name="score"]').val() <= 0) {
            // console.log("score is equal 0")
            // $('input[name="score"]').val('');
            $('p[id="scoreError"]').html('Score can not be zero or negative!');
            $(':input[type="submit"]').prop('disabled', true);
        } else {
            $('p[id="scoreError"]').html('');
            $(':input[type="submit"]').prop('disabled', false);
        }
    });


})





