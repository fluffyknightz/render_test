let table
let data_id
let button = document.getElementById('myButton');
let title = document.getElementById('modelTitle');
let input = document.getElementById('region');
let inputValue



function validationInput() {

    inputValue = input.value.trim();

    // Remove leading and trailing spaces
    inputValue = inputValue.replace(/^\s+|\s+$/g, '');

    // Replace consecutive spaces with a single space
    inputValue = inputValue.replace(/\s+/g, ' ');

    // Capitalize the first letter
    inputValue = inputValue.charAt(0).toUpperCase() + inputValue.slice(1);

}

function performOperation(url, method, successMessage) {


    fetch(url, {
        method: method, headers: {
            contentType: 'application/json', [csrfHeader]: csrfToken
        }, body: null
    }).then(async response => {
        if (response.status === 201 || response.status === 200) {
            $('#model').modal('hide');
            Notiflix.Notify.success(successMessage);
            table.ajax.reload();
        } else {

            Notiflix.Notify.failure(await response.text());
        }
    })
        .catch(error => {
            Notiflix.Notify.failure("Error has occurred during process. Please Try again later.");
            console.error(error); // Optionally log the error to the console for debugging
        });

}

function adding() {
    validationInput()
    console.log("add reach")
    const url = '/spcu-dims/api/v1/region/create?name=' + encodeURIComponent(inputValue);
    performOperation(url, 'POST', 'Create successful.');
}


function editing() {
    console.log("edit reach")
    validationInput()
    const url = '/spcu-dims/api/v1/region/update?name=' + encodeURIComponent(inputValue) + '&&id=' + encodeURIComponent(data_id);
    performOperation(url, 'PATCH', 'Edit successful.');

}

$(document).ready(function () {

    table = $('#myTable').DataTable({
        dom: 'flrtip', language: {
            infoFiltered: null,
        },

        ajax: {

            url: 'spcu-dims/api/v1/region/show/table', method: 'POST', contentType: 'application/json',

            headers: {
                [csrfHeader]: csrfToken
            },

            data: function (d) {
                d.columns[2].search.value = false;
                return JSON.stringify(d);
            },

        }, processing: true, serverSide: true, stateSave: true, columns: [{
            data: 'id', render: function (data, type, row, meta) {
                // meta.row contains the row index
                return meta.row + 1;
            }

        }, {
            data: 'name'
        }, {
            data: 'isDelete', render: function (data, type, row) {
                return '<button type="button" class="btn btn-outline-warning p-0" data-bs-toggle="modal" ' + 'data-bs-target="#model" onclick="changeEdit(\'' + row.name + '\', ' + row.id + ')" style="border: none">\n' + '<i class="bi bi-pencil-square mx-1" style="font-size: 1.1rem"></i>\n' + '</button>\n' + '<button type="button" onclick="showConfirm(' + row.id + ')" class="btn btn-outline-danger p-0" style="border: none">\n' + '<i class="bi bi-trash3 mx-1" style="font-size: 1.1rem"></i>\n' + '</button>';
            }
        },]
    });

});

Notiflix.Confirm.init({
    titleColor: '#4d56ec', okButtonColor: '#f8f8f8', okButtonBackground: '#f32222',
});

Notiflix.Notify.init({
    position: 'center-top',
});

function showConfirm(id) {
    Notiflix.Confirm.show('Please Confirm', 'Are you sure!! you want to delete?', 'Yes', 'No', function okCb() {
        fetch('/spcu-dims/api/v1/region/delete/' + id, {
            method: 'PATCH', headers: {
                [csrfHeader]: csrfToken
            }
        })
            .then(r => {
                if (r.ok) {

                    Notiflix.Notify.success('Delete successful.');
                    table.ajax.reload();


                } else {
                    Notiflix.Notify.failure('Delete Failed!');
                }
            })

    }, {
        width: '320px', borderRadius: '8px',
    },);
}

document.getElementById('myForm').addEventListener('keydown', function (e) {
    if (e.key === 'Enter') {
        e.preventDefault(); // Prevent default form submission
        button.click(); // Trigger the 'Save' button click event
    }
});



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