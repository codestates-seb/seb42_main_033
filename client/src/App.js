import './App.css';
import { HeaderLogin, HeaderLogout } from './components/Header.js';
import { useState } from 'react';
import Footer from './components/Footer';
// import Login from './page/LoginPage.js';
import Signup from './page/SignupPage.js';
import Modalmain from './components/Modalmain.js';
import Dm from './components/Dm.js';

function App() {
  const [login, setLogin] = useState(false);
  const [modal, setModal] = useState(false);
  return (
    <div className="App">
      {login ? (
        <HeaderLogout setLogin={setLogin} login={login} />
      ) : (
        <HeaderLogin setLogin={setLogin} setModal={setModal} modal={modal} />
      )}
      {modal ? <Modalmain /> : null}
      {/* <Login /> */}
      <Dm />
      <Signup />
      <Footer />
    </div>
  );
}

export default App;
