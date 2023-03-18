import { useEffect, useState } from 'react';
import FormularioContato from './FormularioContato';
import FormularioPessoa from './FormularioPessoa';
import TabelaContato from './TabelaContato';
import TabelaPessoa from './TabelaPessoa';
import MostrarContatos from './MostrarContatos';


function TelaPrincipal() {
    const [mostrarContatos, setMostrarContatos] = useState(false);
    const [mostrarFormularioPessoa, setMostrarFormularioPessoa] = useState(false);
    const [mostrarFormularioContato, setMostrarFormularioContato] = useState(false);
    const [mostrarTabelaPessoa, setMostrarTabelaPessoa] = useState(false);
    const [mostrarTabelaContato, setMostrarTabelaContato] = useState(false);
    const [Pessoas, setPessoas] = useState([]);
    const [Contatos, setContatos] = useState([]);
    const [objPessoa, setObjPessoa] = useState({ id: '', nome: '', cpf: '', dataNascimento: '', contatos: [] });
    const [objContato, setObjContato] = useState({ id: '', nome: '', telefone: '', email: '' });
    const [btnCadastrar, setBtnCadastrar] = useState(true);
    const [btnFiltrarPessoaN, setBtnFiltrarPessoaN] = useState(false);
    const [btnFiltrarContatoN, setBtnFiltrarContatoN] = useState(false);
    const [btnFiltrarPessoaI, setBtnFiltrarPessoaI] = useState(false);
    const [btnFiltrarContatoI, setBtnFiltrarContatoI] = useState(false);
    const [btnDesfiltrar, setbtnDesfiltrar] = useState(true);

    //UseEffect
    useEffect(() => {
        fetch("http://localhost:8080/pessoas/listar")
            .then(retorno => retorno.json())
            .then(retorno_convertido => setPessoas(retorno_convertido));
    }, [])

    useEffect(() => {
        fetch("http://localhost:8080/contatos/listar")
            .then(retorno => retorno.json())
            .then(retorno_convertido => setContatos(retorno_convertido));
    }, [])

    //cadastrar pessoa
    const cadastrarPessoa = () => {
        if (!objPessoa.nome || !objPessoa.cpf || !objPessoa.dataNascimento ||
            !objContato.id || !objContato.nome || !objContato.telefone || !objContato.email) {
            alert('Todos os campos são obrigatórios!');
            return;
        }

        const objPessoaComContato = {
            nome: objPessoa.nome,
            cpf: objPessoa.cpf,
            dataNascimento: objPessoa.dataNascimento,
            contatos: [
                {
                    id: objContato.id,
                    nome: objContato.nome,
                    telefone: objContato.telefone,
                    email: objContato.email
                }
            ]
        };

        fetch('http://localhost:8080/pessoas/cadastrar', {
            method: 'post',
            body: JSON.stringify(objPessoaComContato),
            headers: {
                'Content-type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then(retorno => retorno.json())
            .then(retorno_convertido => {
                if (retorno_convertido.mensagem !== undefined) {
                    alert(retorno_convertido.mensagem);
                } else {
                    setPessoas([...Pessoas, retorno_convertido]);
                    alert('Pessoa cadastrada com sucesso!!');
                    limparFormularioP();
                    limparFormularioC();
                }
            })
    }

    //cadastrar contato
    const cadastrarContato = () => {
        if (!objContato.nome || !objContato.telefone || !objContato.email) {
            alert('Todos os campos são obrigatórios!');
            return;
        }

        fetch('http://localhost:8080/contatos/cadastrar', {
            method: 'post',
            body: JSON.stringify(objContato),
            headers: {
                'Content-type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then(retorno => retorno.json())
            .then(retorno_convertido => {
                setContatos([...Contatos, retorno_convertido]);
                alert('Contato cadastrado com sucesso!!');
                limparFormularioC();
                

            })
    }


    //alterar pessoa
    const alterarPessoa = () => {
        if (!objPessoa.nome || !objPessoa.cpf || !objPessoa.dataNascimento ||
            !objContato.id || !objContato.nome || !objContato.telefone || !objContato.email) {
            alert('Todos os campos são obrigatórios!');
            return;
        }

        const objPessoaComContato = {
            nome: objPessoa.nome,
            cpf: objPessoa.cpf,
            dataNascimento: objPessoa.dataNascimento,
            contatos: [
                {
                    id: objContato.id,
                    nome: objContato.nome,
                    telefone: objContato.telefone,
                    email: objContato.email
                }
            ]
        };

        fetch('http://localhost:8080/pessoas/alterar', {
            method: 'put',
            body: JSON.stringify(objPessoaComContato),
            headers: {
                'Content-type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then(retorno => retorno.json())
            .then(retorno_convertido => {
                if (retorno_convertido.mensagem !== undefined) {
                    alert(retorno_convertido.mensagem);
                } else {

                    //mensagem
                    alert('Pessoa alterada com sucesso!!');

                    //copia do vetor de contatos
                    let vetorTemp = [...Pessoas];

                    //indice
                    let indice = vetorTemp.findIndex((c) => {
                        return c.id === objPessoa.id;

                    });

                    //alterar contato do vetorTemp
                    vetorTemp[indice] = objPessoa

                    //atualizar o vetor de produtos
                    setPessoas(vetorTemp);

                    limparFormularioP();
                }
            })
    }

    //alterar contato
    const alterarContato = () => {
        if (!objContato.nome || !objContato.telefone || !objContato.email) {
            alert('Todos os campos são obrigatórios!');
            return;
        }

        fetch('http://localhost:8080/contatos/alterar', {
            method: 'put',
            body: JSON.stringify(objContato),
            headers: {
                'Content-type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then(retorno => retorno.json())
            .then(retorno_convertido => {

                //mensagem
                alert('Contato alterado com sucesso!!');

                //copia do vetor de contatos
                let vetorTemp = [...Contatos];

                //indice
                let indice = vetorTemp.findIndex((c) => {
                    return c.id === objContato.id;

                });

                //alterar contato do vetorTemp
                vetorTemp[indice] = objContato;

                //atualizar o vetor de produtos
                setContatos(vetorTemp);

                limparFormularioC();

            })
    }

    //filtrar pessoa pelo Id
    const filtrarIdPessoa = (id) => {
        if (btnFiltrarPessoaI === true) {
            fetch('http://localhost:8080/pessoas/listarId/' + id, {
                method: 'get',
                headers: {
                    'Content-type': 'application/json',
                    'Accept': 'application/json'
                }
            })
                .then(retorno => retorno.json())
                .then(retorno_convertido => setPessoas([retorno_convertido]));
        }

    }

    //filtrar contato pelo Id
    const filtrarIdContato = (id) => {
        if (btnFiltrarContatoI === true) {
            fetch('http://localhost:8080/contatos/listarId/' + id, {
                method: 'get',
                headers: {
                    'Content-type': 'application/json',
                    'Accept': 'application/json'
                }
            })
                .then(retorno => retorno.json())
                .then(retorno_convertido => setContatos([retorno_convertido]));
        }

    }

    //filtrar pessoa pelo nome
    const filtrarNomePessoa = (nome) => {
        if (btnFiltrarPessoaN === true) {
            fetch('http://localhost:8080/pessoas/listarNome/' + nome, {
                method: 'get',
                headers: {
                    'Content-type': 'application/json',
                    'Accept': 'application/json'
                }
            })
                .then(retorno => retorno.json())
                .then(retorno_convertido => setPessoas(retorno_convertido));
        }

    }

    //filtrar contato pelo nome
    const filtrarNomeContato = (nome) => {
        if (btnFiltrarContatoN === true) {
            fetch('http://localhost:8080/contatos/listarNome/' + nome, {
                method: 'get',
                headers: {
                    'Content-type': 'application/json',
                    'Accept': 'application/json'
                }
            })
                .then(retorno => retorno.json())
                .then(retorno_convertido => setContatos(retorno_convertido));
        }

    }

    //desfiltrar pessoa
    const desfiltrarPessoas = () => {
        setbtnDesfiltrar(true);
        if (btnDesfiltrar === true) {
            fetch('http://localhost:8080/pessoas/listar', {
                method: 'get',
                headers: {
                    'Content-type': 'application/json',
                    'Accept': 'application/json'
                }
            })
                .then(retorno => retorno.json())
                .then(retorno_convertido => setPessoas(retorno_convertido));
        }

    }


    //desfiltrar contato
    const desfiltrarContato = () => {
        setbtnDesfiltrar(true);
        if (btnDesfiltrar === true) {
            fetch('http://localhost:8080/contatos/listar', {
                method: 'get',
                headers: {
                    'Content-type': 'application/json',
                    'Accept': 'application/json'
                }
            })
                .then(retorno => retorno.json())
                .then(retorno_convertido => setContatos(retorno_convertido));
        }

    }

    //remover Pessoa
    const removerPessoa = () => {

        fetch('http://localhost:8080/pessoas/deletar/' + objPessoa.id, {
            method: 'delete',
            headers: {
                'Content-type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then(retorno => retorno.json())
            .then(retorno_convertido => {

                //mensagem
                alert(retorno_convertido.mensagem);

                //copia do vetor de contatos
                let vetorTemp = [...Pessoas];

                //indice
                let indice = vetorTemp.findIndex((c) => {
                    return c.id === objPessoa.id;

                });

                //remover contato do vetorTemp
                vetorTemp.splice(indice, 1);

                //atualizar o vetor de produtos
                setPessoas(vetorTemp);

                limparFormularioP();

            })
    }

    //remover contato
    const removerContato = () => {

        fetch('http://localhost:8080/contatos/deletar/' + objContato.id, {
            method: 'delete',
            headers: {
                'Content-type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then(retorno => retorno.json())
            .then(retorno_convertido => {

                //mensagem
                alert(retorno_convertido.mensagem);

                //copia do vetor de contatos
                let vetorTemp = [...Contatos];

                //indice
                let indice = vetorTemp.findIndex((c) => {
                    return c.id === objContato.id;

                });

                //remover contato do vetorTemp
                vetorTemp.splice(indice, 1);

                //atualizar o vetor de produtos
                setContatos(vetorTemp);

                limparFormularioC();

            })
    }

    const selecionarPessoa = (indice) => {
        setObjPessoa(Pessoas[indice]);
        setBtnCadastrar(false);
    }

    const selecionarContato = (indice) => {
        setObjContato(Contatos[indice]);
        setBtnCadastrar(false);
    }

    const aoDigitarPessoa = (e) => {
        setObjPessoa({ ...objPessoa, [e.target.name]: e.target.value });
    }

    const aoDigitarContato = (e) => {
        setObjContato({ ...objContato, [e.target.name]: e.target.value });
    }

    const limparFormularioP = () => {
        setObjPessoa({ nome: '', cpf: '', dataNascimento: '', contato: { id: '', nome: '', telefone: '', email: '' } });
        setBtnCadastrar(true);
    }

    const limparFormularioC = () => {
        setObjContato({ id: '', nome: '', telefone: '', email: '' });
        setBtnCadastrar(true);
    }

    const fecharFormularioPessoa = () => {
        setMostrarFormularioPessoa(false);
        setMostrarTabelaPessoa(false);
        setBtnFiltrarPessoaN(false);
        setBtnFiltrarPessoaI(false);

    }

    const fecharFormularioContato = () => {
        setMostrarFormularioContato(false);
        setMostrarTabelaContato(false);
        setBtnFiltrarContatoN(false);
        setBtnFiltrarContatoI(false);

    }

    const listaContatos = (indice) => {
        setMostrarContatos(true);
        setObjPessoa(Pessoas[indice]);
    }



    return (
        <div>
            <h1>Cadastro Pessoas</h1>
            <label class="lead">
                Seja bem vindo! Escolha o que deseja fazer, manipular as Pessoas ou Contatos, mas lembre-se é necessario ao menos 1 contato para cadastrar uma pessoa:
            </label>
            <p></p>
            <input type='button' value='Pessoas' onClick={() => {
                setMostrarFormularioPessoa(true);
                setMostrarTabelaPessoa(true);
                setBtnFiltrarPessoaN(true);
                setBtnFiltrarPessoaI(true);
            }} className="btn btn-info" />
            <input type='button' value='Contatos' onClick={() => {
                setMostrarFormularioContato(true);
                setMostrarTabelaContato(true)
                setBtnFiltrarContatoN(true);
                setBtnFiltrarContatoI(true);
            }} className="btn btn-info" />

            {mostrarFormularioPessoa && (
                <FormularioPessoa
                    botao={btnCadastrar}
                    eventoTeclado={aoDigitarPessoa}
                    eventoTeclado2={aoDigitarContato}
                    cadastrar={cadastrarPessoa}
                    obj={objPessoa}
                    obj2={objContato}
                    cancelar={limparFormularioP}
                    fechar={fecharFormularioPessoa}
                    remover={removerPessoa}
                    alterar={alterarPessoa}

                />
            )}

            {mostrarContatos && (
                <MostrarContatos vetor={Pessoas} />
            )}

            {mostrarTabelaPessoa && (
                <TabelaPessoa vetor={Pessoas}
                    selecionar={selecionarPessoa}
                    obj={objPessoa}
                    eventoTeclado={aoDigitarPessoa}
                    filtrarN={filtrarNomePessoa}
                    desfiltrar={desfiltrarPessoas}
                    filtrarId={filtrarIdPessoa}
                    contatos={listaContatos}

                />
            )}

            {mostrarFormularioContato && (
                <FormularioContato
                    botao={btnCadastrar}
                    eventoTeclado={aoDigitarContato}
                    cadastrar={cadastrarContato}
                    obj={objContato}
                    cancelar={limparFormularioC}
                    fechar={fecharFormularioContato}
                    remover={removerContato}
                    alterar={alterarContato}
                />
            )}

            {mostrarTabelaContato && (
                <TabelaContato vetor={Contatos}
                    selecionar={selecionarContato}
                    obj={objContato}
                    eventoTeclado={aoDigitarContato}
                    filtrarN={filtrarNomeContato}
                    filtrarId={filtrarIdContato}
                    desfiltrar={desfiltrarContato} />
            )}
        </div>
    );
}



export default TelaPrincipal;