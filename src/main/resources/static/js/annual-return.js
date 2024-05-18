let table
let data_id
let button = document.getElementById('myButton');
let title = document.getElementById('modelTitle');
let start = null;
let end = null;

let dateRange = document.getElementById('reportrange2');
let year = document.getElementById("year");
let form = document.getElementById('myForm');

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

function reload() {
    const button = document.querySelector('.dtsp-clearAll');
    button.click();
    setTimeout(rebuild, 150); // 1000 milliseconds = 1 second
}

function rebuild() {
    table.searchPanes.rebuildPane();
    start = null;
    end = null;
    cb(start, end)
}

function numberLock() {
    const quantityInputs = document.querySelectorAll('.number-type');

    quantityInputs.forEach(function (input) {
        // Listen for input event on each input field
        input.addEventListener('input', function (event) {
            let inputValue = event.target.value;
            inputValue = inputValue.replace(/\D/g, '');
            event.target.value = inputValue;
        });
    });
}

function cb(start, end) {

    if (!start || !end || start.format('YYYY-MM-DD') === 'Invalid date' || end.format('YYYY-MM-DD') === 'Invalid date') {
        dateRange.querySelector('span').innerHTML = 'None';
        table.column(54).search(null + ';' + null).draw();
    } else {
        dateRange.querySelector('span').innerHTML = start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY');
        table.column(54).search(start.format('YYYY-MM-DD') + ';' + end.format('YYYY-MM-DD')).draw();
    }
}


function dateFilter() {
    // Initialize date range picker
    $('#reportrange2').daterangepicker({
        startDate: start, endDate: end, ranges: {
            'None': [null, null],
            'Today': [moment(), moment()],
            'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
            'Last 7 Days': [moment().subtract(6, 'days'), moment()],
            'Last 30 Days': [moment().subtract(30, 'days'), moment()],
            'This Month': [moment().startOf('month'), moment().endOf('month')],
            'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
            'Last 3 Month': [moment().subtract(3, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
        }
    }, cb);

    cb(start, end);

}

$(document).ready(async function () {
// Initial content update
    updateModalContent(currentPage);

    numberLock()
    await parishFetching()


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

        ajax: {
            url: '/spcu-dims/api/v1/annual-return/show/table',
            method: 'POST',
            contentType: 'application/x-www-form-urlencoded',
            headers: {
                [csrfHeader]: csrfToken
            },
            data: function (d) {
                d.columns[56].search.value = false;
                return d;
            },
        },

        lengthMenu: [25, 50, 75, 100, 250], language: {
            infoFiltered: "",
        }, serverSide: true, processing: true, scrollX: true, layout: {
            top3: {
                searchPanes: {
                    threshold: 1, layout: 'columns-1', columns: [0, 3], dtOpts: {
                        select: {
                            style: 'multi'
                        }, scrollY: '100px'
                    }
                },
            }, top2Start: 'pageLength', top2End: 'search', topStart: {
                buttons: [

                    {
                        extend: 'excel', filename: 'annual-return', title: 'Annual Returns', exportOptions: {
                            columns: ':not(.noExport):visible'
                        }
                    }, {
                        extend: 'pdf',
                        filename: 'annual-return',
                        title: 'Annual Returns',
                        orientation: 'landscape',
                        pageSize: 'LEGAL',
                        exportOptions: {
                            columns: ':not(.noExport):visible'
                        },
                        customize: function (doc) {
                            // Center content in the PDF
                            doc.defaultStyle.alignment = 'center';
                        }
                    }, {
                        extend: 'print', filename: 'annual-return', title: 'Annual Returns',

                        exportOptions: {
                            columns: ':not(.noExport):visible'
                        }
                    }]
            }, topEnd: {
                buttons: [{
                    extend: 'colvis',
                    collectionLayout: 'fixed columns',
                    popoverTitle: 'Column Show/Hide Control',
                    text: 'Show/Hide',
                    postfixButtons: ['colvisRestore'],
                    columnText: function (dt, idx, title) {
                        return idx + 1 + ': ' + title.replace(/\(Total : \)/g, '');
                    }
                }]
            }
        },

        columns: [{
            data: 'parishName',
        }, {
            data: 'year'
        }, {
            data: 'totalActualResident'
        }, {
            data: 'indigenous'
        }, {
            data: 'notIndigenous'
        }, {
            data: 'bcTotal'
        }, {
            data: 'nonChristian'
        }, {
            data: 'nonCatholicChristian'
        }, {
            data: 'ncTotal'
        }, {
            data: 'catechumen'
        }, {
            data: 'chapel'
        }, {
            data: 'visitedPlace'
        }, {
            data: 'male'
        }, {
            data: 'female'
        }, {
            data: 'paid'
        }, {
            data: 'voluntary'
        }, {
            data: 'homeMissioner'
        }, {
            data: 'major'
        }, {
            data: 'minor'
        }, {
            data: 'rlMale'
        }, {
            data: 'rlFemale'
        }, {
            data: 'above8Year'
        }, {
            data: 'upTo7Year'
        }, {
            data: 'betweenCatholic'
        }, {
            data: 'withNonCatholicChristian'
        }, {
            data: 'withNonChristian'
        }, {
            data: 'priest'
        }, {
            data: 'religious'
        }, {
            data: 'catholic'
        }, {
            data: 'seminary'
        }, {
            data: 'novitiate'
        }, {
            data: 'catechist'
        }, {
            data: 'trainingForHomeMissioner'
        }, {
            data: 'boardingForPoor'
        }, {
            data: 'orphan'
        }, {
            data: 'poor'
        }, {
            data: 'religiousProfession'
        }, {
            data: 'graduated'
        }, {
            data: 'basic'
        }, {
            data: 'middle'
        }, {
            data: 'high'
        }, {
            data: 'university'
        }, {
            data: 'retreats'
        }, {
            data: 'recollections'
        }, {
            data: 'seminar'
        }, {
            data: 'other'
        }, {
            data: 'legionOfMary'
        }, {
            data: 'stVincentDePaul'
        }, {
            data: 'parishElder'
        }, {
            data: 'youthGroup'
        }, {
            data: 'motherGuide'
        }, {
            data: 'caOther'
        }, {
            data: 'project'
        }, {
            data: 'matrimonialCases'
        }, {
            data: 'createdDate'
        }, {
            data: 'createdUser'
        }, {
            data: 'isDelete', className: 'noExport', ordering: false, render: function (data, type, row) {
                const rowDataJson = JSON.stringify(row).replace(/"/g, '&quot;');
                return `
            <button type="button" class="btn btn-outline-warning p-0" data-bs-toggle="modal" data-bs-target="#model"
                onclick="changeEdit('${rowDataJson}')" style="border: none">
                <i class="bi bi-pencil-square mx-1" style="font-size: 1.1rem"></i>
            </button>
            <button type="button" onclick="showConfirm(${row.id})" class="btn btn-outline-danger p-0" style="border: none">
                <i class="bi bi-trash3 mx-1" style="font-size: 1.1rem"></i>
            </button>`;
            }

        }],
    });
    dateFilter();
    document
        .querySelector('div.dtsp-verticalPanes')
        .appendChild(table.searchPanes.container().get(0));
});

async function parishFetching() {
    try {
        const data = await fetchData('spcu-dims/api/v1/parish/show/all');
        await populateSelect(parishSelect, data, "Select Related Archdiocese", false);
    } catch (error) {
        Notiflix.Notify.failure('Data Fetching Error!');

    }
}

function changeAdd() {

    button.innerHTML = 'Add'
    title.textContent = 'Add Annual Return'
    button.setAttribute('onclick', 'adding()');
    return false;
}


async function changeEdit(rowDataJson) {

    console.log(rowDataJson);

    button.innerHTML = 'Edit';
    title.textContent = 'Edit Annual Return';
    button.setAttribute('onclick', 'editing()');
    return false;

}

async function showConfirm(id) {
    await deleteConfirm('/spcu-dims/api/v1/annual/delete/' + encodeURIComponent(id), reload)


}

function adding() {
    const jsonObject = {};
    const formData = new FormData(form);
    let showMessage = false; // Flag to track if the message has been shown

    formData.forEach((value, key) => {
        if (!value) { // Check if the value is empty or null
            value = 'default';
            if (!showMessage) {
                Notiflix.Notify.failure('Please Enter All Data'); // Show notification message
                showMessage = true; // Set the flag to true to avoid showing the message again
            }
        }
        jsonObject[key] = value;
    });

    const jsonString = JSON.stringify(jsonObject);
    console.log(jsonString);
}

function editing(rowDataJson) {

}
