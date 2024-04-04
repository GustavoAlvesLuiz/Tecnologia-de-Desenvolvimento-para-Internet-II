const numeros = [1, 2, 3, 4, 5];

const quadrados = []

for(let n of numeros){
    let quadrado = n * n;
    quadrados.push(quadrado); 
}


// function quadrado(valor){
//return valor * valor; 
// }

//Funcional
const quadrados2 = numeros.map((n) => n * n);

//Exemplos com reduce

console.log(numeros);
console.log(quadrados);
console.log(quadrados2);