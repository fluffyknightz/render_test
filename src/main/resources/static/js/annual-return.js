let table
let data_id
let button = document.getElementById('myButton');
let title = document.getElementById('modelTitle');
let input = document.getElementById('annual-return');
let year = document.getElementById("year");

// Pagination logic
let currentPage = 1;
let previousPage = 0;
let totalPages = 3;
let modalContent = document.querySelectorAll('.modal-body .page-content');

// Function to update modal content
function updateModalContent(page) {
    // Hide all content sections
    modalContent.forEach(function (content) {
        content.style.display = "none";
    });
    // Show the content section corresponding to the current page
    document.getElementById('content' + page).style.display = "block";

    switch (previousPage) {
        case 0:
            break
        default :
            document.getElementById('page' + previousPage).classList.remove('active');
            document.getElementById('page' + currentPage).classList.add('active');
    }


}
$(document).ready(function () {



    // Initial content update
    updateModalContent(currentPage);

    // Next button click event
    document.getElementById('next').addEventListener('click', function () {
        if (currentPage < totalPages) {
            previousPage = currentPage;
            currentPage++;

            updateModalContent(currentPage);
        }
    });

    // Previous button click event
    document.getElementById('prev').addEventListener('click', function () {
        if (currentPage > 1) {
            previousPage = currentPage;
            currentPage--;
            updateModalContent(currentPage);
        }
    });

    // Pagination button click events
    document.getElementById('page1').addEventListener('click', function () {
        previousPage = currentPage;
        currentPage = 1;
        updateModalContent(currentPage);

    });

    document.getElementById('page2').addEventListener('click', function () {
        previousPage = currentPage;
        currentPage = 2;
        updateModalContent(currentPage);
    });

    document.getElementById('page3').addEventListener('click', function () {
        previousPage = currentPage;
        currentPage = 3;
        updateModalContent(currentPage);
    });


    year.value = new Date().getFullYear();


    table = new DataTable('#myTable', {
        lengthMenu: [25, 50, 75, 100, 250],
        language: {
            infoFiltered: "",
        },
        scrollX: true,
    })


})
