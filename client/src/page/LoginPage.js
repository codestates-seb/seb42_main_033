import styled from 'styled-components';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';
import { useEffect, useState } from 'react';
function LoginPage() {
  const [userId, setUserId] = useState('');
  const [confirmID, setConfirmId] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [guest, setGuest] = useState('');
  const navigate = useNavigate();
  // 로그인 같은 비동기 외부파일로.(관심사 불리 위해)
  const LoginSubit = async (event) => {
    event.preventDefault();
    if (userId === '') {
      setConfirmId('Id cannot be empty.');
    } else {
      setConfirmId('');
    }
    if (password === '') {
      setConfirmPassword('Password cannot be empty.');
    } else {
      setConfirmPassword('');
    }
    try {
      const response = await axios.post(
        `https://9b33-211-217-72-99.jp.ngrok.io/user/login`,
        {
          email: userId,
          password1: password,
        }
      );
      console.log(response);
      const accessToken = response.headers.authorization;
      localStorage.setItem('jwtToken', accessToken);
      navigate('/');
    } catch (error) {
      console.log(error);
    }
  };
  const Guestlogin = async (event) => {
    event.preventDefault();
    try {
      // 게스트 여러개면 재밌을둣
      setGuest('guest');
      const response = await axios.post(
        `https://9b33-211-217-72-99.jp.ngrok.io/user/login/guest`,
        {
          email: guest,
          // 게스트 상태에 guest
          password1: guest,
          // guest
        }
      );
      console.log(response);
      if (response.status === 200) {
        navigate('/');
      }
    } catch (error) {
      console.log(error);
    }
  };
  return (
    <Loginbody>
      <Logintext>로그인</Logintext>
      {console.log(confirmPassword)}
      {console.log(confirmID)}
      <form onSubmit={LoginSubit}>
        <Logintextbox>
          <Logintextboxinput
            type="text"
            name="userid"
            placeholder="  아이디"
            onChange={(e) => setUserId(e.target.value)}
          ></Logintextboxinput>
          {console.log(userId)}
          {console.log(password)}
        </Logintextbox>
        <Logintextbox>
          <Logintextboxinput
            type="password"
            name="userid"
            placeholder="  비밀번호"
            font-size="10rem"
            onChange={(e) => setPassword(e.target.value)}
          ></Logintextboxinput>
        </Logintextbox>
        <Loginbutton type="submit"> 로그인 </Loginbutton>
      </form>
      <Loginguestbody>
        <form onSubmit={Guestlogin}>
          <Loginguest onClick={() => setGuest('guest')}>
            게스트 로그인
          </Loginguest>
        </form>
        <ToSigup to="/Signup"> 회원가입 </ToSigup>
      </Loginguestbody>
    </Loginbody>
  );
}
const Loginbody = styled.div`
  position: absolute;
  height: 100%;
  top: 80%;
  left: 50%;
  transform: translate(-50%, -50%);
`;
const Logintext = styled.div`
  position: relative;
  font-size: 35px;
  font-weight: 600;
  display: block;
  text-align: center;
`;
const Logintextbox = styled.div`
  display: flex;
  position: relative;
  justify-content: center;
  height: 3%;
  width: 100%;
  margin-top: 5%;
  margin-bottom: 6%;
`;
const Logintextboxinput = styled.input`
  display: flex;
  height: 55px;
  width: 430px;
  color: black;
  border-radius: 5px;
  border: solid 1px gray;
  font-size: 20px;
`;
const Loginbutton = styled.button`
  text-decoration: none;
  display: flex;
  align-items: center;
  justify-content: center;
  height: 50px;
  width: 430px;
  border-radius: 5px;
  color: white;
  background-color: #64b5ff;
  margin: auto;
  font-size: 30px;
  font-weight: 600;
  border: solid 10px #64b5ff;
`;
const Loginguestbody = styled.div`
  display: flex;
`;
const Loginguest = styled.button`
  display: flex;
  color: black;
  justify-content: center;
  margin-top: 30px;
  width: 12em;
  font-size: 20px;
  background-color: rgba(0, 0, 0, 0);
  border: solid 0px rgba(0, 0, 0, 0);
`;
const ToSigup = styled(Link)`
  display: flex;
  color: black;
  justify-content: center;
  width: 12em;
  margin-top: 30px;
  font-size: 20px;
  text-decoration: none;
`;
export default LoginPage;
