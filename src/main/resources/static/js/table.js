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

function performOperation(url, method, successMessage) {

    fetch(url, {
        method: method, headers: {
            contentType: 'application/json', [csrfHeader]: csrfToken
        }, body: null
    }).then(async response => {
        if (response.status === 201 || response.status === 200) {
            $('#model').modal('hide');
            Notiflix.Notify.success(successMessage);
            table.ajax.reload();
        } else {
            Notiflix.Notify.failure(await response.text());
        }
    }).catch(error => {
            Notiflix.Notify.failure("Error has occurred during process. Please Try again later.");
            console.error(error); // Optionally log the error to the console for debugging
    });
}

Notiflix.Confirm.init({
    titleColor: '#4d56ec', okButtonColor: '#f8f8f8', okButtonBackground: '#f32222'
});

Notiflix.Notify.init({
    position: 'center-top'

});

document.getElementById('myForm').addEventListener('keydown', function (e) {
    if (e.key === 'Enter') {
        e.preventDefault(); // Prevent default form submission
        button.click(); // Trigger the 'Save' button click event
    }
});

function deleteConfirm(id, url) {
    Notiflix.Confirm.show('Please Confirm', 'Are you sure!! you want to delete?', 'Yes', 'No', function okCb() {
        fetch(url + encodeURIComponent(id), {
            method: 'PATCH', headers: {
                [csrfHeader]: csrfToken
            }
        })
            .then(r => {
                if (r.ok) {

                    Notiflix.Notify.success('Delete successful.');
                    table.ajax.reload();


                } else {
                    Notiflix.Notify.failure('Delete Failed!');
                }
            })

    }, {
        width: '320px', borderRadius: '8px',
    },);
}

async function fetchData(apiEndpoint, filterParam = null) {
    const url = filterParam ? `${apiEndpoint}?${filterParam}` : apiEndpoint;

    try {
        const response = await fetch(url, {
            method: 'POST', headers: {
                'Content-Type': 'application/json', [csrfHeader]: csrfToken
            }, body: null
        });

        return await response.json();
    } catch (error) {
        console.error('Error fetching data:', error);
        throw error; // You may want to handle or log the error appropriately
    }
}

async function populateSelect(element, data, text, option) {
    element.innerHTML = (option)
        ? '<option selected value="" disabled>' + text + '</option><option value="">None</option>'
        : '<option selected value="" disabled>' + text + '</option>';

    data.forEach(item => {
        const option = document.createElement('option');
        option.value = item;
        option.textContent = item;
        element.appendChild(option);
    });
}