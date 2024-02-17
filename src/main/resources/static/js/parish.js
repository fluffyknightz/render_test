$(document).ready(async function () {

    table = $('#myTable').DataTable({
        dom: 'flrtip', language: {
            infoFiltered: null,
        },

        ajax: {

            url: 'spcu-dims/api/v1/parish/show/table',
            method: 'POST', contentType:
                'application/json',

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
                data: 'id', render: function (data, type, row, meta) {
                    // meta.row contains the row index
                    return meta.row + 1;
                }

            }, {
                data: 'name'
            }, {
                data: 'zone.archdiocese.diocese.name'
            },{
                data: 'zone.archdiocese.name'
            },{
                data: 'zone.name'
            },{
                data: 'township.district.region.name'
            },{
                data: 'township.district.name'
            },{
                data: 'township.name'
            },{
                data: 'isDelete',
                render: function (data, type, row) {
                    return '<button type="button" class="btn btn-outline-warning p-0" data-bs-toggle="modal" data-bs-target="#model"  style="border: none">\n' +
                        '<i class="bi bi-pencil-square mx-1" style="font-size: 1.1rem"></i>\n' +
                        '</button>\n' +
                        '<button type="button" onclick="showConfirm(' + row.id + ')" class="btn btn-outline-danger p-0" style="border: none">\n' +
                        '<i class="bi bi-trash3 mx-1" style="font-size: 1.1rem"></i>\n' +
                        '</button>';
                }

            },]
    });

});