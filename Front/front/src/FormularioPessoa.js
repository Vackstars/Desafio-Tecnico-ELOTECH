function FormularioPessoa({ botao, eventoTeclado, cadastrar, obj }) {
    return (
        <form>
            <input type='text' value={obj.nome} onChange={eventoTeclado} name= 'nome' placeholder='Nome' />
            <input type='text' value={obj.cpf} onChange={eventoTeclado} name= 'cpf' placeholder='CPF' />
            <input type='text' value={obj.dataNascimento} onChange={eventoTeclado} name= 'dataNascimento' placeholder='Data de Nascimento' />
            <input type='text' value={obj.contatos} onChange={eventoTeclado} name= 'contatos' placeholder='Contatos' />
            {
                botao
                    ?
                    <input type='button' value='Cadastrar' onClick={cadastrar} className="btn btn-primary" />
                    :
                    <div>
                        <input type='button' value='Alterar' className="btn btn-warning" />
                        <input type='button' value='Deletar' className="btn btn-danger" />
                        <input type='button' value='Cancelar' className="btn btn-secondary" />
                    </div>
            }

            


        </form>
    )
}

export default FormularioPessoa;