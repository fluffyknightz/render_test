let table
let data_id
let inputValue
let button = document.getElementById('myButton');
let title = document.getElementById('modelTitle');
let input = document.getElementById('parish');
let zoneSelect = document.getElementById('zoneSelect');
let filterZone = document.getElementById('filterZone');
let townshipSelect = document.getElementById('townshipSelect');
let filterTownship = document.getElementById('filterTownship');

Notiflix.Confirm.init({
    titleColor: '#4d56ec', okButtonColor: '#f8f8f8', okButtonBackground: '#f32222',
});

Notiflix.Notify.init({
    position: 'center-top',
});

$(document).ready(async function () {

    await zoneFetch();
    await townshipFetch();

    table = $('#myTable').DataTable({
        dom: 'flrtip',
        language: {
            infoFiltered: null,
        },
        ajax: {
            url: 'spcu-dims/api/v1/parish/show/table',
            method: 'POST',
            contentType: 'application/json',
            headers: {
                [csrfHeader]: csrfToken
            },
            data: function (d) {
                d.columns[8].search.value = false;
                return JSON.stringify(d);
            },
        },
        processing: true,
        serverSide: true,
        stateSave: true,
        columns: [
            {
                data: 'id',
                render: function (data, type, row, meta) {
                    // meta.row contains the row index
                    return meta.row + 1;
                }

            }, {
                data: 'name'
            }, {
                data: 'zone.archdiocese.diocese.name'
            }, {
                data: 'zone.archdiocese.name'
            }, {
                data: 'zone.name'
            }, {
                data: 'township.district.region.name'
            }, {
                data: 'township.district.name'
            }, {
                data: 'township.name'
            }, {
                data: 'isDelete',
                render: function (data, type, row) {
                    let escapedName = row.name.replace("'", "\\'");
                    return '<button type="button" class="btn btn-outline-warning p-0" data-bs-toggle="modal"' + ' data-bs-target="#model" onclick="changeEdit(' + row.id + ', \'' + row.township.name + '\', \'' + row.zone.name + '\', \'' + escapedName + '\')" style="border: none">\n' + '<i class="bi bi-pencil-square mx-1" style="font-size: 1.1rem"></i>\n' + '</button>\n' +
                        '<button type="button" onclick="showConfirm(' + row.id + ')" class="btn btn-outline-danger p-0" style="border: none">\n' + '<i class="bi bi-trash3 mx-1" style="font-size: 1.1rem"></i>\n' + '</button>';
                }
            }
        ]
    });
});

filterZone.addEventListener('change', function () {
    table.column(4).search(filterZone.value).draw();
})

filterTownship.addEventListener('change', function () {
    table.column(7).search(filterTownship.value).draw();
})

async function zoneFetch() {
    await fetch('spcu-dims/api/v1/zone/show/all', {
        method: 'POST',
        headers: {
            contentType: 'application/json',
            [csrfHeader]: csrfToken
        },
        body: null
    })
        .then(response => response.json())
        .then(data => {

            // Loop through the data and create options
            data.forEach(zone => {
                // Create separate option elements for each select element
                const optionSelect = document.createElement('option');
                optionSelect.value = zone.name;
                optionSelect.textContent = zone.name;
                zoneSelect.appendChild(optionSelect);

                const optionFilter = document.createElement('option');
                optionFilter.value = zone.name;
                optionFilter.textContent = zone.name;
                filterZone.appendChild(optionFilter);
            });

        })
        .catch(error => console.error('Error fetching data:', error));
}

async function townshipFetch() {
    await fetch('spcu-dims/api/v1/township/show/all', {
        method: 'POST',
        headers: {
            contentType: 'application/json',
            [csrfHeader]: csrfToken
        },
        body: null
    })
        .then(response => response.json())
        .then(data => {

            // Loop through the data and create options
            data.forEach(township => {
                // Create separate option elements for each select element
                const optionSelect = document.createElement('option');
                optionSelect.value = township.name;
                optionSelect.textContent = township.name;
                townshipSelect.appendChild(optionSelect);

                const optionFilter = document.createElement('option');
                optionFilter.value = township.name;
                optionFilter.textContent = township.name;
                filterTownship.appendChild(optionFilter);
            });

        })
        .catch(error => console.error('Error fetching data:', error));
}

async function changeAdd() {
    input.value = null;
    button.innerHTML = 'Add'
    title.textContent = 'Add Parish'
    button.setAttribute('onclick', 'adding()');
    return false;
}

async function changeEdit(id, township, zone, name) {
    zoneSelect.value = zone;
    townshipSelect.value = township;
    data_id = id;
    input.value = name;
    button.innerHTML = 'Edit';
    title.textContent = 'Edit Parish';
    button.setAttribute('onclick', 'editing()');
    return false;
}

function showConfirm(id) {
    Notiflix.Confirm.show('Please Confirm', 'Are you sure!! you want to delete?', 'Yes', 'No', function okCb() {
        fetch('/spcu-dims/api/v1/parish/delete/' + encodeURIComponent(id), {
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

function editing() {
    validationInput()
    const url = '/spcu-dims/api/v1/parish/update?name=' + encodeURIComponent(inputValue) + '&&id=' + encodeURIComponent(data_id) + '&&zone=' + encodeURIComponent(zoneSelect.value)+ '&&township=' + encodeURIComponent(townshipSelect.value);
    performOperation(url, 'PATCH', 'Edit successful.');
}

function adding() {
    validationInput()
    const url = '/spcu-dims/api/v1/parish/create?name=' + encodeURIComponent(inputValue) + '&&zone=' + encodeURIComponent(zoneSelect.value)+ '&&township=' + encodeURIComponent(townshipSelect.value);
    performOperation(url, 'POST', 'Create successful.');
}

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
