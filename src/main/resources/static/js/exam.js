$(document).ready(function () {

    const answers = new Array();


    //js post function for choosing teacher
    $('input[name="saveQuestion"]').click(function () {
        let questionId = $(this).attr("id").split(',')[0];
        // console.log(questionId);
        let questionNumber = $(this).attr("id").split(',')[1];
        // console.log(questionNumber)
        let studentExamAnswerId = $('input[name="studentExamAnswerId"]').attr("value");
        // console.log(studentExamAnswerId)
        let studentAnswerName = 'studentAnswerQId' + questionId;
        // console.log(studentAnswerName)
        // console.log(`#${studentAnswerName}`);
        let studentAnswer = $(`#${studentAnswerName}`).val().trim();
        // console.log(studentAnswer)
        answers[Number(questionNumber)] = [questionId, studentAnswer];

        console.log(answers)

        localStorage.setItem(`Answers`, answers);

        let token = $("meta[name='_csrf']").attr("content");
        let header = $("meta[name='_csrf_header']").attr("content");


        $.ajax({
            type: 'POST',
            url: `http://localhost:8080/question/save-answer`,
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            data: {
                studentExamAnswerId: studentExamAnswerId,
                questionId: questionId,
                studentAnswer: studentAnswer
            },
            success: function () {
                console.log("teacher changed!");
            }
        });
    });

})




