$(document).ready(function() {
    masks();
    callSelect2();

    $("div[step='1']").keydown(function(event){
        if(event.keyCode == 13) {
            event.preventDefault();
            $("a[href='#info']").click();
            return false;
        }
    });

    $("body").on("click", ".changeStep", function(e) {
        e.preventDefault();

        var target = $(this).attr("href");
        var currentStep = parseInt($(".show").attr("step"));
        var targetStep = parseInt($(this).attr("step"));

        if (validateForm(".show .form-control", true) && (currentStep + 1 == targetStep || currentStep - 1 == targetStep)) {
            $(".changeStep").removeClass("active");
            $("a[href='"+target+"'].nav-link").addClass("active");
            $(".step").hide();
            $(".show").removeClass("show");
            $(target).show();
            $(target).addClass("show");
        }
    });

    $("body").on("click", "input[type='submit'], button[type='submit']", function(e) {
        validateForm($(this).closest("form"));
    });

    $("body").find("input[type='date']").attr("max", new Date().toISOString().split("T")[0]);

    $("body").css("margin-bottom", $("#bar-navigation").height());

    if ($("#chart-weight").length) {
        var chart = new Chart($("#chart-weight"), {
            type: 'line',
            data: {
                datasets: [{
                    label: 'Peso',
                    data: JSON.parse($("#chart-weight").attr("data-values")).reverse(),
                    backgroundColor: 'transparent',
                    borderColor: '#CFD2FF',
                    fontColor: '#CFD2FF',
                }],
                labels: JSON.parse($("#chart-weight").attr("data-labels")).reverse()
            },
            options: {
                layout: {
                    padding: {
                        right: 20,
                    }
                },
                elements: {
                    point: {
                        borderWidth: 6,
                        fill: false,
                        hoverBorderWidth: 6,
                    }
                },
                legend: {
                    display: false,
                    fontColor: "#CFD2FF"
                },
                scales: {
                    xAxes: [{
                        ticks: {
                            fontColor: "#CFD2FF",
                        }
                    }],
                    yAxes: [{
                        position: 'right',
                        ticks: {
                            fontColor: "#CFD2FF",
                            suggestedMin: 40,
                            suggestedMax: 70,
                            stepSize: 10,
                            callback: function(label, index, labels) {
                                return label + ' kg';
                            }
                        },
                        gridLines: {
                            color: "#CFD2FF",
                            display: true,
                            drawOnChartArea: true
                        }
                    }]
                }
            }
        });
    }

    $("body").on("click", ".add-food", function() {
        var i = $(".food").length + 1;

        $(".food").last().after('<div class="food form-row mt-3"><div class="col-12 col-lg-7"><small>Alimento ' + i + '</small><input required type="text" name="food_item[name]" class="form-control" placeholder="Digite o alimento"></div><div class="col-10 col-lg-4"><small>Calorias</small><input required type="text" name="food_item[cal]" class="form-control number" placeholder="Calorias"></div><div class="col-2 col-lg-1"><a type="button" class="btn-action remove-food mt-3" title="Remover alimento"><i class="far fa-trash-alt"></i></a></div></div>');

        masks();
    });

    $("body").on("click", ".remove-food", function() {
        var count = $(".food").length;
        if (count > 1) {
            $(this).parent().parent().remove();
        }
    })

    $("body").on("click", ".add-profile-pic", function() {
        $("body").find(".input-profile-pic").click();
    })

    $("body").on("blur", "#old_password", function() {
        if ($(this).val() != "") {
            $("#password").attr("required", true);
            $("#password_confirm").attr("required", true);
        } else {
            $("#password").attr("required", false);
            $("#password_confirm").attr("required", false);
        }
    });
    
    $("body").on("click", ".close-message", function() {
        $(this).parents(".alert").remove();
    });
    
    if ($("body").find("table")) {
    	$("body").find("table").dataTable();
    }
    
    if ($("body").find(".imc-bar")) {
    	let $this = $("body").find(".imc-bar");
    	let imc = calculateImc($this.data("height"), $this.data("weight"));	
    	
    	$this.find("#imc-" + imc.reference).addClass("active").html("<span>" + imc.value + "</span>");
    	$("#imc-name").html(imc.name);
    }
    
    if ($("body").find("#recomended-weight")) {
    	let $this = $("body").find("#recomended-weight");
    	let weight = calculateWeight($this.data("height"), $this.data("gender"));	
    	
    	$this.html(weight);
    }
    
    if ($("body").find("#recomended-calories")) {
    	let $this = $("body").find("#recomended-calories");
    	let calories = calculateCalories($this.data("height"), $this.data("weight"), $this.data("age"), $this.data("gender"));	
    	
    	$this.html(calories);
    }
});

