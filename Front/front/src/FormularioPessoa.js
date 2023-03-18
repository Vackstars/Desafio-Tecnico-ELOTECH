

function FormularioPessoa({ botao, eventoTeclado,eventoTeclado2, cadastrar, obj,obj2, fechar, cancelar, remover, alterar }) {
    return (
        <form>
            <input type='text' value={obj.nome} onChange={eventoTeclado} name='nome' placeholder='Nome' />
            <input type='text' value={obj.cpf} onChange={eventoTeclado} name='cpf' placeholder='CPF' />
            <input type='text' value={obj.dataNascimento} onChange={eventoTeclado} name='dataNascimento' placeholder='Data de Nascimento' />
            <label>Digite os dados do contato:</label>
            <p></p>
            <input type='text' value={obj2.id} onChange={eventoTeclado2} name='id' placeholder='Id' />
            <input type='text' value={obj2.nome} onChange={eventoTeclado2} name='nome' placeholder='Nome' />
            <input type='text' value={obj2.telefone} onChange={eventoTeclado2} name='telefone' placeholder='telefone' />
            <input type='text' value={obj2.email} onChange={eventoTeclado2} name='email' placeholder='Email' />
            

            {
                botao
                    ?
                    <div>
                        <input type='button' value='Cadastrar' onClick={cadastrar} className="btn btn-primary" />
                        <input type='button' value='Fechar' onClick={fechar} className="btn btn-secondary" />
                    </div>
                    :
                    <div>
                        <input type='button' value='Alterar' onClick={alterar} className="btn btn-warning" />
                        <input type='button' value='Deletar' onClick={remover} className="btn btn-danger" />
                        <input type='button' value='Cancelar'  onClick={cancelar} className="btn btn-secondary" />
                    </div>
            }
        </form>
    )
}

export default FormularioPessoa;
