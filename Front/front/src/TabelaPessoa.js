function TabelaPessoa({ vetor, selecionar, obj, eventoTeclado, filtrarN, filtrarId, desfiltrar, contatos }) {


    return (
        <div>
            <input type='button' value='FiltrarNome' onClick={() => filtrarN(obj.nome)} className="btn btn-info" />
            <input type='button' value='Filtrar Id' onClick={() => filtrarId(obj.id)} className="btn btn-danger" />
            <input type='button' value='desfiltrar' onClick={() => desfiltrar()} className="btn btn-warning" />
            <p></p>
            <input type='text' value={obj.nome} onChange={eventoTeclado} name='nome' placeholder='Nome' />
            <input type='text' value={obj.id} onChange={eventoTeclado} name='id' placeholder='Id' />

            <table className="table">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Nome</th>
                        <th>CPF</th>
                        <th>Data de Nascimento</th>
                        <th>Contatos</th>
                        <th>Selecionar</th>
                    </tr>
                </thead>
                <tbody>
                    {vetor.map((obj, indice) => (
                        <tr key={indice}>
                            <td>{indice + 1}</td>
                            <td>{obj.nome}</td>
                            <td>{obj.cpf}</td>
                            <td>{obj.dataNascimento}</td>
                            <td><button onClick={() => contatos(indice)} className='btn btn-primary'>Contatos</button></td>
                            <td><button onClick={() => selecionar(indice)} className='btn btn-success'>Selecionar</button></td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default TabelaPessoa;