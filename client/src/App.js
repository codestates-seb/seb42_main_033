import './App.css';
import { HeaderIcon, HeaderLogin } from './components/Header.jsx';
import { useState } from 'react';
import { Routes, Route } from 'react-router-dom';
import styled from 'styled-components';
import Footer from './components/Footer.jsx';
import LoginPage from './page/LoginPage.js';
import SignupPage from './page/SignupPage.js';
import Modalmain from './components/Modalmain.jsx';
import SendDm from './page/SendDm.js';
import PostlistPage from './page/PostlistPage';
import PostPage from './page/PostPage.js';
import PostviewPage from './page/PostviewPage.js';
import MainPage from './page/MainPage';
import MyCommentsPage from './page/MyCommentsPage';
import MyPostPage from './page/MyPostPage.js';
import EditProfilePage from './page/EditProfilePage';
import SignoutPage from './page/SignoutPage';
import Infp from './page/Infplist';

const Dev = styled.div`
  display: flex;
  width: 100%;
  height: 100%;
  background: none;
  padding-top: 50px;
  justify-content: center;
`;
function App() {
  const [modal, setModal] = useState(false);
  const token = localStorage.getItem('jwtToken');
  return (
    <Dev className="App">
      {console.log(token)}
      {token ? (
        <HeaderIcon setModal={setModal} modal={modal} />
      ) : (
        <HeaderLogin />
      )}
      <Routes>
        <Route path="/" element={<MainPage />} />
        {/* 홈화면 */}
        <Route path="/PostviewPage/:id" element={<PostviewPage />} />
        {/* 게시글 화면 */}
        <Route path="/Login" element={<LoginPage />} />
        {/* 로그인 */}
        <Route path="/Signup" element={<SignupPage />} />
        {/* 회원가입 */}
        <Route path="/Signout" element={<SignoutPage />} />
        {/* 회원탈퇴 */}
        <Route path="/PostlistPage" element={<PostlistPage />} />
        {/* 통합게시판 */}
        <Route path="/PostPage" element={<PostPage />} />
        {/* 글쓰기 */}
        <Route path="/Dm" element={<SendDm />} />
        {/* 쪽지보내기 */}
        <Route path="/MyComments" element={<MyCommentsPage />} />
        {/* 내댓글 */}
        <Route path="/MyPost" element={<MyPostPage />} />
        {/* 내 게시글 */}
        <Route path="/EditProfile" element={<EditProfilePage />} />
        {/* 내정보 수정하기 */}
        <Route path="/infp" element={<Infp />} />
      </Routes>
      {modal ? <Modalmain setModal={setModal} modal={modal} /> : null}
      <Footer />
    </Dev>
  );
}

export default App;
