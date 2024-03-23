let table
let data_id
let button = document.getElementById('myButton');
let title = document.getElementById('modelTitle');
let input = document.getElementById('user');
let roleSelect = document.getElementById('roleSelect');
let password = document.getElementById('password');
let confirmPassword = document.getElementById('confirmPassword');
let passwordLabel = document.getElementById('passwordLabel');


function adding() {
    validationInput()

    if (inputValue === undefined || inputValue === null || inputValue.trim() === "" || roleSelect.value === undefined || roleSelect.value === null || roleSelect.value.trim() === "" || password.value === undefined || password.value === null || password.value.trim() === "" || confirmPassword.value === undefined || confirmPassword.value === null || confirmPassword.value.trim() === "" || password.value !== confirmPassword.value) {

        Notiflix.Notify.failure("Please enter All Data!");
    } else {
        const url = '/spcu-dims/api/v1/user/create?username=' + encodeURIComponent(inputValue) + '&&role=' + encodeURIComponent(roleSelect.value) + '&&password=' + encodeURIComponent(password.value);
        performOperation(url, 'POST', 'Create successful.');
    }

}


function editing() {

    validationInput()
    if (inputValue === undefined || inputValue === null || inputValue.trim() === "" || password.value !== confirmPassword.value) {
        Notiflix.Notify.failure("Please enter All Data!");
    } else {
        let url = '/spcu-dims/api/v1/user/update?id=' + encodeURIComponent(data_id) + '&&username=' + encodeURIComponent(inputValue) + '&&role=' + encodeURIComponent(roleSelect.value);
        url = password.value !== '' && password.value !== null ? url + '&&password=' + encodeURIComponent(password.value) : url;

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

            url: 'spcu-dims/api/v1/user/show/table', method: 'POST', contentType: 'application/json',

            headers: {
                [csrfHeader]: csrfToken
            },

            data: function (d) {
                d.columns[2].search.value = false;
                return JSON.stringify(d);
            },

        }, processing: true, serverSide: true, columns: [{

            data: 'username'
        }, {
            data: 'role.name', render: function (data, type, row) {
                if (row.role.name === 'ADMIN') {
                    return 'Admin';
                } else if (row.role.name === 'USER') {
                    return 'User';
                } else {
                    return data; // Return original data if not 'ADMIN' or 'USER'
                }
            }
        }, {
            data: 'isDelete', ordering: false, render: function (data, type, row) {
                return '<button type="button" class="btn btn-outline-warning p-0" data-bs-toggle="modal" ' + 'data-bs-target="#model" onclick="changeEdit(\'' + row.username.replace(/'/g, "\\'") + '\', ' + row.id + ', \'' + row.role.name.replace(/'/g, "\\'") + '\')" style="border: none">\n' + '<i class="bi bi-pencil-square mx-1" style="font-size: 1.1rem"></i>\n' + '</button>\n' + '<button type="button" onclick="showConfirm(' + row.id + ')" class="btn btn-outline-danger p-0" style="border: none">\n' + '<i class="bi bi-trash3 mx-1" style="font-size: 1.1rem"></i>\n' + '</button>';
            }
        },]
    });

});


function showConfirm(id) {
    deleteConfirm('/spcu-dims/api/v1/user/delete/' + encodeURIComponent(id))
}


function changeAdd() {

    password.setAttribute('placeholder', 'Password(Optional)');
    passwordLabel.innerText = 'Password'
    input.value = null;
    button.innerHTML = 'Add'
    roleSelect.selectedIndex = 0;
    title.textContent = 'Add User'
    button.setAttribute('onclick', 'adding()');
    return false;
}

function changeEdit(name, id, role) {
    console.log(role)
    password.setAttribute('placeholder', 'Password');
    passwordLabel.innerText = 'Password(Optional)'
    data_id = id
    input.value = name;
    roleSelect.value = role;
    button.innerHTML = 'Edit'
    title.textContent = 'Edit User'
    button.setAttribute('onclick', 'editing()');
    return false;
}

async function roleFetch() {
    try {
        const data = await fetchData('spcu-dims/api/v1/roles/show/all');
        await populateSelect(roleSelect, data, "Select Role", false);
    } catch (error) {
        Notiflix.Notify.failure('Data Fetching Error!');
    }
}