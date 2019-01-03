function doAjax(idForm, id, idMistake) {
    var inputText = document.getElementById(idForm).value;
    document.getElementById(idMistake).value = '';
    <!-- document.getElementById(idForm).value = ''; -->
    $.ajax({
        url : 'ajaxCart',
        type: 'Post',
        data : ({
            phoneId: id,
            quantity: inputText
        }),

        success: function(data) {
            if (data.errorMessage === null){
                $('#quantity').html(data.quantity);
                $('#summa').html(data.summa);
            }else{
                document.getElementById(idMistake).value = data.errorMessage;
            }
        },
        error: function (data, e) {
        }
    });
}

var deleteCart = function(id) {
    document.getElementById('phoneId').value = id;
    $('#deleteForm').submit();
};
