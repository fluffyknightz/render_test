let table
let data_id
let button = document.getElementById('myButton');
let title = document.getElementById('modelTitle');
let input = document.getElementById('district');
let regionSelect = document.getElementById('regionSelect');
let regionFilter = document.getElementById('filterRegion');


$(document).ready(async function () {
    await regionFetch()
    table = new DataTable('#myTable', {
        language: {
            infoFiltered: "",
        }, ajax: {
            url: 'spcu-dims/api/v1/district/show/table', method: 'POST', contentType: 'application/json', headers: {
                [csrfHeader]: csrfToken
            }, data: function (d) {
                d.columns[2].search.value = false;
                return JSON.stringify(d);
            },
        }, processing: true, serverSide: true, stateSave: true, columns: [
            {
            data: 'name'
        }, {
            data: 'region.name'
        }, {
            data: 'isDelete', render: function (data, type, row) {
                return '<button type="button" class="btn btn-outline-warning p-0" data-bs-toggle="modal" ' + 'data-bs-target="#model" onclick="changeEdit(\'' + row.name.replace(/'/g, "\\'") + '\', ' + row.id + ', \'' + row.region.name.replace(/'/g, "\\'") + '\')" style="border: none">\n' + '<i class="bi bi-pencil-square mx-1" style="font-size: 1.1rem"></i>\n' + '</button>\n' + '<button type="button" onclick="showConfirm(' + row.id + ')" class="btn btn-outline-danger p-0" style="border: none">\n' + '<i class="bi bi-trash3 mx-1" style="font-size: 1.1rem"></i>\n' + '</button>';
            }
        },]
    });
});

regionFilter.addEventListener('change', function () {
    table.column(1).search(regionFilter.value).draw();
})

async function regionFetch() {
    try {
        const data = await fetchData('spcu-dims/api/v1/region/show/all');
        await populateSelect(regionSelect, data, "Select Related Region", false);
        await populateSelect(regionFilter, data, "Filter Region", true);
    } catch (error) {
        Notiflix.Notify.failure('Data Fetching Error!');
    }
}

function editing() {
    console.log("edit reach")
    validationInput()
    if (inputValue === undefined || inputValue === null || inputValue.trim() === "" || regionSelect.value.trim() === "") {
        Notiflix.Notify.failure("Please enter All Data!");
    } else {
        const url = '/spcu-dims/api/v1/district/update?name=' + encodeURIComponent(inputValue) + '&&id=' + encodeURIComponent(data_id) + '&&region=' + encodeURIComponent(regionSelect.value);
        performOperation(url, 'PATCH', 'Edit successful.');
    }
}

function changeAdd() {
    regionSelect.selectedIndex = 0;
    input.value = null;
    button.innerHTML = 'Add'
    title.textContent = 'Add District'
    button.setAttribute('onclick', 'adding()');
    return false;
}

async function changeEdit(name, id, region) {

    regionSelect.value = region
    data_id = id
    input.value = name;
    button.innerHTML = 'Edit'
    title.textContent = 'Edit District'
    button.setAttribute('onclick', 'editing()');
    return false;
}

function showConfirm(id) {
    deleteConfirm('/spcu-dims/api/v1/district/delete/' + encodeURIComponent(id))
}


function adding() {
    validationInput()
    if (inputValue === undefined || inputValue === null || inputValue.trim() === "" || regionSelect.value.trim() === "") {
        Notiflix.Notify.failure("Please enter All Data!");
    } else {
        const url = '/spcu-dims/api/v1/district/create?name=' + encodeURIComponent(inputValue) + '&&region=' + encodeURIComponent(regionSelect.value);
        performOperation(url, 'POST', 'Create successful.');
    }
}



