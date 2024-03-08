function somaA(op1, op2){
    return op1 + op2;
}

const soma = function(op1, op2){
    return op1 + op2;
};

const sub = function(op1, op2){
    return op1 - op2;
};

const a = somaA(1,2);
console.log(a);
console.log(typeof a);

console.log(somaA(5,5));

const b = soma("5",5);
console.log(b);
console.log(typeof b);


// soma = 2+2;
// console.log(soma);
//console.log(typeof soma);
//não se pode mudar os valores pois a variavel soma esta como constante(const), para mudar tem q trocar para let

function hof(operation, writer, op1, op2){
    writer(operation(op1, op2));
}

hof(sub, console.log, 1, 1);