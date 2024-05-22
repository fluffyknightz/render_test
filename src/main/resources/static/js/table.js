let inputValue


function validationInput() {

    inputValue = input.value.trim();

    // Remove leading and trailing spaces
    inputValue = inputValue.replace(/^\s+|\s+$/g, '');

    // Replace consecutive spaces with a single space
    inputValue = inputValue.replace(/\s+/g, ' ');

    // Capitalize the first letter
    inputValue = inputValue.charAt(0).toUpperCase() + inputValue.slice(1);

}

function deleteConfirm(url) {
    Notiflix.Confirm.show('Please Confirm', 'Are you sure you want to delete?', 'Yes', 'No', async function okCb() {
        await performOperation(url, 'PATCH', 'Delete Successful!');
        table.ajax.reload();
    }, {
        width: '320px', borderRadius: '8px',
    });
}

async function performOperation(url, method, successMessage, jsonBody) {

    await fetch(url, {
        method: method,
        headers: {
            'Content-Type': 'application/json',
            [csrfHeader]: csrfToken
        },
        body: jsonBody ? JSON.stringify(jsonBody) : null
    }).then(async response => {
        if (response.ok) {
            $('#model').modal('hide');
            Notiflix.Notify.success(successMessage);
            table.ajax.reload();

        } else if (response.status === 405) {
            Notiflix.Notify.failure("Error has occurred during process. Please Try again later.");
        } else {
            Notiflix.Notify.failure(await response.text());
        }
    }).catch(error => {
        Notiflix.Notify.failure("Error has occurred during process. Please Try again later.");
    });
}


async function fetchData(apiEndpoint, filterParam = null, jsonBody = null) {
    const url = filterParam ? `${apiEndpoint}?${filterParam}` : apiEndpoint;

    try {
        const response = await fetch(url, {
            method: 'POST', headers: {
                'Content-Type': 'application/json',
                [csrfHeader]: csrfToken
            }, body: jsonBody ? JSON.stringify(jsonBody) : null
        });

        return await response.json();
    } catch (error) {
        Notiflix.Notify.failure('Error has occurred during fetching data.');
    }
}

async function populateSelect(element, data, text, option) {
    element.innerHTML = (option)
        ? '<option selected value="" disabled>' + text + '</option><option value="">None</option>'
        : '<option selected value="" disabled>' + text + '</option>';

    await data.forEach(item => {
        const option = document.createElement('option');
        option.value = item;
        option.textContent = item;
        element.appendChild(option);
    });
}