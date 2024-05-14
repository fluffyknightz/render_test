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

$(document).ready(function () {

    numberLock()
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

        ajax: {
            url: '/spcu-dims/api/v1/annual-return/show/table',
            method: 'POST',
            contentType: 'application/x-www-form-urlencoded',
            headers: {
                [csrfHeader]: csrfToken
            },
            data: function (d) {
                d.columns[53].search.value = false;
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
                        extend: 'excel', filename: 'beneficiary-list', title: 'Beneficiary Lists', exportOptions: {
                            columns: ':not(.noExport):visible'
                        }
                    }, {
                        extend: 'pdf',
                        filename: 'beneficiary-list',
                        title: 'Beneficiary Lists',
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
                        extend: 'print', filename: 'beneficiary-list', title: 'Beneficiary Lists',

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
            data: 'parish.name',
        }, {
            data: 'year'
        }, {
            data: 'totalActualResident'
        }, {
            data: 'baptizedCatholic.indigenous'
        }, {
            data: 'baptizedCatholic.notIndigenous'
        }, {
            data: null, render: function (data, type, row) {
                return row.baptizedCatholic.notIndigenous + row.baptizedCatholic.notIndigenous
            }
        }, {
            data: 'nonCatholic.nonChristian'
        }, {
            data: 'nonCatholic.nonCatholicChristian'
        }, {
            data: null, render: function (data, type, row) {
                return row.nonCatholic.nonChristian + row.nonCatholic.nonCatholicChristian
            }
        }, {
            data: 'catechumen'
        }, {
            data: 'chapel'
        }, {
            data: 'visitedPlace'
        }, {
            data: 'priest.male'
        }, {
            data: 'priest.female'
        }, {
            data: 'catechist.paid'
        }, {
            data: 'catechist.voluntary'
        }, {
            data: 'catechist.homeMissioner'
        }, {
            data: 'seminarian.major'
        }, {
            data: 'seminarian.minor'
        }, {
            data: 'religiousLife.male'
        }, {
            data: 'religiousLife.female'
        }, {
            data: 'baptism.above8Year'
        }, {
            data: 'baptism.upTo7Year'
        }, {
            data: 'marriage.betweenCatholic'
        }, {
            data: 'marriage.withNonCatholicChristian'
        }, {
            data: 'marriage.withNonChristian'
        }, {
            data: 'death.priest'
        }, {
            data: 'death.religious'
        }, {
            data: 'death.catholic'
        }, {
            data: 'institute.seminary'
        }, {
            data: 'institute.novitiate'
        }, {
            data: 'institute.catechist'
        }, {
            data: 'institute.trainingForHomeMissioner'
        }, {
            data: 'institute.boardingForPoor'
        }, {
            data: 'institute.orphan'
        }, {
            data: 'institute.poor'
        }, {
            data: 'priestlyOrdination.religiousProfession'
        }, {
            data: 'priestlyOrdination.graduated'
        }, {
            data: 'education.basic'
        }, {
            data: 'education.middle'
        }, {
            data: 'education.high'
        }, {
            data: 'education.university'
        }, {
            data: 'religiousActivity.retreats'
        }, {
            data: 'religiousActivity.recollections'
        }, {
            data: 'religiousActivity.seminar'
        }, {
            data: 'religiousActivity.other'
        }, {
            data: 'catholicAction.legionOfMary'
        }, {
            data: 'catholicAction.stVincentDePaul'
        }, {
            data: 'catholicAction.parishElder'
        }, {
            data: 'catholicAction.youthGroup'
        }, {
            data: 'catholicAction.motherGuide'
        }, {
            data: 'catholicAction.other'
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
                return '<button type="button" class="btn btn-outline-warning p-0" data-bs-toggle="modal" data-bs-target="#model" ' + 'onclick="changeEdit()" style="border: none">\n' + '<i class="bi bi-pencil-square mx-1" style="font-size: 1.1rem"></i>\n' + '</button>\n' + '<button type="button" onclick="showConfirm(' + row.id + ')" class="btn btn-outline-danger p-0" style="border: none">\n' + '<i class="bi bi-trash3 mx-1" style="font-size: 1.1rem"></i>\n' + '</button>';
            }

        }],
    });
    document
        .querySelector('div.dtsp-verticalPanes')
        .appendChild(table.searchPanes.container().get(0));
});
