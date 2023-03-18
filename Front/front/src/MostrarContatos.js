import TabelaContato from './TabelaContato';

function MostrarContatos({ contatos }) {

  return (
    <div>
      <h2>Contatos:</h2>
      <TabelaContato vetor={contatos} />
    </div>
  );
}

export default MostrarContatos;