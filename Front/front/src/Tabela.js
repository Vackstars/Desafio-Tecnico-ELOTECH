function Tabela({ vetor, selecionar }) {
    return (
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


                {
                    vetor.map((obj, indice) => (
                        <tr key={indice} >
                            <td>{indice+1}</td>
                            <td>{obj.nome}</td>
                            <td>{obj.cpf}</td>
                            <td>{obj.dataNascimento}</td>
                            <td><button className='btn btn-primary'>Contatos</button> </td>
                            <td><button onClick={() =>{selecionar(indice)}} className='btn btn-success'>Selecionar</button> </td>
                        </tr>
                    ))
                }


            </tbody>
        </table>
    )
}

export default Tabela;