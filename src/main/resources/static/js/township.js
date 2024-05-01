let table
let data_id

let button = document.getElementById('myButton');
let title = document.getElementById('modelTitle');
let input = document.getElementById('township');

let districtSelect = document.getElementById('districtSelect');
let regionSelect = document.getElementById('regionSelect');
let zoneSelect = document.getElementById('zoneSelect');

let districtFilter = document.getElementById('filterDistrict');
let regionFilter = document.getElementById('filterRegion');
let zoneFilter = document.getElementById('filterZone');


async function adding() {
    validationInput()

    if (inputValue === undefined || inputValue === null || inputValue.trim() === "" || districtSelect.value.trim() === "" || zoneSelect.value.trim() === "") {
        Notiflix.Notify.failure("Please enter All Data!");
    } else {
        const url = '/spcu-dims/api/v1/township/create?name=' + encodeURIComponent(inputValue) + '&&district=' + encodeURIComponent(districtSelect.value) + '&&zone=' + encodeURIComponent(zoneSelect.value);
        await performOperation(url, 'POST', 'Create successful.');
    }
}

function editing() {
    console.log("edit reach")
    validationInput()
    if (inputValue === undefined || inputValue === null || inputValue.trim() === "" || districtSelect.value.trim() === "") {
        Notiflix.Notify.failure("Please enter All Data!");
    } else {
        const url = '/spcu-dims/api/v1/township/update?name=' + encodeURIComponent(inputValue) + '&&id=' + encodeURIComponent(data_id) + '&&district=' + encodeURIComponent(districtSelect.value) + '&&zone=' + encodeURIComponent(zoneSelect.value);
        performOperation(url, 'PATCH', 'Edit successful.');
    }

}

$(document).ready(async function () {
    await districtFetch()
    await regionFetch()
    await zoneFetch()
    table = new DataTable('#myTable', {
        language: {
            infoFiltered: "",
        },
        scrollX: true,
        ajax: {

            url: 'spcu-dims/api/v1/township/show/table', method: 'POST', contentType: 'application/json',

            headers: {
                [csrfHeader]: csrfToken
            },

            data: function (d) {
                d.columns[4].search.value = false;
                return JSON.stringify(d);
            },

        }, processing: true, serverSide: true, columns: [
            {
                data: 'name'
            }, {
                data: 'zone.name'
            }, {
                data: 'district.region.name'
            }, {
                data: 'district.name'
            }, {
                data: 'isDelete',
                ordering: false,
                render: function (data, type, row) {
                    return '<button type="button" class="btn btn-outline-warning p-0" data-bs-toggle="modal" data-bs-target="#model" onclick="changeEdit(\'' + row.name.replace(/'/g, "\\'") + '\', ' + row.id + ', \'' + row.district.name.replace(/'/g, "\\'") + '\', \'' + row.district.region.name.replace(/'/g, "\\'") + '\',\'' + row.zone.name.replace(/'/g, "\\'") + '\')" style="border: none">\n' + '<i class="bi bi-pencil-square mx-1" style="font-size: 1.1rem"></i>\n' + '</button>\n' + '<button type="button" onclick="showConfirm(' + row.id + ')" class="btn btn-outline-danger p-0" style="border: none">\n' + '<i class="bi bi-trash3 mx-1" style="font-size: 1.1rem"></i>\n' + '</button>';
                }

            },]
    });

});


function showConfirm(id) {
    deleteConfirm('/spcu-dims/api/v1/township/delete/' + encodeURIComponent(id))
}

zoneFilter.addEventListener('change', function () {
    table.column(1).search(zoneFilter.value).draw();
})

regionFilter.addEventListener('change', function () {
    table.column(2).search(regionFilter.value).draw();
})

districtFilter.addEventListener('change', function () {
    table.column(3).search(districtFilter.value).draw();
})


regionSelect.addEventListener('change', async function () {
    await districtSpecificFetch(regionSelect.value)
})

async function changeAdd() {
    regionSelect.selectedIndex = 0;
    districtSelect.selectedIndex = 0;
    zoneSelect.selectedIndex = 0;
    input.value = null;
    button.innerHTML = 'Add'
    title.textContent = 'Add Township'
    button.setAttribute('onclick', 'adding()');
    return false;
}

async function changeEdit(name, id, district, region, zone) {
    await districtSpecificFetch(region)
    zoneSelect.value = zone
    regionSelect.value = region
    districtSelect.value = district

    data_id = id
    input.value = name;
    button.innerHTML = 'Edit'
    title.textContent = 'Edit Township'
    button.setAttribute('onclick', 'editing()');
    return false;
}


async function districtSpecificFetch(region) {
    try {
        const data = await fetchData('spcu-dims/api/v1/district/show', `region=${encodeURIComponent(region)}`);
        await populateSelect(districtSelect, data, "Select Related District", false);
    } catch (error) {
        Notiflix.Notify.failure('Data Fetching Error!');
    }
}

async function districtFetch() {
    try {
        const data = await fetchData('spcu-dims/api/v1/district/show/all');
        await populateSelect(districtFilter, data, "Filter District", true);
    } catch (error) {
        Notiflix.Notify.failure('Data Fetching Error!');
    }
}

async function regionFetch() {
    try {
        const data = await fetchData('spcu-dims/api/v1/region/show/all');
        await populateSelect(regionFilter, data, "Filter Region", true);
        await populateSelect(regionSelect, data, "Select Related Region", false);
    } catch (error) {
        Notiflix.Notify.failure('Data Fetching Error!');
    }
}

async function zoneFetch() {
    try {
        const data = await fetchData('spcu-dims/api/v1/zone/show/all');
        await populateSelect(zoneFilter, data, "Filter Zone", true);
        await populateSelect(zoneSelect, data, "Select Related Zone", false);
    } catch (error) {
        Notiflix.Notify.failure('Data Fetching Error!');
    }
}