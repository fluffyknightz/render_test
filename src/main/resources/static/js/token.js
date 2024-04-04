const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute("content");
const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute("content");

document.addEventListener('DOMContentLoaded', function () {
    login_user()
        .then(async r => {
            document.getElementById('login_user').innerText = await r.text();
        });
});

function login_user() {
    return fetch('/spcu-dims/api/v1/user/login-user', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            [csrfHeader]: csrfToken
        },
        body: null// Pass an empty object as the body
    });
}
