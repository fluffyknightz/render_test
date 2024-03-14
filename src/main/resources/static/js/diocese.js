let table
let data_id
let button = document.getElementById('myButton');
let title = document.getElementById('modelTitle');
let input = document.getElementById('diocese');
let archdioceseSelect = document.getElementById('archdioceseSelect');
let archdioceseFilter = document.getElementById('filterArchdiocese');


function adding() {
    // Assuming validationInput sets inputValue
    validationInput();
    console.log(archdioceseSelect.value)
    if (inputValue === undefined || inputValue === null || inputValue.trim() === "" || archdioceseSelect.value.trim() === "") {
        Notiflix.Notify.failure("Please enter All Data!");
    } else {
        const url = `/spcu-dims/api/v1/diocese/create?name=${encodeURIComponent(inputValue)}&&archdiocese=${encodeURIComponent(archdioceseSelect.value)}`;
        performOperation(url, 'POST', 'Create successful.');
    }
}



function editing() {

    validationInput()
    if (inputValue === undefined || inputValue === null || inputValue.trim() === "" || archdioceseSelect.value.trim() === "") {
        Notiflix.Notify.failure("Please enter All Data!");
    } else {
        const url = '/spcu-dims/api/v1/diocese/update?name=' + encodeURIComponent(inputValue) + '&&id=' + encodeURIComponent(data_id) + '&&archdiocese=' + encodeURIComponent(archdioceseSelect.value);
        performOperation(url, 'PATCH', 'Edit successful.');
    }

}

$(document).ready(async function () {

    await archdioceseFetch()
    table = $('#myTable').DataTable({
        dom: 'flrtip', language: {
            infoFiltered: "",
        },

        ajax: {

            url: 'spcu-dims/api/v1/diocese/show/table', method: 'POST', contentType: 'application/json',

            headers: {
                [csrfHeader]: csrfToken
            },

            data: function (d) {
                d.columns[3].search.value = false;
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
            data: 'archdiocese.name'
        }, {
            data: 'isDelete', render: function (data, type, row) {
                return '<button type="button" class="btn btn-outline-warning p-0" data-bs-toggle="modal" ' + 'data-bs-target="#model" onclick="changeEdit(\'' + row.name.replace(/'/g, "\\'") + '\', ' + row.id + ', \'' + row.archdiocese.name.replace(/'/g, "\\'") + '\')" style="border: none">\n' + '<i class="bi bi-pencil-square mx-1" style="font-size: 1.1rem"></i>\n' + '</button>\n' + '<button type="button" onclick="showConfirm(' + row.id + ')" class="btn btn-outline-danger p-0" style="border: none">\n' + '<i class="bi bi-trash3 mx-1" style="font-size: 1.1rem"></i>\n' + '</button>';
            }
        },]
    });

});



function showConfirm(id) {
    deleteConfirm('/spcu-dims/api/v1/diocese/delete/' + encodeURIComponent(id))
}



archdioceseFilter.addEventListener('change',function (){
    table.column(2).search(archdioceseFilter.value).draw();
})

function changeAdd() {
    archdioceseSelect.selectedIndex = 0;
    input.value = null;
    button.innerHTML = 'Add'
    title.textContent = 'Add Diocese'
    button.setAttribute('onclick', 'adding()');
    return false;
}

function changeEdit(name, id, archdiocese) {

    archdioceseSelect.value = archdiocese
    data_id = id
    input.value = name;
    button.innerHTML = 'Edit'
    title.textContent = 'Edit Diocese'
    button.setAttribute('onclick', 'editing()');
    return false;
}

async function archdioceseFetch(){
        try {
            const data = await fetchData('spcu-dims/api/v1/archdiocese/show/all');
            await populateSelect(archdioceseSelect, data, "Select Related Archdiocese", false);
            await populateSelect(archdioceseFilter, data, "Filter Archdiocese", true);
        } catch (error) {
            Notiflix.Notify.failure('Data Fetching Error!');
        }
}