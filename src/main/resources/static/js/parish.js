let table
let data_id
let button = document.getElementById('myButton');
let title = document.getElementById('modelTitle');
let input = document.getElementById('parish');
let zoneFilter = document.getElementById('filterZone');
let townshipFilter = document.getElementById('filterTownship');
let townshipSelect = document.getElementById('townshipSelect');


$(document).ready(async function () {

    await zoneFetch();
    await townshipFetch();

    table = $('#myTable').DataTable({
        dom: 'flrtip', language: {
            infoFiltered: "",
        }, ajax: {
            url: 'spcu-dims/api/v1/parish/show/table', method: 'POST', contentType: 'application/json', headers: {
                [csrfHeader]: csrfToken
            }, data: function (d) {
                d.columns[8].search.value = false;
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
            data: 'township.zone.diocese.archdiocese.name'
        }, {
            data: 'township.zone.diocese.name'
        }, {
            data: 'township.zone.name'
        }, {
            data: 'township.district.region.name'
        }, {
            data: 'township.district.name'
        }, {
            data: 'township.name'
        }, {
            data: 'isDelete', render: function (data, type, row) {
                let escapedName = row.name.replace("'", "\\'");
                return '<button type="button" class="btn btn-outline-warning p-0" data-bs-toggle="modal"' + ' data-bs-target="#model" onclick="changeEdit(' + row.id + ', \'' + row.township.name + '\', \'' + escapedName + '\')" style="border: none">\n' + '<i class="bi bi-pencil-square mx-1" style="font-size: 1.1rem"></i>\n' + '</button>\n' + '<button type="button" onclick="showConfirm(' + row.id + ')" class="btn btn-outline-danger p-0" style="border: none">\n' + '<i class="bi bi-trash3 mx-1" style="font-size: 1.1rem"></i>\n' + '</button>';
            }
        }]
    });
});

zoneFilter.addEventListener('change', function () {
    table.column(4).search(zoneFilter.value).draw();
})

townshipFilter.addEventListener('change', function () {
    table.column(7).search(townshipFilter.value).draw();
})

async function zoneFetch() {
    try {
        const data = await fetchData('spcu-dims/api/v1/zone/show/all');
        await populateSelect(zoneFilter, data, "Filter Zone", true);
    } catch (error) {
        Notiflix.Notify.failure('Data Fetching Error!');
    }
}

async function townshipFetch() {
    try {
        const data = await fetchData('spcu-dims/api/v1/township/show/all');
        await populateSelect(townshipSelect, data, "Select Related Township", false);
        await populateSelect(townshipFilter, data, "Filter Township", true);
    } catch (error) {
        Notiflix.Notify.failure('Data Fetching Error!');
    }
}

function changeAdd() {
    townshipSelect.selectedIndex = 0;
    input.value = null;
    button.innerHTML = 'Add'
    title.textContent = 'Add Parish'
    button.setAttribute('onclick', 'adding()');
    return false;
}

function changeEdit(id, township, name) {
    townshipSelect.value = township;
    data_id = id;
    input.value = name;
    button.innerHTML = 'Edit';
    title.textContent = 'Edit Parish';
    button.setAttribute('onclick', 'editing()');
    return false;
}

function showConfirm(id) {
    deleteConfirm('/spcu-dims/api/v1/parish/delete/' + encodeURIComponent(id))
}

function editing() {
    validationInput()
    if (inputValue === undefined || inputValue === null || inputValue.trim() === "" || townshipSelect.value.trim() === "") {
        Notiflix.Notify.failure("Please enter All Data!");
    } else {
        const url = '/spcu-dims/api/v1/parish/update?name=' + encodeURIComponent(inputValue) + '&&id=' + encodeURIComponent(data_id) + '&&township=' + encodeURIComponent(townshipSelect.value);
        performOperation(url, 'PATCH', 'Edit successful.');
    }
}

function adding() {
    validationInput()
    if (inputValue === undefined || inputValue === null || inputValue.trim() === "" || townshipSelect.value.trim() === "") {
        Notiflix.Notify.failure("Please enter All Data!");
    } else {
        const url = '/spcu-dims/api/v1/parish/create?name=' + encodeURIComponent(inputValue) + '&&township=' + encodeURIComponent(townshipSelect.value);
        performOperation(url, 'POST', 'Create successful.');
    }
}


