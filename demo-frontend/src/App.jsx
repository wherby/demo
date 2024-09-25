import { useState } from 'react'

import './App.css'
import ListComponent from './components/ListComponent'
import HeaderComponent from './components/HeaderComponent'
import FooterConponent from './components/FooterConponent'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import IncidentComponent from './components/IncidentComponent'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
    <BrowserRouter>
     <HeaderComponent/>
     <Routes>
      <Route path='/' element={ <ListComponent/>}></Route>
      <Route path='/add-incident' element={ <IncidentComponent/>}></Route>
      <Route path='/edit-incident/:id' element={ <IncidentComponent/>}></Route>
     </Routes>
     
      <FooterConponent/>
      </BrowserRouter>
    </>
  )
}

export default App
