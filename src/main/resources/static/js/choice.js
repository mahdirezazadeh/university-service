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

    // function removeChoice(button) {
    //     console.log(button.attr("id"));
    // };
    //
    // $('button[name="removeChoice"]').click(removeChoice());

    // $("body").on("click", "#btnAdd", function () {
    //     //Reference the Name and Country TextBoxes.
    //     var txtName = $("#txtName");
    //     var txtCountry = $("#txtCountry");
    //
    //     //Get the reference of the Table's TBODY element.
    //     var tBody = $("#tblCustomers > TBODY")[0];
    //
    //     //Add Row.
    //     var row = tBody.insertRow(-1);
    //
    //     //Add Name cell.
    //     var cell = $(row.insertCell(-1));
    //     cell.html(txtName.val());
    //
    //     //Add Country cell.
    //     cell = $(row.insertCell(-1));
    //     cell.html(txtCountry.val());
    //
    //     //Add Button cell.
    //     cell = $(row.insertCell(-1));
    //     var btnRemove = $("<input />");
    //     btnRemove.attr("type", "button");
    //     btnRemove.attr("onclick", "Remove(this);");
    //     btnRemove.val("Remove");
    //     cell.append(btnRemove);
    //
    //     //Clear the TextBoxes.
    //     txtName.val("");
    //     txtCountry.val("");
    // });
    //
    // function Remove(button) {
    //     //Determine the reference of the Row using the Button.
    //     var row = $(button).closest("TR");
    //     var name = $("TD", row).eq(0).html();
    //     if (confirm("Do you want to delete: " + name)) {
    //         //Get the reference of the Table.
    //         var table = $("#tblCustomers")[0];
    //
    //         //Delete the Table row using it's Index.
    //         table.deleteRow(row[0].rowIndex);
    //     }
    // };

    $("body").on("click", "#btnSave", function () {
        //Loop through the Table rows and build a JSON array.
        var customers = new Array();
        $("#tblCustomers TBODY TR").each(function () {
            var row = $(this);
            var customer = {};
            customer.Name = row.find("TD").eq(0).html();
            customer.Country = row.find("TD").eq(1).html();
            customers.push(customer);
        });

        //Send the JSON array to Controller using AJAX.
        $.ajax({
            type: "POST",
            url: "/Home/InsertCustomers",
            data: JSON.stringify(customers),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (r) {
                alert(r + " record(s) inserted.");
            }
        });
    });
})


// $(`button[class="input choiceInput"]`).on('change', `button[name="removeChoice"]`, function () {
//     console.log($(this));
// })




