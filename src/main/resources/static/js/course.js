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


//    remove_question
    $('input[name="remove_question"]').click(function () {
        let examQuestionId = $(this).attr("id");
        let examNewScore = $('#examScore').html().substring(12) - $(`#score${examQuestionId}`).html();
        console.log($('#examScore').html().substring(12));
        console.log($(`#score${examQuestionId}`).html());
        console.log(examNewScore);
        let token = $("meta[name='_csrf']").attr("content");
        let header = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            type: 'POST',
            url: `http://localhost:8080/question/remove-from-exam`,
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            data: {
                examQuestionId: examQuestionId
            },
            success: function () {
                $(`#question${examQuestionId}`).remove();
                $('#examScore').html(`Exam Score: ${examNewScore}`)
                console.log("exam deleted!");
            }
        });
    });

    $('input[name="delete_question"]').click(function () {
        let questionId = $(this).attr("id");
        // let examId = $('input[name="examId"]').val();
        // console.log(examId)
        let token = $("meta[name='_csrf']").attr("content");
        let header = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            type: 'POST',
            url: `http://localhost:8080/question/delete`,
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            data: {
                questionId: questionId
            },
            success: function () {
                $(`#question${questionId}`).remove();
                console.log("exam deleted!");
            }
        });
    });
})





