// const nome = 'Gustavo'
// const altura = 1.77
// const hab = true

// console.log(`Tipo nome: ${typeof nome}, altura: ${typeof altura}, hab: ${typeof hab}.`)

// console.log(`Nome: ${nome}, Altura: ${altura}, Habilitação: ${hab}.`)
// console.log(`Nome: ${nome.toUpperCase()}, Altura: ${altura.toFixed()}, Habilitação: ${hab.toString().toUpperCase()}.`)

String.prototype.metodoQualquer = function(){
    return this
}

let nome = 'Gustavo'//string - String
console.log(typeof nome)
console.log(nome.length)//Coersão AQUI
let copiaDoNome = nome.metodoQualquer()//Coersão AQUI
console.log(typeof copiaDoNome)
console.log(copiaDoNome instanceof String)
console.log(typeof nome)


