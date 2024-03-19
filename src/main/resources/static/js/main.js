/*
=========================================================
* Dash UI - Bootstrap 5 Admin & Dashboard Theme
=========================================================
* Product Page: https://codescandy.com/dashui/index.html
* Copyright 2020 Codescandy (https://codescandy.com/)
* Designed and coded by https://codescandy.com
========================================================= */


//
// Main js
//

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


'use strict';
(function () {

    // Menu toggle for admin dashboard

    if ($("#nav-toggle").length) {
        $("#nav-toggle").on("click", function (e) {
            e.preventDefault();
            $("#db-wrapper").toggleClass("toggled");
        });

    }


    //  slimscroll for sidebar nav

    if ($(".nav-scroller").length) {
        $(".nav-scroller").slimScroll({
            height: "97%",
        });
    }


    // Notification dropdown scroll List

    if ($('.notification-list-scroll').length) {
        $(".notification-list-scroll").slimScroll({
            height: 300,
        });
    }


    // Default Tooltip

    if ($('[data-bs-toggle="tooltip"]').length) {
        const tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
        const tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
            return new bootstrap.Tooltip(tooltipTriggerEl)
        });
    }


    // Default Popover

    if ($('[data-bs-toggle="popover"]').length) {
        const popoverTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="popover"]'));
        const popoverList = popoverTriggerList.map(function (popoverTriggerEl) {
            return new bootstrap.Popover(popoverTriggerEl)
        });
    }

    // Scrollspy

    if ($('[data-bs-spy="scroll"]').length) {
        const dataSpyList = [].slice.call(document.querySelectorAll('[data-bs-spy="scroll"]'));
        dataSpyList.forEach(function (dataSpyEl) {
            bootstrap.ScrollSpy.getInstance(dataSpyEl)
                .refresh()
        })

    }

    // Toast

    if ($('.toast').length) {

        const toastElList = [].slice.call(document.querySelectorAll('.toast'));
        const toastList = toastElList.map(function (toastEl) {
            return new bootstrap.Toast(toastEl)
        });

    }







})();
