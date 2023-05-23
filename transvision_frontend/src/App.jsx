import { useState } from 'react'
// import './App.css'
import SignInSide from './components/SignIn'
import GeneralUserPage from './components/GeneralUserPage'
import AdminUserPage from './components/AdminUserPage'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      {/* <SignInSide/> */}
      {/* <GeneralUserPage/> */}
      <AdminUserPage/>
    </>
  )
}

export default App
