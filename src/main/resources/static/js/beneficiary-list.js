let table
let data_id


let activityForm = document.querySelector('.other-activity');
let activity = document.getElementById('activity');

let button = document.getElementById('myButton');
let title = document.getElementById('modelTitle');


let activitySelect = document.getElementById('activitySelect');
let archdioceseSelect = document.getElementById('archdioceseSelect');
let dioceseSelect = document.getElementById('dioceseSelect');
let zoneSelect = document.getElementById('zoneSelect');
let parishSelect = document.getElementById('parishSelect');
let congregationSelect = document.getElementById('congregationSelect');

let inputBeneficiariesHousehold = document.getElementById('beneficiaries-household');
let inputBeneficiariesMale = document.getElementById('beneficiaries-male');
let inputBeneficiariesFemale = document.getElementById('beneficiaries-female');
let inputDisabilitiesMale = document.getElementById('disabilities-male');
let inputDisabilitiesFemale = document.getElementById('disabilities-female');
let inputVulnerabilitiesUnder18Male = document.getElementById('vulnerabilities-under18-male');
let inputVulnerabilitiesUnder18Female = document.getElementById('vulnerabilities-under18-female');
let inputVulnerabilitiesOver60Male = document.getElementById('vulnerabilities-over60-male');
let inputVulnerabilitiesOver60Female = document.getElementById('vulnerabilities-over60-female');

let dateRange = document.getElementById('reportrange');
let start = null;
let end = null;

let bHousehold;
let bMale;
let bFemale;
let dMale;
let dFemale;
let vU18Male;
let vU18Female;
let vO60Male;
let vO60Female;

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
        table.column(14).search(null + ';' + null).draw();
    } else {
        dateRange.querySelector('span').innerHTML = start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY');
        table.column(14).search(start.format('YYYY-MM-DD') + ';' + end.format('YYYY-MM-DD')).draw();
    }
}


function dateFilter() {
    // Initialize date range picker
    $('#reportrange').daterangepicker({
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

    await activityFetch();
    await archdioceseFetch();
    await congregationFetch();
    numberLock();


    function calculateTotal() {
        bHousehold = table
            .data()
            .reduce((acc, curr) => acc + curr.beneficiaryHousehold, 0);

        bMale = table
            .data()
            .reduce((acc, curr) => acc + curr.beneficiaryMale, 0);

        bFemale = table
            .data()
            .reduce((acc, curr) => acc + curr.beneficiaryFemale, 0);

        dMale = table
            .data()
            .reduce((acc, curr) => acc + curr.disabilityMale, 0);

        dFemale = table
            .data()
            .reduce((acc, curr) => acc + curr.disabilityFemale, 0);

        vU18Male = table
            .data()
            .reduce((acc, curr) => acc + curr.under18Male, 0);

        vU18Female = table
            .data()
            .reduce((acc, curr) => acc + curr.under18Female, 0);

        vO60Male = table
            .data()
            .reduce((acc, curr) => acc + curr.over60Male, 0);

        vO60Female = table
            .data()
            .reduce((acc, curr) => acc + curr.over60Female, 0);
    }


    table = new DataTable('#myTable', {

        ajax: {
            url: '/spcu-dims/api/v1/beneficiary-lists/show/table',
            method: 'POST',
            contentType: 'application/x-www-form-urlencoded',
            headers: {
                [csrfHeader]: csrfToken
            },
            data: function (d) {
                d.columns[16].search.value = false;
                return d;
            },
        },
        columnDefs: [
            {
                searchPanes: {
                    show: true,
                },
                targets: [0, 3, 4]
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
                    columns: [0, 3, 4],
                    dtOpts: {
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
            data: 'activityName',
        }, {
            data: 'zoneName'
        }, {
            data: 'dioceseName'
        }, {
            data: 'parishName'
        }, {
            data: 'congregation'
        }, {
            data: 'beneficiaryHousehold'
        }, {
            data: 'beneficiaryMale'
        }, {
            data: 'beneficiaryFemale'
        }, {
            data: 'disabilityMale'
        }, {
            data: 'disabilityFemale'
        }, {
            data: 'under18Male'
        }, {
            data: 'under18Female'
        }, {
            data: 'over60Male'
        }, {
            data: 'over60Female'
        }, {
            data: 'createdDate'
        }, {
            data: 'createdUser'
        }, {
            data: 'isDelete', className: 'noExport', ordering: false, render: function (data, type, row) {
                return '<button type="button" class="btn btn-outline-warning p-0" data-bs-toggle="modal" data-bs-target="#model" ' + 'onclick="changeEdit(' + row.id + ', \'' + row.activityName.replace(/'/g, "\\'") + '\', \'' + row.parishName.replace(/'/g, "\\'") + '\', \'' + row.congregation.replace(/'/g, "\\'") + '\', ' + row.beneficiaryHousehold + ', ' + row.beneficiaryMale + ', ' + row.beneficiaryFemale + ', ' + row.disabilityMale + ', ' + row.disabilityFemale + ', ' + row.under18Male + ', ' + row.under18Female + ', ' + row.over60Male + ', ' + row.over60Female + ', \'' + row.archdioceseName.replace(/'/g, "\\'") + '\', \'' + row.dioceseName.replace(/'/g, "\\'") + '\', \'' + row.zoneName.replace(/'/g, "\\'") + '\')" style="border: none">\n' + '<i class="bi bi-pencil-square mx-1" style="font-size: 1.1rem"></i>\n' + '</button>\n' +
                    '<button type="button" onclick="showConfirm(' + row.id + ')" class="btn btn-outline-danger p-0" style="border: none">\n' + '<i class="bi bi-trash3 mx-1" style="font-size: 1.1rem"></i>\n' + '</button>';
            }

        }],

        drawCallback: function (settings) {
            // Recalculate total combined household count
            calculateTotal();
            // Display total combined household count in HTML
            document.querySelectorAll('.bHousehold').forEach(element => {
                element.innerText = bHousehold;
            });

            document.querySelectorAll('.bMale').forEach(element => {
                element.innerText = bMale;
            });

            document.querySelectorAll('.bFemale').forEach(element => {
                element.innerText = bFemale;
            });

            document.querySelectorAll('.dMale').forEach(element => {
                element.innerText = dMale;
            });

            document.querySelectorAll('.dFemale').forEach(element => {
                element.innerText = dFemale;
            });

            document.querySelectorAll('.vU18Male').forEach(element => {
                element.innerText = vU18Male;
            });

            document.querySelectorAll('.vU18Female').forEach(element => {
                element.innerText = vU18Female;
            });

            document.querySelectorAll('.vO60Male').forEach(element => {
                element.innerText = vO60Male;
            });

            document.querySelectorAll('.vO60Female').forEach(element => {
                element.innerText = vO60Female;
            });


        },


    });

    dateFilter();

    document
        .querySelector('div.dtsp-verticalPanes')
        .appendChild(table.searchPanes.container().get(0));
});

activitySelect.addEventListener('change', async function () {
    console.log('reached')
    if (activitySelect.value === 'Other') {
        activityForm.style.display = 'block';
    } else {
        activityForm.style.display = 'none';
    }
})

archdioceseSelect.addEventListener('change', async function () {
    await dioceseSpecificFetch(archdioceseSelect.value)
})

dioceseSelect.addEventListener('change', async function () {
    await zoneSpecificFetch(dioceseSelect.value)
})

zoneSelect.addEventListener('change', async function () {
    await parishSpecificFetch(zoneSelect.value)
})


async function activityFetch() {
    try {
        const data = await fetchData('spcu-dims/api/v1/activity/show/all');
        await populateSelect(activitySelect, data, "Select Related Activity", false);
        const option = document.createElement('option');
        option.value = 'Other';
        option.textContent = 'Other';
        activitySelect.appendChild(option);

    } catch (error) {
        Notiflix.Notify.failure('Data Fetching Error!');
    }
}

async function archdioceseFetch() {
    try {
        const data = await fetchData('spcu-dims/api/v1/archdiocese/show/all');
        await populateSelect(archdioceseSelect, data, "Select Related Archdiocese", false);
    } catch (error) {
        Notiflix.Notify.failure('Data Fetching Error!');
    }
}

async function congregationFetch() {
    try {
        const data = await fetchData('spcu-dims/api/v1/congregation/show/all');
        await populateSelect(congregationSelect, data, "Select Related Congregation", false);
    } catch (error) {
        Notiflix.Notify.failure('Data Fetching Error!');
    }
}

async function dioceseSpecificFetch(archdiocese) {
    try {
        const data = await fetchData('spcu-dims/api/v1/diocese/show', `archdiocese=${encodeURIComponent(archdiocese)}`);
        await populateSelect(dioceseSelect, data, "Select Related Diocese", false);
        zoneSelect.options.length = 1
        zoneSelect.selectedIndex = 0;
        parishSelect.options.length = 1
        parishSelect.selectedIndex = 0;

    } catch (error) {
        Notiflix.Notify.failure('Data Fetching Error!');
    }
}

async function zoneSpecificFetch(diocese) {
    try {
        const data = await fetchData('spcu-dims/api/v1/zone/show', `diocese=${encodeURIComponent(diocese)}`);
        await populateSelect(zoneSelect, data, "Select Related Zone", false);
        parishSelect.options.length = 1;
        parishSelect.selectedIndex = 0;

    } catch (error) {
        Notiflix.Notify.failure('Data Fetching Error!');
    }
}

async function parishSpecificFetch(zone) {
    try {
        const data = await fetchData('spcu-dims/api/v1/parish/show', `zone=${encodeURIComponent(zone)}`);
        await populateSelect(parishSelect, data, "Select Related Parish", false);
    } catch (error) {
        Notiflix.Notify.failure('Data Fetching Error!');
    }
}


function changeAdd() {
    activityForm.style.display = 'none';
    activitySelect.selectedIndex = 0;
    parishSelect.selectedIndex = 0;
    congregationSelect.selectedIndex = 0;
    activity.value = null
    activitySelect.selectedIndex = 0;
    archdioceseSelect.selectedIndex = 0;
    dioceseSelect.selectedIndex = 0;
    zoneSelect.selectedIndex = 0;
    parishSelect.selectedIndex = 0;
    inputBeneficiariesHousehold.value = null;
    inputBeneficiariesMale.value = null;
    inputBeneficiariesFemale.value = null;
    inputDisabilitiesMale.value = null;
    inputDisabilitiesFemale.value = null;
    inputVulnerabilitiesUnder18Male.value = null;
    inputVulnerabilitiesUnder18Female.value = null;
    inputVulnerabilitiesOver60Male.value = null;
    inputVulnerabilitiesOver60Female.value = null;
    button.innerHTML = 'Add'
    title.textContent = 'Add Beneficiary List'
    button.setAttribute('onclick', 'adding()');
    return false;
}

async function changeEdit(id, activity, parish, congregation, houseHold, male, female, disabilityMale, disabilityFemale, under18Male, under18Female, over60Male, over60Female, archdiocese, diocese, zone) {
    activityForm.style.display = 'none';
    await dioceseSpecificFetch(archdiocese)
    await zoneSpecificFetch(diocese)
    await parishSpecificFetch(zone)


    button.innerHTML = 'Edit';
    title.textContent = 'Edit Beneficiary List';
    button.setAttribute('onclick', 'editing()');

    data_id = id;
    activitySelect.value = activity;
    archdioceseSelect.value = archdiocese;
    dioceseSelect.value = diocese;
    zoneSelect.value = zone;
    parishSelect.value = parish;
    congregationSelect.value = congregation;
    inputBeneficiariesHousehold.value = houseHold;
    inputBeneficiariesMale.value = male;
    inputBeneficiariesFemale.value = female;

    inputDisabilitiesMale.value = disabilityMale;
    inputDisabilitiesFemale.value = disabilityFemale;

    inputVulnerabilitiesUnder18Male.value = under18Male;
    inputVulnerabilitiesUnder18Female.value = under18Female;
    inputVulnerabilitiesOver60Male.value = over60Male;
    inputVulnerabilitiesOver60Female.value = over60Female;
    return false;


}

async function showConfirm(id) {
    await deleteConfirm('/spcu-dims/api/v1/beneficiary-lists/delete/' + encodeURIComponent(id))


}

async function editing() {
    if (checkRequired()) {
        Notiflix.Notify.failure("Please enter All Data!");
    } else if (activitySelect.value === 'Other' && (activity.value === undefined || activity.value === null || activity.value.trim() === "")) {
        Notiflix.Notify.failure("Please enter All Data!");
    } else {

        let requestData = {
            id: data_id,
            activityName: activitySelect.value === 'Other' ? activity.value : activitySelect.value,
            parishName: parishSelect.value,
            congregation: congregationSelect.value,
            beneficiaryHousehold: inputBeneficiariesHousehold.value,
            beneficiaryMale: inputBeneficiariesMale.value,
            beneficiaryFemale: inputBeneficiariesFemale.value,
            disabilityMale: inputDisabilitiesMale.value,
            disabilityFemale: inputDisabilitiesFemale.value,
            vulnerabilityUnder18Male: inputVulnerabilitiesUnder18Male.value,
            vulnerabilityUnder18Female: inputVulnerabilitiesUnder18Female.value,
            vulnerabilityOver60Male: inputVulnerabilitiesOver60Male.value,
            vulnerabilityOver60Female: inputVulnerabilitiesOver60Female.value
        }

        const url = '/spcu-dims/api/v1/beneficiary-lists/update';


        if (activitySelect.value === 'Other') {
            await performOperation('/spcu-dims/api/v1/activity/create?name=' + encodeURIComponent(activity.value), 'POST', 'Activity Create successful.');
            await activityFetch();
        }
        await performOperation(url, 'PATCH', 'Edit successful.', requestData);


    }
}


async function adding() {
    if (checkRequired()) {
        Notiflix.Notify.failure("Please enter All Data!");
    } else if (activitySelect.value === 'Other' && (activity.value === undefined || activity.value === null || activity.value.trim() === "")) {
        console.log(activitySelect.value)

        Notiflix.Notify.failure("Please enter All Data!");
    } else {

        let requestData = {
            activityName: activitySelect.value === 'Other' ? activity.value : activitySelect.value,
            parishName: parishSelect.value,
            congregation: congregationSelect.value,
            beneficiaryHousehold: inputBeneficiariesHousehold.value,
            beneficiaryMale: inputBeneficiariesMale.value,
            beneficiaryFemale: inputBeneficiariesFemale.value,
            disabilityMale: inputDisabilitiesMale.value,
            disabilityFemale: inputDisabilitiesFemale.value,
            vulnerabilityUnder18Male: inputVulnerabilitiesUnder18Male.value,
            vulnerabilityUnder18Female: inputVulnerabilitiesUnder18Female.value,
            vulnerabilityOver60Male: inputVulnerabilitiesOver60Male.value,
            vulnerabilityOver60Female: inputVulnerabilitiesOver60Female.value
        }
        const url = '/spcu-dims/api/v1/beneficiary-lists/create';

        if (activitySelect.value === 'Other') {
            await performOperation('/spcu-dims/api/v1/activity/create?name=' + encodeURIComponent(activity.value), 'POST', 'Activity create successful.');
            await activityFetch();
        }
        await performOperation(url, 'POST', 'Create successful.', requestData);
    }
}

function checkRequired() {
    return activitySelect.value === null || activitySelect.value.trim() === "" || parishSelect.value === null || parishSelect.value.trim() === "" || congregationSelect.value === null || congregationSelect.value.trim() === "" || inputBeneficiariesHousehold.value === null || inputBeneficiariesHousehold.value.trim() === "" || inputBeneficiariesMale.value === null || inputBeneficiariesMale.value.trim() === "" || inputBeneficiariesFemale.value === null || inputBeneficiariesFemale.value.trim() === "" || inputDisabilitiesMale.value === null || inputDisabilitiesMale.value.trim() === "" || inputDisabilitiesFemale.value === null || inputDisabilitiesFemale.value.trim() === "" || inputVulnerabilitiesUnder18Male.value === null || inputVulnerabilitiesUnder18Male.value.trim() === "" || inputVulnerabilitiesUnder18Female.value === null || inputVulnerabilitiesUnder18Female.value.trim() === "" || inputVulnerabilitiesOver60Male.value === null || inputVulnerabilitiesOver60Male.value.trim() === "" || inputVulnerabilitiesOver60Female.value === null || inputVulnerabilitiesOver60Female.value.trim() === "";
}
