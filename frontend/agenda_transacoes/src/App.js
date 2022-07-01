import { useEffect } from 'react';
import './App.css';
import AgendamentoTransferencia from './components/AgendamentoTransferencia';


function App() {
  useEffect(() => {
    // 👇️ set style on body element
    document.body.style.backgroundColor = '#dadada';

    return () => {
      // 👇️ optionally remove styles when component unmounts
      document.body.style.backgroundColor = null;
    };
  }, []);
  return (
    <AgendamentoTransferencia />
  );
}

export default App;
