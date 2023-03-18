function FormularioContato({ botao, eventoTeclado, cadastrar, obj, fechar }) {
    return (
        <form>
            <input type='text' value={obj.nome} onChange={eventoTeclado} name= 'nome' placeholder='Nome' />
            <input type='text' value={obj.email} onChange={eventoTeclado} name= 'email' placeholder='Email' />
            <input type='text' value={obj.telefone} onChange={eventoTeclado} name= 'telefone' placeholder='telefone' />
            {
                botao
                    ?
                    <div>
                    <input type='button' value='Cadastrar' onClick={cadastrar} className="btn btn-primary" />
                    <input type='button' value='Fechar' onClick={fechar} className="btn btn-secondary" />
                    </ div>
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

export default FormularioContato;