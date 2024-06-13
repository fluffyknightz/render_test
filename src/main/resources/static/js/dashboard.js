let counts;
let region = document.getElementById("region");
let district = document.getElementById("district");
let township = document.getElementById("township");
let archdiocese = document.getElementById("archdiocese");
let diocese = document.getElementById("diocese");
let zone = document.getElementById("zone");
let parish = document.getElementById("parish");
let activity = document.getElementById("activity");
let beneficiaryList = document.getElementById("beneficiaryList");
let congregation = document.getElementById("congregation");
let annualReturn = document.getElementById("annual-return");

function clickLink(path){
    window.location.href = path;
}

document.addEventListener('DOMContentLoaded', async function () {
    await fetch("/spcu-dims/api/v1/dashboard/count", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            [csrfHeader]: csrfToken
        },
        body: null
    })
        .then(async response => {
            if (response.status === 201 || response.status === 200) {
                counts = await response.json();
                region.textContent = counts.region
                district.textContent = counts.district
                township.textContent = counts.township
                archdiocese.textContent = counts.archdiocese
                diocese.textContent = counts.diocese
                zone.textContent = counts.zone
                parish.textContent = counts.parish
                activity.textContent = counts.activity
                beneficiaryList.textContent = counts.beneficiaryList
                congregation.textContent = counts.congregation
                annualReturn.textContent = counts.annualReturn
            } else {
                Notiflix.Notify.failure("Error at Fetching data.");
            }
        })
})