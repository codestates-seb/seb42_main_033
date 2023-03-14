import './App.css';
import { HeaderLogin, HeaderLogout } from './components/Header.js';
import { useState } from 'react';
import { Routes, Route } from 'react-router-dom';
import styled from 'styled-components';
import Footer from './components/Footer';
import Login from './page/LoginPage.js';
import Signup from './page/SignupPage.js';
import Modalmain from './components/Modalmain.js';
import Dm from './components/Dm.js';
import PostlistPage from './page/PostlistPage';
import PostPage from './page/PostPage.js';
import PostviewPage from './page/PostviewPage.js';
import MainPage from './page/MainPage';
import MyComments from './components/MyComents';
import MyPost from './components/MyPost.js';
import EditProfilePage from './page/EditProfilePage';
import SignoutPage from './page/SignoutPage';

const Dev = styled.div`
  display: flex;
  width: 100%;
  height: 100%;
  background: none;
  /* max-width: 1264px; */
  padding-top: 50px;
  /* position: relative; */
  justify-content: center;
`;
function App() {
  const [login, setLogin] = useState(false);
  const [modal, setModal] = useState(false);
  return (
    <Dev className="App">
      {login ? (
        <HeaderLogout setLogin={setLogin} login={login} />
      ) : (
        <HeaderLogin setLogin={setLogin} setModal={setModal} modal={modal} />
      )}
      <Routes>
        <Route path="/" element={<MainPage />} />
        <Route path="/PostviewPage" element={<PostviewPage />} />
        <Route path="/Login" element={<Login />} />
        <Route path="/Signup" element={<Signup />} />
        <Route path="/PostlistPage" element={<PostlistPage />} />
        <Route path="/PostPage" element={<PostPage />} />
        <Route path="/Dm" element={<Dm />} />
        <Route path="/MyComments" element={<MyComments />} />
        <Route path="/MyPost" element={<MyPost />} />
        <Route path="/EditProfile" element={<EditProfilePage />} />
        <Route path="/Signout" element={<SignoutPage />} />
      </Routes>
      {modal ? <Modalmain /> : null}
      <Footer />
    </Dev>
  );
}

export default App;
