let table

document.getElementById('saveButton').addEventListener('click', function (event) {
    event.preventDefault()
    const name = document.getElementById('region');
    fetch('/spcu-dims/api/v1/region/create?name=' + encodeURIComponent(name.value), {
        method: 'POST',
        headers: {
            contentType: 'application/json',
            [csrfHeader]: csrfToken
        },
        body: null
    }).then(r => {
        if (r.status === 201) {
            $('#addModel').modal('hide');
            Notiflix.Notify.success('Create successful.');
            table.ajax.reload();
        } else {
            Notiflix.Notify.failure('Create Failed.');
        }
    })
})
$(document).ready(function () {

        table = $('#myTable').DataTable({
            dom: 'flrtip',
            language:{
                infoFiltered: null,
            },

            ajax: {

                url: 'spcu-dims/api/v1/region/show/table',
                method: 'POST',
                contentType: 'application/json',

                headers: {
                    [csrfHeader]: csrfToken
                },

                data: function (d) {
                    d.columns[2].search.value = false;
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

                },
                {
                    data: 'name'
                },
                {
                    data: 'isDelete',
                    render: function (data, type, row) {
                        return '<button type="button" class="btn btn-outline-warning p-0" data-bs-toggle="modal" ' +
                            'data-bs-target="#editModel" style="border: none">\n' +
                            '<i class="bi bi-pencil-square mx-1" style="font-size: 1.1rem"></i>\n' +
                            '</button>\n' +
                            '<button type="button" onclick="showConfirm(' + row.id + ')" class="btn btn-outline-danger p-0" style="border: none">\n' +
                            '<i class="bi bi-trash3 mx-1" style="font-size: 1.1rem"></i>\n' +
                            '</button>';
                    }
                },
            ]
        });

    }
);

Notiflix.Confirm.init({
    titleColor: '#4d56ec',
    okButtonColor: '#f8f8f8',
    okButtonBackground: '#f32222',
});

Notiflix.Notify.init({
    position: 'center-top',
});

function showConfirm(id) {
    Notiflix.Confirm.show(
        'Please Confirm',
        'Are you sure!! you want to delete?',
        'Yes',
        'No',
        function okCb() {
            console.log(typeof id)
            fetch('/spcu-dims/api/v1/region/delete/' + id, {
                method: 'PATCH',
                headers: {
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
                    }
                )

        },
        {
            width: '320px',
            borderRadius: '8px',
        },
    );
}