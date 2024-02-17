let table
let data_id
let button = document.getElementById('myButton');
let title = document.getElementById('modelTitle');
let input = document.getElementById('zone');
let inputValue

$(document).ready(function () {
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
            data: 'archdiocese.name'
        }, {
            data: 'isDelete',
            render: function (data, type, row) {
                return '<button type="button" class="btn btn-outline-warning p-0" data-bs-toggle="modal" ' + 'data-bs-target="#model" onclick="changeEdit(\'' + row.name + '\', ' + row.id + ')" style="border: none">\n' + '<i class="bi bi-pencil-square mx-1" style="font-size: 1.1rem"></i>\n' + '</button>\n' +
                       '<button type="button" onclick="showConfirm(' + row.id + ')" class="btn btn-outline-danger p-0" style="border: none">\n' + '<i class="bi bi-trash3 mx-1" style="font-size: 1.1rem"></i>\n' + '</button>';
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
        fetch('/spcu-dims/api/v1/zone/delete/' + id, {
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