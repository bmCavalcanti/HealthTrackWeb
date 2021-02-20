$.validator.setDefaults({
    focusInvalid: false,
    focusCleanup: false,
    errorClass: "invalid",
    lang: "pt-BR",
    highlight: function (element, errorClass, validClass) {
        var elem = $(element);
        if (elem.hasClass("select2-hidden-accessible")) {
            elem.next().find(".select2-selection").addClass(errorClass);
        } else {
            elem.addClass(errorClass);
        }
    },
    unhighlight: function (element, errorClass, validClass) {
        var elem = $(element);
        if (elem.hasClass("select2-hidden-accessible")) {
            elem.next().find(".select2-selection").removeClass(errorClass);
        } else {
            elem.removeClass(errorClass);
        }
    },
    errorPlacement: function(error, element) {
        var elem = $(element);
        if (elem.hasClass("select2-hidden-accessible")) {
            error.insertAfter(elem.next().find(".select2-selection"));
        } else {
            error.insertAfter(element);
        }
    }
});

$.validator.addMethod("fullname", function(value, element) {
    var val = $.trim(value);
    return this.optional(element) || val.indexOf(" ") >= 0;
}, "Nome inválido. Por favor, insira seu nome completo.");

$.extend(true, $.fn.dataTable.defaults, {
	order: [[ 0, "desc" ]],
	language: {
	    "sEmptyTable": "Nada encontrado",
	    "sInfo": "Apresentando de _START_ até _END_ de _TOTAL_ registros",
	    "sInfoEmpty": "Nada encontrado",
	    "sInfoFiltered": "(Filtrando de _MAX_ registros)",
	    "sInfoPostFix": "",
	    "sInfoThousands": ".",
	    "sLengthMenu": "_MENU_ resultados por página",
	    "sLoadingRecords": "Carregando...",
	    "sProcessing": "Processando...",
	    "sZeroRecords": "Nada encontrado",
	    "sSearch": "Buscar",
	    "oPaginate": {
	        "sNext": "Próximo",
	        "sPrevious": "Anterior",
	        "sFirst": "Primeiro",
	        "sLast": "Último"
	    },
	    "oAria": {
	        "sSortAscending": ": Ordenar colunas de forma ascendente",
	        "sSortDescending": ": Ordenar colunas de forma descendente"
	    }
	},
	lengthChange: false,
	ordering: true,
	drawCallback: function() {
	    $(this.api().table().header()).hide();
	}
});

function masks() {
    $(".meter").mask("0.00", {reverse: false});
    $(".kg").mask("000.00", {reverse: true});
    $(".number").mask("#0.00", {reverse: true});
    $(".blood-pressure").mask("000/00");
}

function callSelect2() {
    $("select:not(.d-none)").select2({
        allowClear: true,
        width: "100%",
        placeholder: "Selecione uma opção"
    });
}

function validateForm(form, response = false) {
    $("body").find(form).validate();
    if (response) {
        return $(form).valid();
    }
}
