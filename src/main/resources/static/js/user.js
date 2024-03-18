let table
let data_id
let button = document.getElementById('myButton');
let title = document.getElementById('modelTitle');
let input = document.getElementById('user');
let roleSelect = document.getElementById('roleSelect');

function adding() {
    validationInput()

    if (inputValue === undefined || inputValue === null || inputValue.trim() === "" ||
        roleSelect.value === undefined || roleSelect.value === null || roleSelect.value.trim() === "") {

        Notiflix.Notify.failure("Please enter All Data!");
    } else {
        const url = '/spcu-dims/api/v1/user/create?username=' + encodeURIComponent(inputValue) + '&&role=' + encodeURIComponent(roleSelect.value);
        performOperation(url, 'POST', 'Create successful.');
    }

}


function editing() {

    validationInput()
    if (inputValue === undefined || inputValue === null || inputValue.trim() === "") {
        Notiflix.Notify.failure("Please enter All Data!");
    } else {
        const url = '/spcu-dims/api/v1/user/update?id=' + encodeURIComponent(data_id) + '&&username=' + encodeURIComponent(inputValue) + '&&role=' + encodeURIComponent(roleSelect.value);
        performOperation(url, 'PATCH', 'Edit successful.');
    }
}

$(document).ready(async function () {
    await roleFetch()
    table = new DataTable('#myTable', {
        language: {
            infoFiltered: "",
        },

        ajax: {

            url: 'spcu-dims/api/v1/user/show/table',
            method: 'POST',
            contentType: 'application/json',

            headers: {
                [csrfHeader]: csrfToken
            },

            data: function (d) {
                d.columns[2].search.value = false;
                return JSON.stringify(d);
            },

        }, processing: true, serverSide: true,  columns: [
            {

            data: 'username'
        },{
            data: 'role.name'
        }, {
            data: 'isDelete',
                ordering: false,
                render: function (data, type, row) {
                return '<button type="button" class="btn btn-outline-warning p-0" data-bs-toggle="modal" ' + 'data-bs-target="#model" onclick="changeEdit(\'' + row.username.replace(/'/g, "\\'") + '\', ' + row.id + ' + \'' + row.role.name.replace(/'/g, "\\'") + '\')" style="border: none">\n' +
                    '<i class="bi bi-pencil-square mx-1" style="font-size: 1.1rem"></i>\n' + '</button>\n' + '<button type="button" onclick="showConfirm(' + row.id + ')" class="btn btn-outline-danger p-0" style="border: none">\n' + '<i class="bi bi-trash3 mx-1" style="font-size: 1.1rem"></i>\n' + '</button>';
            }
        },]
    });

});


function showConfirm(id) {
    deleteConfirm('/spcu-dims/api/v1/user/delete/' + encodeURIComponent(id))
}


function changeAdd() {

    input.value = null;
    button.innerHTML = 'Add'
    title.textContent = 'Add User'
    button.setAttribute('onclick', 'adding()');
    return false;
}

function changeEdit(name, id, role) {

    data_id = id
    input.value = name;
    roleSelect.value = role;
    button.innerHTML = 'Edit'
    title.textContent = 'Edit User'
    button.setAttribute('onclick', 'editing()');
    return false;
}

async function roleFetch(){
    try {
        const data = await fetchData('spcu-dims/api/v1/roles/show/all');
        await populateSelect(roleSelect, data, "Select Role",false);
    } catch (error) {
        Notiflix.Notify.failure('Data Fetching Error!');
    }
}