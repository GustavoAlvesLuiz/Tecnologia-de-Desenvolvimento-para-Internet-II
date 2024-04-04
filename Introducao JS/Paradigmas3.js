const bill = {
    nome: 'Bill Gates',
    cpf: '123',
    nascimento: '1/1/1965',
};

// console.log(bill);
// console.log(typeof bill);

Object.getPrototypeOf(bill).saudar = function(){
    console.log(`Nome: ${this.nome}, CPF: ${this.cpf}`);
};

console.log(bill.saudar());
console.log(bill.__proto__);
console.log(bill.prototype);
