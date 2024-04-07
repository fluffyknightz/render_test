let table
let data_id
let button = document.getElementById('myButton');
let title = document.getElementById('modelTitle');
let input = document.getElementById('user');
let roleSelect = document.getElementById('roleSelect');
let password = document.getElementById('password');
let confirmPassword = document.getElementById('confirmPassword');
let passwordLabel = document.getElementById('passwordLabel');
let password_form = document.getElementsByClassName('password-form');
let user_role = document.getElementById('login-role')
let user_id = document.getElementById('login-id')

async function adding() {

    inputValue = input.value.trim();
    // Remove leading and trailing spaces
    inputValue = inputValue.replace(/^\s+|\s+$/g, '');
    // Replace consecutive spaces with a single space
    inputValue = inputValue.replace(/\s+/g, ' ');

    if (inputValue === undefined || inputValue === null || inputValue.trim() === "" || roleSelect.value === undefined || roleSelect.value === null || roleSelect.value.trim() === "" || password.value === undefined || password.value === null || password.value.trim() === "" || confirmPassword.value === undefined || confirmPassword.value === null || confirmPassword.value.trim() === "") {
        Notiflix.Notify.failure("Please enter All Data!");
    } else if (password.value !== confirmPassword.value) {
        Notiflix.Notify.failure("Password and Confirm Password do not match!");
    } else if ((password.value !== '' && (password.value.length < 8 || password.value.length > 12)) ||
        (confirmPassword.value !== '' && (confirmPassword.value.length < 8 || confirmPassword.value.length > 12))) {
        Notiflix.Notify.failure("Password should be between 8 and 12 characters.");
    } else {
        const url = '/spcu-dims/api/v1/user/create?username=' + encodeURIComponent(inputValue) + '&&role=' + encodeURIComponent(roleSelect.value) + '&&password=' + encodeURIComponent(password.value);
        await performOperation(url, 'POST', 'Create successful.');
    }

}

async function editing(id) {

    inputValue = input.value.trim();
    // Remove leading and trailing spaces
    inputValue = inputValue.replace(/^\s+|\s+$/g, '');
    // Replace consecutive spaces with a single space
    inputValue = inputValue.replace(/\s+/g, ' ');

    if (inputValue === undefined || inputValue === null || inputValue.trim() === "") {
        Notiflix.Notify.failure("Please enter All Data!");
    } else if (password.value !== confirmPassword.value) {
        Notiflix.Notify.failure("Password and Confirm Password do not match!");
    } else if ((password.value !== '' && (password.value.length < 8 || password.value.length > 12)) ||
        (confirmPassword.value !== '' && (confirmPassword.value.length < 8 || confirmPassword.value.length > 12))) {
        Notiflix.Notify.failure("Password should be between 8 and 12 characters.");
    } else {
        let url = '/spcu-dims/api/v1/user/update?id=' + encodeURIComponent(data_id) + '&&username=' + encodeURIComponent(inputValue) + '&&role=' + encodeURIComponent(roleSelect.value);
        url = password.value !== '' && password.value !== null ? url + '&&password=' + encodeURIComponent(password.value) : url;

        await performOperation(url, 'PATCH', 'Edit successful.')

    }

}


$(document).ready(async function () {

    await roleFetch()
    roleSelect.options[1].style.display = 'none';
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

        },
        processing: true,
        serverSide: true,
        columns: [
            {
                data: 'username'
            }, {
                data: 'role.name'
            }, {
                data: 'isDelete', ordering: false, render: function (data, type, row) {
                    if(user_role.innerText === 'DefaultAdmin'){
                        if (row.role.name === 'DefaultAdmin') {
                            return '<button type="button" class="btn btn-outline-warning p-0" data-bs-toggle="modal" ' + 'data-bs-target="#model" onclick="changeEdit(\'' + row.username.replace(/'/g, "\\'") + '\', ' + row.id + ', \'' + row.role.name.replace(/'/g, "\\'") + '\')" style="border: none">\n' + '<i class="bi bi-pencil-square mx-1" style="font-size: 1.1rem"></i>\n' + '</button>';
                        } else {
                            return '<button type="button" class="btn btn-outline-warning p-0" data-bs-toggle="modal" ' + 'data-bs-target="#model" onclick="changeEdit(\'' + row.username.replace(/'/g, "\\'") + '\', ' + row.id + ', \'' + row.role.name.replace(/'/g, "\\'") + '\')" style="border: none">\n' + '<i class="bi bi-pencil-square mx-1" style="font-size: 1.1rem"></i>\n' + '</button>\n' + '<button type="button" onclick="showConfirm(' + row.id + ')" class="btn btn-outline-danger p-0" style="border: none">\n' + '<i class="bi bi-trash3 mx-1" style="font-size: 1.1rem"></i>\n' + '</button>';
                        }
                    } else if (user_role.innerText === 'Admin') {
                        if (row.id === parseInt(user_id.innerText) || row.role.name ===  'User'){
                            return '<button type="button" class="btn btn-outline-warning p-0" data-bs-toggle="modal" ' + 'data-bs-target="#model" onclick="changeEdit(\'' + row.username.replace(/'/g, "\\'") + '\', ' + row.id + ', \'' + row.role.name.replace(/'/g, "\\'") + '\')" style="border: none">\n' + '<i class="bi bi-pencil-square mx-1" style="font-size: 1.1rem"></i>\n' + '</button>\n' + '<button type="button" onclick="showConfirm(' + row.id + ')" class="btn btn-outline-danger p-0" style="border: none">\n' + '<i class="bi bi-trash3 mx-1" style="font-size: 1.1rem"></i>\n' + '</button>';
                        } else {
                            return  null;
                        }
                    }


                    // return '<button type="button" class="btn btn-outline-warning p-0" data-bs-toggle="modal" ' + 'data-bs-target="#model" onclick="changeEdit(\'' + row.username.replace(/'/g, "\\'") + '\', ' + row.id + ', \'' + row.role.name.replace(/'/g, "\\'") + '\')" style="border: none">\n' + '<i class="bi bi-pencil-square mx-1" style="font-size: 1.1rem"></i>\n' + '</button>\n' + '<button type="button" onclick="showConfirm(' + row.id + ')" class="btn btn-outline-danger p-0" style="border: none">\n' + '<i class="bi bi-trash3 mx-1" style="font-size: 1.1rem"></i>\n' + '</button>';
                }
            }
        ]
    });

});


function showConfirm(id) {
    deleteConfirm('/spcu-dims/api/v1/user/delete/' + encodeURIComponent(id))
}


function changeAdd() {

    password.setAttribute('placeholder', 'Password(Optional)');
    passwordLabel.innerText = 'Password'
    input.value = null;
    password.value = null;
    confirmPassword.value = null;
    button.innerHTML = 'Add'
    roleSelect.selectedIndex = 0;
    roleSelect.disabled = false;
    title.textContent = 'Add User'
    button.setAttribute('onclick', 'adding()');
    for (let i = 0; i < password_form.length; i++) {
        password_form[i].style.display = 'block';
    }
    return false;
}

function changeEdit(name, id, role) {

    password.setAttribute('placeholder', 'Password');
    passwordLabel.innerText = 'Password(Optional)';
    password.value = null;
    confirmPassword.value = null;
    data_id = id
    input.value = name;

    if (role === user_role.innerText || role === 'DefaultAdmin') {
        roleSelect.value = role;
        roleSelect.disabled = true;
    } else {
        roleSelect.value = role;
        roleSelect.disabled = false;
    }

    button.innerHTML = 'Edit'
    title.textContent = 'Edit User'
    button.setAttribute('onclick', 'editing(' + id + ')');

    if (user_role.innerText === role) {
        // Loop through each element and hide it
        for (let i = 0; i < password_form.length; i++) {
            password_form[i].style.display = 'none';
        }
    } else {
        for (let i = 0; i < password_form.length; i++) {
            password_form[i].style.display = 'block';
        }
    }
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