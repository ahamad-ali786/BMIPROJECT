


function button_check(){

 let inputName  = document.getElementById("isName").value;
 console.log(`welcome ${inputName} . please sit here...`);
 console.log( typeof inputName);
 
let inputHeight  = Number(document.getElementById("isHeight").value);

console.log("height : " , inputHeight, "cm");
console.log(typeof inputHeight);



let heightInMeters = inputHeight/100;

 console.log("heightInMeters : ", Number(heightInMeters), "m");

 console.log(typeof heightInMeters);
 
let inputWeight = Number(document.getElementById("isWeight").value);
 console.log( "weight : ", inputHeight,"kg");
 console.log(typeof inputWeight);

 let bmi = (inputWeight / heightInMeters **2).toFixed(2);
 console.log("bmi : " , bmi);




let isresult = document.getElementById("result");

// NORMAL
if (bmi >= 18.5 && bmi <= 25) {

    isresult.innerHTML = `
        BMI is: ${bmi}<br>
        Hello ${inputName}, You don't need to worry. You are normal.
    `;

}

// UNDERWEIGHT
else if (bmi >= 10 && bmi < 18.5) {

    isresult.innerHTML = `
        BMI is: ${bmi}<br>
        Hello ${inputName}, You are underweight.
    `;

}

// OVERWEIGHT
else if (bmi > 25 && bmi <= 50) {

    isresult.innerHTML = `
        BMI is: ${bmi}<br>
        Hello ${inputName}, You are overweight.
    `;

}

// OTHER CASE
else {

    isresult.innerHTML = `
        BMI is: ${bmi}<br>
        Hello ${inputName}, Please consult a doctor.
    `;
}

}
function resetBtn(){

document.getElementById("isName").value="";
document.getElementById("isHeight").value="";
document.getElementById("isWeight").value="";

document.getElementById('result').innerHTML = "";

};

let logoutproile = document.getElementById('logout').style.display = "none";

