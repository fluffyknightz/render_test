let table
let data_id
let button = document.getElementById('myButton');
let title = document.getElementById('modelTitle');
let input = document.getElementById('annual-return');


$(document).ready(function () {


    $(".yearpicker").yearpicker();

    table = new DataTable('#myTable', {
        lengthMenu: [25, 50, 75, 100, 250],
        language: {
            infoFiltered: "",
        },
        scrollX: true,
    })


})
