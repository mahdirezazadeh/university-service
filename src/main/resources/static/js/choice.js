$(document).ready(function () {

    $('#addChoice').click(function () {
        let numberOfChoice = $('#numberOfChoices').val();
        $(`#choiceDiv${numberOfChoice - 1}`).after(`
                <div class="container" id="choiceDiv${numberOfChoice}">
                    <div class="container">
                        <input class="choiceRadio" name="writeAnswer" type="radio" value="${numberOfChoice}">
                    </div>
                    <div class="container AnswerText">
                        <input class="input" name="choices[${numberOfChoice}]" id="choice${numberOfChoice}" required="required" type="text"/>
                    </div>
                </div>
        `)
        $('#numberOfChoices').val(Number(1) + Number(numberOfChoice));
    });
})
