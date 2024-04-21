document.getElementById('myForm').addEventListener('keydown', function (e) {
    if (e.key === 'Enter') {
        e.preventDefault(); // Prevent default form submission
        document.getElementById('myButton').click(); // Trigger the 'Save' button click event
    }
});

const oldPassword = document.getElementById('oldPassword');
const newPassword = document.getElementById('newPassword');
const confirmPassword = document.getElementById('confirmPassword');
const alertDiv = document.getElementById('validated_alert');

document.addEventListener('DOMContentLoaded', function () {


// <-----------------------------------------------Password Toggle---------------------------------------------------->

    const toggleButton1 = document.getElementById('togglePassword1');
    const toggleButton2 = document.getElementById('togglePassword2');
    const toggleButton3 = document.getElementById('togglePassword3');


    function setupPasswordToggle(inputField, toggleButton) {
        inputField.addEventListener('input', function () {
            // Show toggle button when there is input in the password field
            toggleButton.style.display = inputField.value.length > 0 ? 'block' : 'none';

        });

        toggleButton.addEventListener('click', function () {
            // Toggle password visibility
            if (inputField.type === 'password') {
                inputField.type = 'text';
            } else {
                inputField.type = 'password';
            }
        });
    }

    setupPasswordToggle(oldPassword, toggleButton1);
    setupPasswordToggle(newPassword, toggleButton2);
    setupPasswordToggle(confirmPassword, toggleButton3);

});

function change() {


    if (oldPassword === undefined || oldPassword.value.trim() === null || oldPassword.value === '' ||
        newPassword === undefined || newPassword.value.trim() === '' || newPassword.value === '' ||
        confirmPassword === undefined || confirmPassword.value.trim() === '' || confirmPassword.value === '') {

        alertDiv.className = 'alert alert-danger position-relative mb-4';
        alertDiv.innerHTML = 'Please Fill All Data to change Password!';
    }
    if (newPassword.value.length < 8 || newPassword.value.length > 12 || confirmPassword.value.length < 8 || confirmPassword.value.length > 12) {
        alertDiv.className = 'alert alert-danger position-relative mb-4';
        alertDiv.innerHTML = 'Passwords should be between 8 to 12 characters!';
    } else if (newPassword.value !== confirmPassword.value) {
        alertDiv.className = 'alert alert-danger position-relative mb-4';
        alertDiv.innerHTML = 'New Password and Confirm Password do not match!';
    } else {
        alertDiv.removeAttribute('class');
        alertDiv.innerHTML = '';

        fetch('/spcu-dims/api/v1/user/password?oldPassword=' + oldPassword.value + '&&newPassword=' + newPassword.value, {
            method: 'PATCH',
            headers: {
                [csrfHeader]: csrfToken,
                'Content-Type': 'application/json'
            },
            body: null,
        })
            .then(async r => {
                oldPassword.value = '';
                newPassword.value = '';
                confirmPassword.value = '';
                if (r.status === 200 || r.status === 201) {

                    alertDiv.className = 'alert alert-primary position-relative mb-4';
                    alertDiv.innerHTML = 'Password Changing Success.'

                } else if (r.status === 304) {

                    alertDiv.className = 'alert alert-danger position-relative mb-4';
                    alertDiv.innerHTML = 'Old Password does not match!'
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alertDiv.className = 'alert alert-danger position-relative mb-4';
                alertDiv.innerHTML = 'An error occurred while processing your request';
            });

    }
}