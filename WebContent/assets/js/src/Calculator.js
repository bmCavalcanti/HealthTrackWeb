function calculateCalories(height, weight, age, gender) {
    let calories = 0;

    if (gender == 1) { // FEMININO
        calories = 655.1 + (1.8 * (height * 100)) + (9.5 * weight) - (4.7 * age);
    }

    if (gender == 2) { // MASCULINO
        calories = 66.5 + (5 * (height * 100)) + (13.8 * weight) - (6.8 * age);
    }

    return parseFloat(calories).toFixed(2);
}

function calculateWeight(height, gender) {
    var weight = 0;

    if (gender == 1) { // FEMININO
        weight = (62.1 * height) - 44.7;
    }

    if (gender == 2) { // MASCULINO
        weight = (72.7 * height) - 58;
    }

    return parseFloat(weight).toFixed(2);
}

function calculateImc(height, weight) {
	
	let name = "";
	let reference = 0;
	let imc = weight / (height * height);
	
	if (imc < 16) {
		name = "Baixo peso Grau III";
		reference = 1;
	} else if (imc >= 16 && imc <= 16.99) {
		name = "Baixo peso Grau II";
		reference = 2;
	} else if (imc >= 17 && imc <= 18.49) {
		name = "Baixo peso Grau I";
		reference = 3;
	} else if (imc >= 18.50 && imc <= 24.99) {
		name = "Peso ideal";
		reference = 4;
	} else if (imc >= 25 && imc <= 29.99) {
		name = "Sobrepeso";
		reference = 5;
	} else if (imc >= 30 && imc <= 34.99) {
		name = "Obesidade Grau I";
		reference = 6;
	} else if (imc >= 35 && imc <= 39.99) {
		name = "Obesidade Grau II";
		reference = 7;
	} else if (imc > 40) {
		name = "Obesidade Grau III";
		reference = 8;
	}
	
	return {
		"name": name,
		"value": parseFloat(imc).toFixed(2),
		"reference": reference
	};
}

