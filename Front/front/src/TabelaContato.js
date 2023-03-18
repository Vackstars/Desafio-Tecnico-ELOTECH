function TabelaContato({ vetor, selecionar, obj, eventoTeclado, filtrarN, filtrarId, desfiltrar }) {
    return (
        <div>
            <input type='button' value='FiltrarNome' onClick={() => filtrarN(obj.nome)} className="btn btn-info" />
            <input type='button' value='FiltrarId' onClick={() => filtrarId(obj.id)} className="btn btn-danger" />
            <input type='button' value='desfiltrar' onClick={() => desfiltrar()} className="btn btn-warning" />
            <p></p>
            <input type='text' value={obj.nome} onChange={eventoTeclado} name='nome' placeholder='Nome' />
            <input type='text' value={obj.id} onChange={eventoTeclado} name='id' placeholder='Id' />

            <table className="table">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Nome</th>
                        <th>Telefone</th>
                        <th>Email</th>
                        <th>Selecionar</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        vetor.map((obj, indice) => (
                            <tr key={indice}>
                                <td>{indice + 1}</td>
                                <td>{obj.nome}</td>
                                <td>{obj.telefone}</td>
                                <td>{obj.email}</td>
                                <td><button onClick={() => selecionar(indice)} className='btn btn-success'>Selecionar</button></td>
                            </tr>
                        ))
                    }



                </tbody>
            </table>
        </div>
    )
}

export default TabelaContato;