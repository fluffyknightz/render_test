let table
let data_id

let button = document.getElementById('myButton');
let title = document.getElementById('modelTitle');

let activityFilter = document.getElementById('filterActivity');
let parishFilter = document.getElementById('filterParish');

let activitySelect = document.getElementById('activitySelect');
let archdioceseSelect = document.getElementById('archdioceseSelect');
let dioceseSelect = document.getElementById('dioceseSelect');
let zoneSelect = document.getElementById('zoneSelect');
let parishSelect = document.getElementById('parishSelect');

let inputBeneficiariesHousehold = document.getElementById('beneficiaries-household');
let inputBeneficiariesMale = document.getElementById('beneficiaries-male');
let inputBeneficiariesFemale = document.getElementById('beneficiaries-female');
let inputDisabilitiesMale = document.getElementById('disabilities-male');
let inputDisabilitiesFemale = document.getElementById('disabilities-female');
let inputVulnerabilitiesUnder18Male = document.getElementById('vulnerabilities-under18-male');
let inputVulnerabilitiesUnder18Female = document.getElementById('vulnerabilities-under18-female');
let inputVulnerabilitiesOver60Male = document.getElementById('vulnerabilities-over60-male');
let inputVulnerabilitiesOver60Female = document.getElementById('vulnerabilities-over60-female');
let householdtotal = document.getElementById('household-count')
let totalCombinedHousehold;
$(document).ready(async function () {

    await activityFetch();
    await archdioceseFetch();
    await parishFetch();
    function calculateTotalCombinedHousehold() {
            totalCombinedHousehold = table
                .column('beneficiaryHousehold')
                .data()
                .reduce((acc, curr) => acc + curr, 0);


    }


    table = new DataTable('#myTable', {

        layout: {
            top2Start: 'pageLength',
            top2End: 'search',
            topStart: {
                buttons: [

                    {
                        extend: 'excel',
                        filename: 'beneficiary-list',
                        title: 'Beneficiary Lists',
                        exportOptions: {
                            columns: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]
                        }
                    },
                    {
                        extend: 'pdf',
                        filename: 'beneficiary-list',
                        title: 'Beneficiary Lists',
                        orientation: 'landscape',
                        pageSize: 'LEGAL',
                        exportOptions: {
                            columns: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]
                        },
                        customize: function (doc) {
                            doc.content[1].margin = [100, 20, 0, 0] //left, top, right, bottom
                        }
                    },
                    {
                        extend: 'print',
                        filename: 'beneficiary-list',
                        title: 'Beneficiary Lists',
                        exportOptions: {
                            columns: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]
                        }
                    }
                ]
            },
            topEnd: {
                buttons: [
                    {
                        extend: 'colvis',
                        postfixButtons: ['colvisRestore']
                    }
                ]
            }
        },
        lengthMenu: [25, 50, 75, 100, 250, {label: 'All', value: -1}],
        language: {
            infoFiltered: "",
        },
        ajax: {
            url: 'spcu-dims/api/v1/beneficiary-lists/show/table',
            method: 'POST',
            contentType: 'application/json',
            headers: {
                [csrfHeader]: csrfToken
            },
            data: function (d) {
                d.columns[13].search.value = false;
                return JSON.stringify(d);
            },
        },
        processing: true,
        serverSide: true,
        scrollX: true,
        columns: [
            {
                data: 'activityName',
            }, {
                data: 'zoneName'
            }, {
                data: 'dioceseName'
            }, {
                data: 'parishName'
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
                data: 'isDelete',
                ordering: false,
                render: function (data, type, row) {
                    return '<button type="button" class="btn btn-outline-warning p-0" data-bs-toggle="modal" data-bs-target="#model" ' +
                        'onclick="changeEdit(' + row.id + ', \'' + row.activityName.replace(/'/g, "\\'") + '\', \'' + row.parishName.replace(/'/g, "\\'") +
                        '\', ' + row.beneficiaryHousehold + ', ' + row.beneficiaryMale + ', ' + row.beneficiaryFemale + ', ' + row.disabilityMale +
                        ', ' + row.disabilityFemale + ', ' + row.under18Male + ', ' + row.under18Female + ', ' + row.over60Male +
                        ', ' + row.over60Female + ', \'' + row.archdioceseName.replace(/'/g, "\\'") + '\', \'' + row.dioceseName.replace(/'/g, "\\'") + '\', \'' + row.zoneName.replace(/'/g, "\\'") + '\')" style="border: none">\n' +
                        '<i class="bi bi-pencil-square mx-1" style="font-size: 1.1rem"></i>\n' +
                        '</button>\n' +
                        '<button type="button" onclick="showConfirm(' + row.id + ')" class="btn btn-outline-danger p-0" style="border: none">\n' +
                        '<i class="bi bi-trash3 mx-1" style="font-size: 1.1rem"></i>\n' +
                        '</button>';
                }
            }, {
                data: 'archdioceseName',
                visible: false,
                ordering: false,
            }],

        drawCallback: function (settings) {
            // Recalculate total combined household count
            calculateTotalCombinedHousehold();
            // Display total combined household count in HTML
            householdtotal.innerText = totalCombinedHousehold
        },
    });

});

activityFilter.addEventListener('change', function () {
    table.column(0).search(activityFilter.value).draw();
})

parishFilter.addEventListener('change', function () {
    table.column(3).search(parishFilter.value).draw();
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
        await populateSelect(activityFilter, data, "Filter Activity", true);
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

async function parishFetch() {
    try {
        const data = await fetchData('spcu-dims/api/v1/parish/show/all');
        await populateSelect(parishFilter, data, "Filter Parish", true);
    } catch (error) {
        Notiflix.Notify.failure('Data Fetching Error!');
    }
}

function changeAdd() {

    activitySelect.selectedIndex = 0;
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

async function changeEdit(id, activity, parish, houseHold, male, female, disabilityMale, disabilityFemale, under18Male, under18Female, over60Male, over60Female, archdiocese, diocese, zone) {
    console.log('reached')
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

function showConfirm(id) {
    deleteConfirm('/spcu-dims/api/v1/beneficiary-lists/delete/' + encodeURIComponent(id))
}

function editing() {
    if (checkRequired()) {
        Notiflix.Notify.failure("Please enter All Data!");
    } else {

        let requestData = {
            id: data_id,
            activityName: activitySelect.value,
            parishName: parishSelect.value,
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
        performOperation(url, 'PATCH', 'Edit successful.', requestData);
    }
}


function adding() {
    if (checkRequired()) {
        Notiflix.Notify.failure("Please enter All Data!");
    } else {

        let requestData = {
            activityName: activitySelect.value,
            parishName: parishSelect.value,
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
        performOperation(url, 'POST', 'create successful.', requestData);
    }
}

function checkRequired() {
    return activitySelect.value === undefined || activitySelect.value === null || activitySelect.value.trim() === ""
        || parishSelect.value === undefined || parishSelect.value === null || parishSelect.value.trim() === ""
        || inputBeneficiariesHousehold.value === undefined || inputBeneficiariesHousehold.value === null || inputBeneficiariesHousehold.value.trim() === ""
        || inputBeneficiariesMale.value === undefined || inputBeneficiariesMale.value === null || inputBeneficiariesMale.value.trim() === ""
        || inputBeneficiariesFemale.value === undefined || inputBeneficiariesFemale.value === null || inputBeneficiariesFemale.value.trim() === ""
        || inputDisabilitiesMale.value === undefined || inputDisabilitiesMale.value === null || inputDisabilitiesMale.value.trim() === ""
        || inputDisabilitiesFemale.value === undefined || inputDisabilitiesFemale.value === null || inputDisabilitiesFemale.value.trim() === ""
        || inputVulnerabilitiesUnder18Male.value === undefined || inputVulnerabilitiesUnder18Male.value === null || inputVulnerabilitiesUnder18Male.value.trim() === ""
        || inputVulnerabilitiesUnder18Female.value === undefined || inputVulnerabilitiesUnder18Female.value === null || inputVulnerabilitiesUnder18Female.value.trim() === ""
        || inputVulnerabilitiesOver60Male.value === undefined || inputVulnerabilitiesOver60Male.value === null || inputVulnerabilitiesOver60Male.value.trim() === ""
        || inputVulnerabilitiesOver60Female.value === undefined || inputVulnerabilitiesOver60Female.value === null || inputVulnerabilitiesOver60Female.value.trim() === "";
}
