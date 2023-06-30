import { Route, Routes } from 'react-router-dom'
import './App.css'
import PokeList from './Routes/PokeList'
import Pokemon from './Routes/Pokemon'

function App() {


  return (
    <>
      <Routes>
        <Route path='/' element={<PokeList/>}/>
        <Route path='/poke/:name' element={<Pokemon/>}/>
      </Routes>
    </>
  )
}

export default App
