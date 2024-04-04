let nula = null
let indefinida = undefined

console.log(nula == indefinida)//true
console.log(nula === indefinida)//false
console.log(10 == '10')//true
console.log(10 === '10')//false
console.log('\n')

console.log(nula ? 'verdadeira' : 'falsa')
console.log(indefinida ? 'verdadeira' : 'falsa')
console.log('\n')

console.log(nula == false)//false
console.log(true && nula)
console.log('\n')

console.log(2 + indefinida)//NaN
console.log(2 + nula)//2, pois o null vira uma ausencia de valor
console.log('\n')

console.log('2' + indefinida)
console.log('2' + nula)
console.log('\n')