$('#imagenFileEntry').hide();
$("div[id$='fileEntryMedia'] input").on('change', function () {
    if (this.files && this.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#imagenFileEntry').attr('src', e.target.result);
            $('#imagenFileEntry').show();
        }
        reader.readAsDataURL(this.files[0]);
    }
});