let table
let data_id

let button = document.getElementById('myButton');
let title = document.getElementById('modelTitle');

let activityFilter = document.getElementById('filterActivity');
let parishFilter = document.getElementById('filterParish');

let activitySelect = document.getElementById('activitySelect');
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

$(document).ready(async function () {

    await activityFetch();
    await dioceseFetch();
    await zoneFetch();
    await parishFetch();

    table = $('#myTable').DataTable({
        dom: 'flrtip', language: {
            infoFiltered: null,
        }, ajax: {
            url: 'spcu-dims/api/v1/beneficiary-lists/show/table',
            method: 'POST',
            contentType: 'application/json',
            headers: {
                [csrfHeader]: csrfToken
            },
            data: function (d) {
                d.columns[14].search.value = false;
                return JSON.stringify(d);
            },
        }, processing: true, serverSide: true, stateSave: true, scrollX: true, columns: [{
            data: 'id', render: function (data, type, row, meta) {
                // meta.row contains the row index
                return meta.row + 1;
            }
        }, {
            data: 'activity.name'
        }, {
            data: 'parish.zone.name'
        }, {
            data: 'parish.zone.diocese.name'
        }, {
            data: 'parish.name'
        }, {
            data: 'beneficiary.houseHold'
        }, {
            data: 'beneficiary.male'
        }, {
            data: 'beneficiary.female'
        }, {
            data: 'disability.male'
        }, {
            data: 'disability.female'
        }, {
            data: 'vulnerability.under18Male'
        }, {
            data: 'vulnerability.under18Female'
        }, {
            data: 'vulnerability.over60Male'
        }, {
            data: 'vulnerability.over60Female'
        }, {
            data: 'isDelete', render: function (data, type, row) {
                return '<button type="button" class="btn btn-outline-warning p-0" data-bs-toggle="modal" data-bs-target="#model" ' +
                    'onclick="changeEdit(' + row.id + ', \'' + row.activity.name.replace(/'/g, "\\'") + '\', \'' + row.parish.name.replace(/'/g, "\\'") +
                    '\', ' + row.beneficiary.houseHold + ', ' + row.beneficiary.male + ', ' + row.beneficiary.female + ', ' + row.disability.male + ', ' + row.disability.female +
                    ', ' + row.vulnerability.under18Male + ', ' + row.vulnerability.under18Female + ', ' + row.vulnerability.over60Male +
                    ', ' + row.vulnerability.over60Female + ')" style="border: none">\n' +
                    '<i class="bi bi-pencil-square mx-1" style="font-size: 1.1rem"></i>\n' +
                    '</button>\n' +
                    '<button type="button" onclick="showConfirm(' + row.id + ')" class="btn btn-outline-danger p-0" style="border: none">\n' +
                    '<i class="bi bi-trash3 mx-1" style="font-size: 1.1rem"></i>\n' +
                    '</button>';}
        }],
    });
});

activityFilter.addEventListener('change', function () {
    table.column(1).search(activityFilter.value).draw();
})

parishFilter.addEventListener('change', function () {
    table.column(4).search(parishFilter.value).draw();
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

async function dioceseFetch() {
    try {
        const data = await fetchData('spcu-dims/api/v1/diocese/show/all');
        await populateSelect(dioceseSelect, data, "Select Related Diocese", false);
    } catch (error) {
        Notiflix.Notify.failure('Data Fetching Error!');
    }
}

async function zoneFetch() {
    try {
        const data = await fetchData('spcu-dims/api/v1/zone/show/all');
        await populateSelect(zoneSelect, data, "Select Related Zone", false);
    } catch (error) {
        Notiflix.Notify.failure('Data Fetching Error!');
    }
}

async function parishFetch() {
    try {
        const data = await fetchData('spcu-dims/api/v1/parish/show/all');
        await populateSelect(parishSelect, data, "Select Related Parish", false);
        await populateSelect(parishFilter, data, "Filter Parish", true);
    } catch (error) {
        Notiflix.Notify.failure('Data Fetching Error!');
    }
}

function changeAdd() {
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

function changeEdit(id, activity, parish, houseHold, male, female, disabilityMale, disabilityFemale, under18Male, under18Female, over60Male, over60Female) {
    console.log(parish);
    button.innerHTML = 'Edit';
    title.textContent = 'Edit Beneficiary List';
    button.setAttribute('onclick', 'editing()');

    data_id = id;
    activitySelect.value = activity;
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
