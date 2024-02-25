let table
let data_id
let button = document.getElementById('myButton');
let title = document.getElementById('modelTitle');
let input = document.getElementById('zone');
let dioceseSelect = document.getElementById('dioceseSelect');
let dioceseFilter = document.getElementById('filterDiocese');

$(document).ready(async function () {
    await dioceseFetch()
    table = $('#myTable').DataTable({
        dom: 'flrtip',
        language: {
            infoFiltered: null,
        },
        ajax: {
            url: 'spcu-dims/api/v1/zone/show/table',
            method: 'POST',
            contentType: 'application/json',
            headers: {
                [csrfHeader]: csrfToken
            },
            data: function (d) {
                d.columns[3].search.value = false;
                return JSON.stringify(d);
            },
        },
        processing: true,
        serverSide: true,
        stateSave: true,
        columns: [{
            data: 'id',
            render: function (data, type, row, meta) {
                // meta.row contains the row index
                return meta.row + 1;
            }
        }, {
            data: 'name'
        },{
            data: 'diocese.name'
        }, {
            data: 'isDelete',
            render: function (data, type, row) {
                return '<button type="button" class="btn btn-outline-warning p-0" data-bs-toggle="modal" ' + 'data-bs-target="#model" onclick="changeEdit(\'' + row.name + '\', ' + row.id + ', \'' + row.diocese.name + '\')" style="border: none">\n' + '<i class="bi bi-pencil-square mx-1" style="font-size: 1.1rem"></i>\n' + '</button>\n' +
                       '<button type="button" onclick="showConfirm(' + row.id + ')" class="btn btn-outline-danger p-0" style="border: none">\n' + '<i class="bi bi-trash3 mx-1" style="font-size: 1.1rem"></i>\n' + '</button>';
            }
        },]
    });
});

dioceseFilter.addEventListener('change',function (){
    table.column(2).search(dioceseFilter.value).draw();
})

async function dioceseFetch(){
    try {
        const data = await fetchData('spcu-dims/api/v1/diocese/show/all');
        await populateSelect(dioceseSelect, data, "Select Related Archdiocese", false);
        await populateSelect(dioceseFilter, data, "Filter Archdiocese", true);
    } catch (error) {
        Notiflix.Notify.failure('Data Fetching Error!');
    }
}

function changeAdd() {
    input.value = null;
    button.innerHTML = 'Add'
    title.textContent = 'Add Zone'
    button.setAttribute('onclick', 'adding()');
    return false;
}

function editing() {
    console.log("edit reach")
    validationInput()
    if (inputValue === undefined || inputValue === null || inputValue.trim() === "" || dioceseSelect.value.trim() === "") {
        Notiflix.Notify.failure("Please enter All Data!");
    } else {
        const url = '/spcu-dims/api/v1/zone/update?name=' + encodeURIComponent(inputValue) + '&&id=' + encodeURIComponent(data_id) + '&&diocese=' + encodeURIComponent(dioceseSelect.value);
        performOperation(url, 'PATCH', 'Edit successful.');
    }
}

function changeEdit(name, id, diocese) {

    dioceseSelect.value = diocese
    data_id = id
    input.value = name;
    button.innerHTML = 'Edit'
    title.textContent = 'Edit Zone'
    button.setAttribute('onclick', 'editing()');
    return false;
}

function showConfirm(id) {
    Notiflix.Confirm.show('Please Confirm', 'Are you sure!! you want to delete?', 'Yes', 'No', function okCb() {
        fetch('/spcu-dims/api/v1/zone/delete/' + encodeURIComponent(id), {
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



function adding() {
    validationInput()
    if (inputValue === undefined || inputValue === null || inputValue.trim() === "" || dioceseSelect.value.trim() === "") {
        Notiflix.Notify.failure("Please enter All Data!");
    } else {
        const url = '/spcu-dims/api/v1/zone/create?name=' + encodeURIComponent(inputValue) + '&&diocese=' + encodeURIComponent(dioceseSelect.value);
        performOperation(url, 'POST', 'Create successful.');
    }
}

