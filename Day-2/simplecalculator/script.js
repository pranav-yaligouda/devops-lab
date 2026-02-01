function appendValue(value){
    document.getElementById("result").value += value;
}

function clearResult(){
    document.getElementById("result").value = "";
}

function calculate() {
    try {
        let res = eval(document.getElementById("result").value);
        document.getElementById("result").value = res;
    } catch (e) {
        alert("Invalid Expression");
    }
}