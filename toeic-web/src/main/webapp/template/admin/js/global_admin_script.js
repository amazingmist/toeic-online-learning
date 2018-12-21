$(document).ready(function () {
    bindEventCheckAllCheckbox();
    enableOrDisableDeleteAll();
    autoCheckCheckboxAll();
});

function bindEventCheckAllCheckbox() {
    $('#chk-check-all').click(function () {
        $(this).closest("table").find("input[type=checkbox]").prop("checked", this.checked);
        $('#btn-delete-all').prop('disabled', !this.checked);
    });
}

function enableOrDisableDeleteAll() {
    $('tbody input[type=checkbox]').click(function () {
        if ($('tbody input[type=checkbox]:checked').length == 0) {
            $('#btn-delete-all').prop('disabled', true);
        } else {
            $('#btn-delete-all').prop('disabled', false);
        }
    });
}

function autoCheckCheckboxAll() {
    $('tbody input[type=checkbox]').change(function () {
        if ($('tbody input[type=checkbox]').length == $('tbody input[type=checkbox]:checked').length){
            $('#chk-check-all').prop("checked", true);
        }else{
            $('#chk-check-all').prop("checked", false);
        }
    });
}