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
        if ($('tbody input[type=checkbox]').length == $('tbody input[type=checkbox]:checked').length){
            $('#chkCheckAll').prop("checked", true);
        }else{
            $('#chkCheckAll').prop("checked", false);
        }
    });
}