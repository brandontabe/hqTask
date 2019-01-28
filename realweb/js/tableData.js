$(document).ready(function () {
    getAllTasks(true);
});

function updateTask(id) {
    getTaskById(id);
}

function deleteTask(id){
    $.ajax({
        url: 'http://localhost:9000/v1/task/remove/' + id,
        dataType: 'json',
        type: 'GET',
        contentType: 'application/json',
        success: function (data, textStatus, jQxhr) {
            location.reload();
        },
        error: function (jqXhr, textStatus, errorThrown) {
            console.log(errorThrown);
        }

    });

}

function updateTaskById() {
    dueDate = $("#dueDate").val();
    description = $("#description").val();
    id = $("#id").val();

    var task = {
        id: id,
        description: description,
        dueDate: dueDate,
        overdue: false
    };
    $.ajax({
        url: 'http://localhost:9000/v1/task/',
        dataType: 'json',
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(task),
        success: function (data, textStatus, jQxhr) {
            $('#taskModalUpdate').modal('hide');
            location.reload();
        },
        error: function (jqXhr, textStatus, errorThrown) {
            console.log(errorThrown);
        }
    });
}

function getTaskById(id) {

    $.ajax({
        url: 'http://localhost:9000/v1/task/' + id,
        dataType: 'json',
        type: 'GET',
        contentType: 'application/json',
        success: function (data, textStatus, jQxhr) {
            $("#id").val(data.id);
            $('#dueDate').val(data.dueDate.substring(0, 10));
            $("#description").val(data.description);
            $("#overdue").prop('checked', data.overdue);
            $('#taskModalUpdate').modal('show');
        },
        error: function (jqXhr, textStatus, errorThrown) {
            console.log(errorThrown);
        }

    });
}


function getAllTasks(draw) {
    $.ajax({
        url: 'http://localhost:9000/v1/task/',
        dataType: 'json',
        type: 'GET',
        contentType: 'application/json',

        success: function (data, textStatus, jQxhr) {
            if (draw) {
                drawTable(data);
            }
        },
        error: function (jqXhr, textStatus, errorThrown) {
            console.log(errorThrown);
        }
    });
}



function drawTable(data) {
    for (var i = 0; i < data.length; i++) {
        drawRow(data[i]);
    }
}

function drawRow(rowData) {
    var yesno = rowData.overdue == true ? "Yes" : "No";
    var row = $("<tr />")
    $("#tasktable").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
    row.append($("<td>" + rowData.id + "</td>"));
    row.append($("<td>" + rowData.description + "</td>"));
    row.append($("<td>" + rowData.dueDate + "</td>"));
    row.append($("<td>" + yesno + "</td>"));
    row.append($(`<td><div style="cursor: pointer" onclick="updateTask(${rowData.id})"><i class="fa fa-pencil" style="font-size:15px"></i></div></td>`));
    row.append($(`<td><div style="cursor: pointer" onclick="deleteTask(${rowData.id})"><i class="fa fa-trash-o" style="font-size:15px"></i></div></td>`));
    //row.append($(`<td><a href="http://localhost:9000/v1/task/remove/${rowData.id}"><i class="fa fa-trash-o" style="font-size:15px;color:red"></i></a></td>`));


}


$(window).on("load resize ", function () {
    var scrollWidth = $('.tbl-content').width() - $('.tbl-content table').width();
    $('.tbl-header').css({ 'padding-right': scrollWidth });
}).resize();