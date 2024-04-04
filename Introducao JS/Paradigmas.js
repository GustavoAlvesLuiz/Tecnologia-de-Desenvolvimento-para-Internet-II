//Sintaxe da ECMAScript 6
class Pessoa{
    //atributos
    constructor(nome, cpf){
        this.nome = nome;
        this.cpf = cpf;
    }

    //metodos
    saudar(){
        console.log(`Nome: ${this.nome}, CPF: ${this.cpf}`);
    }
}

const gustavo = new Pessoa("Gustavo", "123");
console.log(gustavo);
console.log(typeof gustavo);
console.log(typeof Pessoa);
