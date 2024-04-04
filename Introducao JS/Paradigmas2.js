//Sintaxe pré ES6
function Pessoa(nome, cpf){
    //atributos
    this.nome = nome;
    this.cpf = cpf;

    this.saudar = function(){
        console.log(`Nome: ${this.nome}, CPF: ${this.cpf}`);
    }
};

const programador = new Pessoa('Bill Gates', '123');
console.log(programador.saudar());