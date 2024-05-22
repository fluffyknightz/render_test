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

let resident;
let indigenous;
let notIndigenous
let bcTotal
let nonChristian
let nonCatholicChristian
let ncTotal
let catechumen
let chapel
let visited
let malePriest
let femalePriest
let paid
let voluntary
let homeMissioner
let major
let minor
let male
let female
let above8Year
let upTo7Year
let withCatholic
let withNonCatholicChristian
let withNonChristian
let priest
let religious
let catholic
let seminary
let novitiate
let catechist
let training
let boarding
let poor
let orphan
let profession
let graduated
let basic
let middle
let high
let university
let retreats
let recollections
let seminar
let other
let mary
let vicent
let elder
let youth
let guide
let caOther
let project
let cases

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

    function calculateTotal() {
        resident = table
            .data()
            .reduce((acc, curr) => acc + curr.totalActualResident, 0);

        indigenous = table
            .data()
            .reduce((acc, curr) => acc + curr.indigenous, 0);

        notIndigenous = table
            .data()
            .reduce((acc, curr) => acc + curr.notIndigenous, 0);

        bcTotal = table
            .data()
            .reduce((acc, curr) => acc + curr.bcTotal, 0);

        nonChristian = table
            .data()
            .reduce((acc, curr) => acc + curr.nonChristian, 0);

        nonCatholicChristian = table
            .data()
            .reduce((acc, curr) => acc + curr.nonCatholicChristian, 0);

        ncTotal = table
            .data()
            .reduce((acc, curr) => acc + curr.ncTotal, 0);

        catechumen = table
            .data()
            .reduce((acc, curr) => acc + curr.catechumen, 0);

        chapel = table
            .data()
            .reduce((acc, curr) => acc + curr.chapel, 0);

        visited = table
            .data()
            .reduce((acc, curr) => acc + curr.visitedPlace, 0);

        chapel = table
            .data()
            .reduce((acc, curr) => acc + curr.chapel, 0);

        malePriest = table
            .data()
            .reduce((acc, curr) => acc + curr.malePriest, 0);

        femalePriest = table
            .data()
            .reduce((acc, curr) => acc + curr.femalePriest, 0);

        paid = table
            .data()
            .reduce((acc, curr) => acc + curr.paid, 0);

        voluntary = table
            .data()
            .reduce((acc, curr) => acc + curr.voluntary, 0);

        homeMissioner = table
            .data()
            .reduce((acc, curr) => acc + curr.homeMissioner, 0);

        major = table
            .data()
            .reduce((acc, curr) => acc + curr.major, 0);

        minor = table
            .data()
            .reduce((acc, curr) => acc + curr.minor, 0);

        male = table
            .data()
            .reduce((acc, curr) => acc + curr.rlMale, 0);

        female = table
            .data()
            .reduce((acc, curr) => acc + curr.rlFemale, 0);

        above8Year = table
            .data()
            .reduce((acc, curr) => acc + curr.above8Year, 0);

        upTo7Year = table
            .data()
            .reduce((acc, curr) => acc + curr.upTo7Year, 0);

        withCatholic = table
            .data()
            .reduce((acc, curr) => acc + curr.withCatholic, 0);

        withNonCatholicChristian = table
            .data()
            .reduce((acc, curr) => acc + curr.withNonCatholicChristian, 0);

        withNonChristian = table
            .data()
            .reduce((acc, curr) => acc + curr.withNonChristian, 0);

        priest = table
            .data()
            .reduce((acc, curr) => acc + curr.priest, 0);

        religious = table
            .data()
            .reduce((acc, curr) => acc + curr.religious, 0);

        catholic = table
            .data()
            .reduce((acc, curr) => acc + curr.catholic, 0);

        seminary = table
            .data()
            .reduce((acc, curr) => acc + curr.seminary, 0);

        novitiate = table
            .data()
            .reduce((acc, curr) => acc + curr.novitiate, 0);

        catechist = table
            .data()
            .reduce((acc, curr) => acc + curr.catechist, 0);

        training = table
            .data()
            .reduce((acc, curr) => acc + curr.trainingForHomeMissioner, 0);

        boarding = table
            .data()
            .reduce((acc, curr) => acc + curr.boardingForPoor, 0);

        poor = table
            .data()
            .reduce((acc, curr) => acc + curr.poor, 0);

        orphan = table
            .data()
            .reduce((acc, curr) => acc + curr.orphan, 0);

        profession = table
            .data()
            .reduce((acc, curr) => acc + curr.religiousProfession, 0);

        graduated = table
            .data()
            .reduce((acc, curr) => acc + curr.graduated, 0);

        basic = table
            .data()
            .reduce((acc, curr) => acc + curr.basic, 0);

        middle = table
            .data()
            .reduce((acc, curr) => acc + curr.middle, 0);

        high = table
            .data()
            .reduce((acc, curr) => acc + curr.high, 0);

        university = table
            .data()
            .reduce((acc, curr) => acc + curr.university, 0);

        retreats = table
            .data()
            .reduce((acc, curr) => acc + curr.retreats, 0);

        recollections = table
            .data()
            .reduce((acc, curr) => acc + curr.recollections, 0);

        seminar = table
            .data()
            .reduce((acc, curr) => acc + curr.seminar, 0);

        other = table
            .data()
            .reduce((acc, curr) => acc + curr.other, 0);

        mary = table
            .data()
            .reduce((acc, curr) => acc + curr.legionOfMary, 0);

        vicent = table
            .data()
            .reduce((acc, curr) => acc + curr.stVincentDePaul, 0);

        elder = table
            .data()
            .reduce((acc, curr) => acc + curr.parishElder, 0);

        youth = table
            .data()
            .reduce((acc, curr) => acc + curr.youthGroup, 0);

        guide = table
            .data()
            .reduce((acc, curr) => acc + curr.motherGuide, 0);

        caOther = table
            .data()
            .reduce((acc, curr) => acc + curr.caOther, 0);

        project = table
            .data()
            .reduce((acc, curr) => acc + curr.project, 0);

        cases = table
            .data()
            .reduce((acc, curr) => acc + curr.matrimonialCases, 0);


    }

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
        columnDefs: [
            {
                searchPanes: {
                    show: true
                },
                targets: [0, 1]
            }
        ],
        lengthMenu: [25, 50, 75, 100, 250],
        language: {
            infoFiltered: "",
        },
        serverSide: true,
        processing: true,
        scrollX: true,
        layout: {
            top3: {
                searchPanes: {
                    threshold: 1,
                    layout: 'columns-1',
                    viewTotal: true,
                    cascadePanes: true,
                    dtOpts: {
                        select: {
                            style: 'multi'
                        },
                        scrollY: '100px',

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
            data: 'parish',
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
            data: 'malePriest'
        }, {
            data: 'femalePriest'
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
                onclick="changeEdit(${rowDataJson})" style="border: none">
                <i class="bi bi-pencil-square mx-1" style="font-size: 1.1rem"></i>
            </button>
            <button type="button" onclick="showConfirm(${row.id})" class="btn btn-outline-danger p-0" style="border: none">
                <i class="bi bi-trash3 mx-1" style="font-size: 1.1rem"></i>
            </button>`;
            }

        }],

        drawCallback: function (settings) {
            // Recalculate total combined household count
            calculateTotal();

            // Update the HTML with the calculated totals
            document.querySelectorAll('.resident').forEach(element => element.innerText = resident);
            document.querySelectorAll('.indigenous').forEach(element => element.innerText = indigenous);
            document.querySelectorAll('.notIndigenous').forEach(element => element.innerText = notIndigenous);
            document.querySelectorAll('.bcTotal').forEach(element => element.innerText = bcTotal);
            document.querySelectorAll('.nonChristian').forEach(element => element.innerText = nonChristian);
            document.querySelectorAll('.nonCatholicChristian').forEach(element => element.innerText = nonCatholicChristian);
            document.querySelectorAll('.ncTotal').forEach(element => element.innerText = ncTotal);
            document.querySelectorAll('.catechumen').forEach(element => element.innerText = catechumen);
            document.querySelectorAll('.chapel').forEach(element => element.innerText = chapel);
            document.querySelectorAll('.visited').forEach(element => element.innerText = visited);
            document.querySelectorAll('.malePriest').forEach(element => element.innerText = malePriest);
            document.querySelectorAll('.femalePriest').forEach(element => element.innerText = femalePriest);
            document.querySelectorAll('.paid').forEach(element => element.innerText = paid);
            document.querySelectorAll('.voluntary').forEach(element => element.innerText = voluntary);
            document.querySelectorAll('.homeMissioner').forEach(element => element.innerText = homeMissioner);
            document.querySelectorAll('.major').forEach(element => element.innerText = major);
            document.querySelectorAll('.minor').forEach(element => element.innerText = minor);
            document.querySelectorAll('.male').forEach(element => element.innerText = male);
            document.querySelectorAll('.female').forEach(element => element.innerText = female);
            document.querySelectorAll('.above8Year').forEach(element => element.innerText = above8Year);
            document.querySelectorAll('.upTo7Year').forEach(element => element.innerText = upTo7Year);
            document.querySelectorAll('.withCatholic').forEach(element => element.innerText = withCatholic);
            document.querySelectorAll('.withNonCatholicChristian').forEach(element => element.innerText = withNonCatholicChristian);
            document.querySelectorAll('.withNonChristian').forEach(element => element.innerText = withNonChristian);
            document.querySelectorAll('.priest').forEach(element => element.innerText = priest);
            document.querySelectorAll('.religious').forEach(element => element.innerText = religious);
            document.querySelectorAll('.catholic').forEach(element => element.innerText = catholic);
            document.querySelectorAll('.seminary').forEach(element => element.innerText = seminary);
            document.querySelectorAll('.novitiate').forEach(element => element.innerText = novitiate);
            document.querySelectorAll('.catechist').forEach(element => element.innerText = catechist);
            document.querySelectorAll('.training').forEach(element => element.innerText = training);
            document.querySelectorAll('.boarding').forEach(element => element.innerText = boarding);
            document.querySelectorAll('.poor').forEach(element => element.innerText = poor);
            document.querySelectorAll('.orphan').forEach(element => element.innerText = orphan);
            document.querySelectorAll('.profession').forEach(element => element.innerText = profession);
            document.querySelectorAll('.graduated').forEach(element => element.innerText = graduated);
            document.querySelectorAll('.basic').forEach(element => element.innerText = basic);
            document.querySelectorAll('.middle').forEach(element => element.innerText = middle);
            document.querySelectorAll('.high').forEach(element => element.innerText = high);
            document.querySelectorAll('.university').forEach(element => element.innerText = university);
            document.querySelectorAll('.retreats').forEach(element => element.innerText = retreats);
            document.querySelectorAll('.recollections').forEach(element => element.innerText = recollections);
            document.querySelectorAll('.seminar').forEach(element => element.innerText = seminar);
            document.querySelectorAll('.other').forEach(element => element.innerText = other);
            document.querySelectorAll('.mary').forEach(element => element.innerText = mary);
            document.querySelectorAll('.vicent').forEach(element => element.innerText = vicent);
            document.querySelectorAll('.elder').forEach(element => element.innerText = elder);
            document.querySelectorAll('.youth').forEach(element => element.innerText = youth);
            document.querySelectorAll('.guide').forEach(element => element.innerText = guide);
            document.querySelectorAll('.caOther').forEach(element => element.innerText = caOther);
            document.querySelectorAll('.project').forEach(element => element.innerText = project);
            document.querySelectorAll('.cases').forEach(element => element.innerText = cases);
        }

    });
    dateFilter();
    document
        .querySelector('div.dtsp-verticalPanes')
        .appendChild(table.searchPanes.container().get(0));
});

async function parishFetching() {
    try {
        const data = await fetchData('spcu-dims/api/v1/parish/show/all');
        await populateSelect(parishSelect, data, "Select Related Parish", false);
    } catch (error) {
        Notiflix.Notify.failure('Data Fetching Error!');

    }
}

function changeAdd() {
    previousPage = currentPage;
    currentPage = 1;
    updateModalContent(1);

    form.reset();
    year.value = new Date().getFullYear();
    button.innerHTML = 'Add'
    title.textContent = 'Add Annual Return'
    button.setAttribute('onclick', 'adding()');
    return false;
}


async function changeEdit(rowDataJson) {

    previousPage = currentPage;
    currentPage = 1;
    updateModalContent(1);

    Object.keys(rowDataJson).forEach(key => {
        const input = form.elements[key];
        if (input) {
            input.value = rowDataJson[key];
        }
    });

    button.innerHTML = 'Edit';
    title.textContent = 'Edit Annual Return';
    button.setAttribute('onclick', `editing(${rowDataJson.id})`);
    return false;

}

async function showConfirm(id) {
    await deleteConfirm('/spcu-dims/api/v1/annual-return/delete/' + encodeURIComponent(id))


}

async function processData(form, id = null) {
    const jsonObject = {};
    const formData = new FormData(form);
    let hasEmptyValue = false; // Flag to track if there is any empty value

    if (id !== null) {
        jsonObject['id'] = id;
    }

    formData.forEach((value, key) => {
        if (!value) { // Check if the value is empty or null
            hasEmptyValue = true;
        }
        jsonObject[key] = value;
    });

    if (hasEmptyValue) {
        Notiflix.Notify.failure('Please Enter All Data'); // Show notification message
        return null; // Exit the function if any value is missing
    }

    return jsonObject;
}

async function adding() {
    const jsonObject = await processData(form);
    if (jsonObject !== null) {
        await performOperation('/spcu-dims/api/v1/annual-return/create', 'POST', 'Create successful.', jsonObject);
    }
}

async function editing(id) {
    const jsonObject = await processData(form, id);
    if (jsonObject !== null) {
        await performOperation('/spcu-dims/api/v1/annual-return/update', 'PATCH', 'Update successful.', jsonObject);
    }
}

