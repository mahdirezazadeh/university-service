$(document).ready(function () {

    const answers = new Array();

    const studentExamAnswerId = $('input[name="studentExamAnswerId"]').attr("value");

    localStorage.setItem(`Answers${studentExamAnswerId}`, JSON.stringify(answers));


    //js post function for saving descriptive Question
    $('input[name="saveQuestion"]').click(function () {
        let questionId = $(this).attr("id").split(',')[0];
        // console.log(questionId);
        let questionNumber = $(this).attr("id").split(',')[1];
        // console.log(questionNumber)
        let studentAnswerName = 'studentAnswerQId' + questionId;
        // console.log(studentAnswerName)
        // console.log(`#${studentAnswerName}`);
        let studentAnswer = $(`#${studentAnswerName}`).val().trim();
        // console.log(studentAnswer)

        saveAnswer(questionId, studentAnswer, questionNumber);
    });


    $('[name^=studentAnswerQIdC]').click(function () {
        let questionId = $(this).attr("id").split(',')[0];
        // console.log(questionId);
        let questionNumber = $(this).attr("id").split(',')[1];
        // console.log(questionNumber)
        let studentAnswer = $(this).attr("id").split(',')[2];
        // console.log(studentAnswer)

        saveAnswer(questionId, studentAnswer, questionNumber);
    });

    function saveAnswer(questionId, studentAnswer, questionNumber) {

        var Answers = JSON.parse(localStorage.getItem(`Answers${studentExamAnswerId}`));

        Answers[Number(questionNumber)] = [questionId, studentAnswer];

        console.log(Answers)

        localStorage.setItem(`Answers${studentExamAnswerId}`, JSON.stringify(Answers));

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
    }

    $('input[name="finishExam"]').click(function () {

        var Answers = JSON.parse(localStorage.getItem(`Answers${studentExamAnswerId}`));

        console.log(Answers)

        var sendingAnswers = {};

        let j = 0;
        for (let i = 0; i < Answers.length; i++) {
            if (Answers[i] != null) {
                sendingAnswers[j] = Answers[i];
                j = j + 1;
            }
        }

        let token = $("meta[name='_csrf']").attr("content");
        let header = $("meta[name='_csrf_header']").attr("content");

        $.ajax({
            type: 'POST',
            url: `http://localhost:8080/question/save-answers`,
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            data: {
                studentExamAnswerId: studentExamAnswerId,
                sendingAnswers
            },
            success: function () {
                console.log("teacher changed!");
            }
        });
        window.location.replace(`http://localhost:8080/`);
    });


})




