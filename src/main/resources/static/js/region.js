$(document).ready( function () {
    $('#myTable').DataTable({
        dom: 'flrtip'
    });
} );

Notiflix.Confirm.init({
    titleColor: '#4d56ec',
    okButtonColor: '#f8f8f8',
    okButtonBackground: '#f32222',
});

Notiflix.Notify.init({
    position: 'center-top',
});

function showConfirm(){
    Notiflix.Confirm.show(
        'Please Confirm',
        'Are you sure!! you want to delete?',
        'Yes',
        'No',
        function okCb() {
            // blah blah
            Notiflix.Notify.success('Delete successful.');
        },
        function cancelCb() {
            // blah blah
        },
        {
            width: '320px',
            borderRadius: '8px',
        },
    );
}