let table
let data_id
let button = document.getElementById('myButton');
let title = document.getElementById('modelTitle');
let input = document.getElementById('parish');
let noteInput = document.getElementById('note')
let zoneFilter = document.getElementById('filterZone');
let townshipFilter = document.getElementById('filterTownship');
let townshipSelect = document.getElementById('townshipSelect');
let congregationFilter = document.getElementById('filterCongregation');
let congregationSelect = document.getElementById('congregationSelect');

$(document).ready(async function () {

    await zoneFetch();
    await townshipFetch();
    await congregationFetch();

    function format(d) {
        let note
        if (d.note !== null) {
            note = d.note
        } else {
            note = "No note"
        }
        return (
            '<dl>' +
            '<dd>' +
            note +
            '</dd>' +
            '</dl>'
        );
    }

    table = new DataTable('#myTable', {
        language: {
            infoFiltered: "",
        }, ajax: {
            url: 'spcu-dims/api/v1/parish/show/table', method: 'POST', contentType: 'application/json', headers: {
                [csrfHeader]: csrfToken
            }, data: function (d) {
                d.columns[9].search.value = false;
                return JSON.stringify(d);
            },
        },
        processing: true,
        serverSide: true,
        scrollX: true,
        columns: [
            {
                className: 'dt-control',
                orderable: false,
                data: 'note',
                defaultContent: '',
                render: function () {
                    return null;
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
                data: 'congregation.name'
            }, {
                data: 'township.district.region.name'
            }, {
                data: 'township.district.name'
            }, {
                data: 'township.name'
            }, {
                data: 'isDelete',
                ordering: false,
                render: function (data, type, row) {
                    if (row.note === null) {
                        return '<button type="button" class="btn btn-outline-warning p-0" data-bs-toggle="modal"' + ' data-bs-target="#model" onclick="changeEdit(' + row.id + ', \'' + row.township.name.replace(/'/g, "\\'") + '\', \'' + row.congregation.name.replace(/'/g, "\\'") + '\', \'' + row.name.replace(/'/g, "\\'") + '\', \'\')" style="border: none">\n' + '<i class="bi bi-pencil-square mx-1" style="font-size: 1.1rem"></i>\n' + '</button>\n' + '<button type="button" onclick="showConfirm(' + row.id + ')" class="btn btn-outline-danger p-0" style="border: none">\n' + '<i class="bi bi-trash3 mx-1" style="font-size: 1.1rem"></i>\n' + '</button>';
                    }
                    return '<button type="button" class="btn btn-outline-warning p-0" data-bs-toggle="modal"' + ' data-bs-target="#model" onclick="changeEdit(' + row.id + ', \'' + row.township.name.replace(/'/g, "\\'") + '\', \'' + row.congregation.name.replace(/'/g, "\\'") + '\', \'' + row.name.replace(/'/g, "\\'") + '\', \'' + row.note.replace(/'/g, "\\'") + '\')" style="border: none">\n' + '<i class="bi bi-pencil-square mx-1" style="font-size: 1.1rem"></i>\n' + '</button>\n' + '<button type="button" onclick="showConfirm(' + row.id + ')" class="btn btn-outline-danger p-0" style="border: none">\n' + '<i class="bi bi-trash3 mx-1" style="font-size: 1.1rem"></i>\n' + '</button>';
                }
            },]
    });

    table.on('click', 'td.dt-control', function (e) {
        let tr = e.target.closest('tr');
        let row = table.row(tr);

        if (row.child.isShown()) {
            // This row is already open - close it
            row.child.hide();
        } else {
            // Open this row
            row.child(format(row.data())).show();
        }
    });

});

zoneFilter.addEventListener('change', function () {
    table.column(4).search(zoneFilter.value).draw();
})

townshipFilter.addEventListener('change', function () {
    table.column(8).search(townshipFilter.value).draw();
})

congregationFilter.addEventListener('change', function () {
    table.column(5).search(congregationFilter.value).draw();
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

async function congregationFetch() {
    try {
        const data = await fetchData('spcu-dims/api/v1/congregation/show/all');
        await populateSelect(congregationSelect, data, "Select Related Congregation", false);
        await populateSelect(congregationFilter, data, "Filter Congregation", true);
    } catch (error) {
        Notiflix.Notify.failure('Data Fetching Error!');
    }
}

function changeAdd() {
    townshipSelect.selectedIndex = 0;
    congregationSelect.selectedIndex = 0;
    input.value = null;
    noteInput.value = null;
    noteInput.placeholder = 'Note(Optional)'
    button.innerHTML = 'Add'
    title.textContent = 'Add Parish'
    button.setAttribute('onclick', 'adding()');
    return false;
}

function changeEdit(id, township, congregation, name, note) {
    townshipSelect.value = township;
    congregationSelect.value = congregation;
    data_id = id;
    input.value = name;
    noteInput.value = note;
    noteInput.placeholder = 'Note'
    button.innerHTML = 'Edit';
    title.textContent = 'Edit Parish';
    button.setAttribute('onclick', 'editing()');
    return false;
}

function showConfirm(id) {
    deleteConfirm('/spcu-dims/api/v1/parish/delete/' + encodeURIComponent(id))
}

async function editing() {
    validationInput()
    if (inputValue === undefined || inputValue === null || inputValue.trim() === "" || townshipSelect.value.trim() === "" || congregationSelect.value.trim() === "") {
        Notiflix.Notify.failure("Please enter All Data!");
    } else {
        const url = noteInput.value !== null && noteInput.value !== '' ?
            '/spcu-dims/api/v1/parish/update?name=' + encodeURIComponent(inputValue) + '&&id=' + encodeURIComponent(data_id) + '&&township=' + encodeURIComponent(townshipSelect.value) + '&&congregation=' + encodeURIComponent(congregationSelect.value) + '&&note=' + encodeURIComponent(noteInput.value) :
            '/spcu-dims/api/v1/parish/update?name=' + encodeURIComponent(inputValue) + '&&id=' + encodeURIComponent(data_id) + '&&township=' + encodeURIComponent(townshipSelect.value) + '&&congregation=' + encodeURIComponent(congregationSelect.value);
        await performOperation(url, 'PATCH', 'Edit successful.');
    }
}

async function adding() {
    validationInput()
    if (inputValue === undefined || inputValue === null || inputValue.trim() === "" || townshipSelect.value.trim() === "") {
        Notiflix.Notify.failure("Please enter All Data!");
    } else {
        const url = noteInput.value !== null && noteInput.value !== '' ?
            '/spcu-dims/api/v1/parish/create?name=' + encodeURIComponent(inputValue) + '&&township=' + encodeURIComponent(townshipSelect.value) + '&&congregation=' + encodeURIComponent(congregationSelect.value) + '&&note=' + encodeURIComponent(noteInput.value) :
            '/spcu-dims/api/v1/parish/create?name=' + encodeURIComponent(inputValue) + '&&township=' + encodeURIComponent(townshipSelect.value) + '&&congregation=' + encodeURIComponent(congregationSelect.value);
        await performOperation(url, 'POST', 'Create successful.');
    }
}


