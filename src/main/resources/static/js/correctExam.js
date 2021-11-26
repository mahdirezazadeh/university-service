$(document).ready(function () {
    //js post function for saving descriptive Question
    $('input[name="saveScore"]').click(function () {
        let questionId = $(this).attr("id");

        let teacherGivenScoreName = 'teacherGivenScore' + questionId;
        let teacherGivenScore = $(`#${teacherGivenScoreName}`).val().trim();


        saveAnswer(questionId, teacherGivenScore);
    });

    function saveAnswer(questionId, teacherGivenScore) {

        let token = $("meta[name='_csrf']").attr("content");
        let header = $("meta[name='_csrf_header']").attr("content");


        $.ajax({
            type: 'POST',
            url: `http://localhost:8080/question/save-score`,
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            data: {
                questionId: questionId,
                teacherGivenScore: teacherGivenScore
            },
            success: function () {
                console.log("score saved!!");
                let errorId = `error${questionId}`;
                $(`p[id=${errorId}]`).html("");
                let spanScore = `spanScore${questionId}`;
                $(`span[id=${spanScore}]`).html(`student score: ${teacherGivenScore} points`);
            },
            error: function () {
                console.log("failed!!!!");
                let errorId = `error${questionId}`;
                $(`p[id=${errorId}]`).html("score can not be more than max score or less than 0");
            }
        });
    }

})




