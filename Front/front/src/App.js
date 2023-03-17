import { useEffect, useState } from 'react';
import './App.css';
import FormularioPessoa from './FormularioPessoa';
import Tabela from './Tabela';

function App() {

//Objeto Pessoa
const Pessoa = {
  id: 0,
  nome :'',
  cpf: '',
  dataNascimento: ''
}

//useState
const [btnCadastrar, setBtnCadastrar] = useState(true);
const [Pessoas, setPessoas] = useState([]);
const [objPessoa, setObjPessoa] = useState(Pessoa);

//UseEffect
useEffect(()=>{
  fetch("http://localhost:8080/pessoas/listar")
  .then(retorno => retorno.json())
  .then(retorno_convertido => setPessoas(retorno_convertido));
  }, [])

  //Obtendo dados do formulario
  const aoDigitar = (e) => {
    setObjPessoa({...objPessoa, [e.target.name]:e.target.value});

  }

  //Cadastrar Pessoas
  const cadastrar = () => {
    fetch('http://localhost:8080/pessoas/cadastrar',{
      method: 'post',
      body:JSON.stringify(objPessoa),
      headers:{
        'Content-type':'application/json',
        'Accept':'application/json'
      }
    })
    .then(retorno => retorno.json())
    .then(retorno_convertido =>{
    
      if(retorno_convertido.mensagem !== undefined){
        alert(retorno_convertido.mensagem);
      }else{
        setPessoas([...Pessoas,retorno_convertido]);
        alert('Pessoa cadastrada com sucesso!!');
        limparFormulario();
        
      }
    })
  }

  //limpando formulario
  const limparFormulario = () => {
    setObjPessoa(Pessoa);
  }
  
  //Selecionar Pessoa
  const selecionarPessoa = (indice) => {
    setObjPessoa (Pessoa[indice]);
    setBtnCadastrar(false);
  }

  //Retorno
  return (
    <div>
      <FormularioPessoa botao={btnCadastrar} eventoTeclado={aoDigitar} cadastrar={cadastrar} obj={objPessoa} />
      <Tabela vetor={Pessoas} selecionar={selecionarPessoa} />
      
    </div>
  );
}

export default App;
