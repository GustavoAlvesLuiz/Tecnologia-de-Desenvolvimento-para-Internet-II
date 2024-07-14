const chave_transcoes_ls = "transacoes";
const form = document.getElementById("form");
const descInput = document.getElementById("descricao");
const valorInput = document.querySelector("#montante");
const balancoH1 = document.getElementById("balanco");
const receitaP = document.querySelector("#din-positivo");
const despesaP = document.querySelector("#din-negativo");
const transacoesUL = document.getElementById("transacoes");

let transacoesSalvas;
let ultimoId;

try {
  transacoesSalvas = JSON.parse(localStorage.getItem(chave_transcoes_ls));
  ultimoId = parseInt(localStorage.getItem("ultimoId")) || 0;
} catch (erro) {
  transacoesSalvas = [];
  ultimoId = 0;
}

if (transacoesSalvas == null) {
  transacoesSalvas = [];
}

form.addEventListener("submit", (event) => {
  event.preventDefault();

  const descTransacao = descInput.value.trim();
  const valorTransacao = valorInput.value.trim();
  const isDespesa = document.getElementById("despesa").checked;

  if (descTransacao === "") {
    alert("Informe a descrição da transação!");
    descInput.focus();
    return;
  }
  if (valorTransacao === "") {
    alert("Informe o valor da transação!");
    valorInput.focus();
    return;
  }

  const transacao = {
    id: ultimoId,
    desc: descTransacao,
    valor: parseFloat(isDespesa ? -valorTransacao : valorTransacao),
  };

  alert(`ID da transação: ${transacao.id}`);  // Exibe o ID da transação

  ultimoId += 1;
  localStorage.setItem("ultimoId", ultimoId);

  somaAoSaldo(transacao);
  somaRecitaDespesa(transacao);
  addTransacaoAoDOM(transacao);

  transacoesSalvas.push(transacao);
  localStorage.setItem(chave_transcoes_ls, JSON.stringify(transacoesSalvas));

  descInput.value = "";
  valorInput.value = "";
});

function somaAoSaldo(transacao) {
  let valorBalanco = balancoH1.innerHTML.trim();
  valorBalanco = valorBalanco.replace("R$", "");

  valorBalanco = parseFloat(valorBalanco);
  valorBalanco += transacao.valor;

  balancoH1.innerHTML = `R$${valorBalanco.toFixed(2)}`;
}

function somaRecitaDespesa(transacao) {
  const elemento = transacao.valor > 0 ? receitaP : despesaP;
  const substituir = transacao.valor > 0 ? "+ R$" : "- R$";
  let valor = elemento.innerHTML.replace(substituir, "");
  valor = parseFloat(valor);
  valor += Math.abs(transacao.valor);

  elemento.innerHTML = `${substituir}${valor.toFixed(2)}`;
}

function addTransacaoAoDOM(transacao) {
  const cssClass = transacao.valor > 0 ? "positivo" : "negativo";

  const currency = transacao.valor > 0 ? "R$" : "-R$";

  const liElement = document.createElement("li");
  liElement.classList.add(cssClass);
  liElement.setAttribute("data-id", transacao.id);
  liElement.innerHTML = `${transacao.desc} <span>${currency}${Math.abs(
    transacao.valor
  )}</span><button class="delete-btn" onclick="deletaTransacao(${transacao.id})">X</button>`;

  transacoesUL.append(liElement);
}

function carregarDados() {
  transacoesUL.innerHTML = "";
  balancoH1.innerHTML = "R$0.00";
  receitaP.innerHTML = "+ R$0.00";
  despesaP.innerHTML = "- R$0.00";

  for (let i = 0; i < transacoesSalvas.length; i++) {
    let transacao = transacoesSalvas[i];
    somaAoSaldo(transacao);
    somaRecitaDespesa(transacao);
    addTransacaoAoDOM(transacao);
  }
}

function deletaTransacao(id) {
  const transacaoIndex = transacoesSalvas.findIndex(
    (transacao) => transacao.id == id
  );

  const transacao = transacoesSalvas[transacaoIndex];
  transacoesSalvas.splice(transacaoIndex, 1);
  localStorage.setItem(chave_transcoes_ls, JSON.stringify(transacoesSalvas));

  // Atualiza saldo e receitas/despesas
  let valorBalanco = parseFloat(balancoH1.innerHTML.replace("R$", ""));
  valorBalanco -= transacao.valor;
  balancoH1.innerHTML = `R$${valorBalanco.toFixed(2)}`;

  const elemento = transacao.valor > 0 ? receitaP : despesaP;
  const substituir = transacao.valor > 0 ? "+ R$" : "- R$";
  let valor = parseFloat(elemento.innerHTML.replace(substituir, ""));
  valor -= Math.abs(transacao.valor);
  elemento.innerHTML = `${substituir}${valor.toFixed(2)}`;

  // Remove o elemento do DOM
  const liElement = document.querySelector(`li[data-id='${id}']`);
  liElement.remove();
}

carregarDados();
