let navPassword = document.getElementById('navPassword');
let navConfirmPassword = document.getElementById('navConfirmPassword');




function navPasswordChange() {
    if (password.value === undefined || password.value === null || password.value.trim() === "" ||
        confirmPassword.value === undefined || confirmPassword.value === null || confirmPassword.value.trim() === "") {
        Notiflix.Notify.failure("Please enter All Data!");
    } else if (navPassword.value !== navConfirmPassword.value) {
        Notiflix.Notify.failure('Passwords do not match!');
    }
    fetch('/spcu-dims/api/v1/user/password?password=' + encodeURIComponent(password.value), {
        method: 'PATCH',
        headers: {
            [csrfHeader]: csrfToken
        },
        body: null
    }).then(r => {
        if (r.status === 200 || r.status === 201) {
            Notiflix.Notify.failure("Password Changing Success");
        }
    })
}