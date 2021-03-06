$(document).ready(function () {
    bindEventCheckAllCheckbox();
    enableOrDisableDeleteAll();
    autoCheckCheckboxAll();
});

function bindEventCheckAllCheckbox() {
    $('#chkCheckAll').click(function () {
        $(this).closest("table").find("input[type=checkbox]").prop("checked", this.checked);
        $('#btnDeleteAll').prop('disabled', !this.checked);
    });
}

function enableOrDisableDeleteAll() {
    $('tbody input[type=checkbox]').click(function () {
        if ($('tbody input[type=checkbox]:checked').length == 0) {
            $('#btnDeleteAll').prop('disabled', true);
        } else {
            $('#btnDeleteAll').prop('disabled', false);
        }
    });
}

function autoCheckCheckboxAll() {
    $('tbody input[type=checkbox]').change(function () {
        if ($('tbody input[type=checkbox]').length == $('tbody input[type=checkbox]:checked').length) {
            $('#chkCheckAll').prop("checked", true);
        } else {
            $('#chkCheckAll').prop("checked", false);
        }
    });
}

function showSweetAlertBeforeDelete(confirmCallback, cancelCallback) {
    swal({
        title: "Xác nhận xoá",
        text: "Bạn có chắc chắn muốn xoá dữ liệu đã chọn?",
        icon: "warning",
        buttons: ["Huỷ bỏ", "Xác nhận"],
        dangerMode: true,
    }).then(function (isConfirm) {
        if (isConfirm) {
            confirmCallback();
        } else {
            cancelCallback();
        }
    });

}