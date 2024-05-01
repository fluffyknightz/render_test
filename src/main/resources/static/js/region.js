let table
let data_id
let button = document.getElementById('myButton');
let title = document.getElementById('modelTitle');
let input = document.getElementById('region');


async function adding() {
    validationInput()
    console.log("add reach")
    if (inputValue === undefined || inputValue === null || inputValue.trim() === "") {
        Notiflix.Notify.failure("Please enter All Data!");
    } else {
        const url = '/spcu-dims/api/v1/region/create?name=' + encodeURIComponent(inputValue);
        await performOperation(url, 'POST', 'Create successful.');
    }
}


async function editing() {
    console.log("edit reach")
    validationInput()
    if (inputValue === undefined || inputValue === null || inputValue.trim() === "") {
        Notiflix.Notify.failure("Please enter All Data!");
    } else {
        const url = '/spcu-dims/api/v1/region/update?name=' + encodeURIComponent(inputValue) + '&&id=' + encodeURIComponent(data_id);
        await performOperation(url, 'PATCH', 'Edit successful.');
    }

}

$(document).ready(function () {

    table = new DataTable('#myTable', {
        language: {
            infoFiltered: "",
        },
        scrollX: true,
        ajax: {

            url: 'spcu-dims/api/v1/region/show/table', method: 'POST', contentType: 'application/json',

            headers: {
                [csrfHeader]: csrfToken
            },

            data: function (d) {
                d.columns[1].search.value = false;
                return JSON.stringify(d);
            },

        }, processing: true, serverSide: true, columns: [
            {
                data: 'name'
            }, {
                data: 'isDelete',
                ordering: false,
                render: function (data, type, row) {
                    return '<button type="button" class="btn btn-outline-warning p-0" data-bs-toggle="modal" ' + 'data-bs-target="#model" onclick="changeEdit(\'' + row.name.replace(/'/g, "\\'") + '\', ' + row.id + ')" style="border: none">\n' + '<i class="bi bi-pencil-square mx-1" style="font-size: 1.1rem"></i>\n' + '</button>\n' + '<button type="button" onclick="showConfirm(' + row.id + ')" class="btn btn-outline-danger p-0" style="border: none">\n' + '<i class="bi bi-trash3 mx-1" style="font-size: 1.1rem"></i>\n' + '</button>';
                }
            },]
    });

});


function showConfirm(id) {
    deleteConfirm('/spcu-dims/api/v1/region/delete/' + encodeURIComponent(id))
}


function changeAdd() {

    input.value = null;
    button.innerHTML = 'Add'
    title.textContent = 'Add Region'
    button.setAttribute('onclick', 'adding()');
    return false;
}

function changeEdit(name, id) {

    data_id = id
    input.value = name;
    button.innerHTML = 'Edit'
    title.textContent = 'Edit Region'
    button.setAttribute('onclick', 'editing()');
    return false;
}