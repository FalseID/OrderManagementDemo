$(document).ready(function () {
    $('a[data-toggle="tab"]').on('show.bs.tab', function (e) {
        localStorage.setItem('activeTab', $(e.target).attr('href'));
    });
    var activeTab = localStorage.getItem('activeTab');
    if (activeTab) {
        $('#myTab a[href="' + activeTab + '"]').tab('show');
    }
    $('#orderTable').DataTable({bPaginate: false, bFilter: false, bInfo: false});
});

function editClientModal(securityCode, firstName, lastName, phoneNumber, country, address) {
    $(".modal-body #securityCode").val(securityCode);
    $(".modal-body #firstName").val(firstName);
    $(".modal-body #lastName").val(lastName);
    $(".modal-body #phoneNumber").val(phoneNumber);
    $(".modal-body #country").val(country);
    $(".modal-body #address").val(address);
    $('#editClient').modal('show');
}

function editProductModal(barCode, name, price, desc, releaseDate) {
    $(".modal-body #barCode").val(barCode);
    $(".modal-body #name").val(name);
    $(".modal-body #price").val(price);
    $(".modal-body #desc").val(desc);
    $(".modal-body #releaseDate").val(releaseDate);
    $('#editProduct').modal('show');
}
