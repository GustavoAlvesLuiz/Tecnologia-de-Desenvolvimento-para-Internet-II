let strNativa = 'Gustavo' 
let strNativaFunction = String('Alves')
let strAsAnObject = new String('Luiz')

console.log(typeof strNativa)//string
console.log(typeof strNativaFunction)//string
console.log(typeof strAsAnObject)//object
console.log('\n')

let isString = strAsAnObject instanceof String
console.log(isString)//true
console.log('\n')

//instanceof só funciona com objetos
console.log(strNativa instanceof String)//false
console.log(strNativaFunction instanceof String)//false
console.log('\n')

console.log(strNativa.constructor == String)//true
console.log(strNativaFunction.constructor == String)//true
console.log(strAsAnObject.constructor == String)//true