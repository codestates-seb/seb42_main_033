import styled from 'styled-components';
import { Link, useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';
import axios from 'axios';
import { GoogleLogin, GoogleOAuthProvider } from '@react-oauth/google';

function LoginPage() {
  const [userId, setUserId] = useState('');
  const [password, setPassword] = useState('');
  const [guest, setGuest] = useState('');
  const navigate = useNavigate();
  const URL = `https://5293-211-217-72-99.jp.ngrok.io`;
  // const clientId =
  //   '830176255460-o74i0j4tfi22top821p6g18c5j33d787.apps.googleusercontent.com';
  // 로그인 같은 비동기 외부파일로.(관심사 불리 위해)
  // const clientId =
  //   '830176255460-o74i0j4tfi22top821p6g18c5j33d787.apps.googleusercontent.com';
  const LoginSubit = async (event) => {
    event.preventDefault();
    if (userId === '' && password === '') {
      alert('회원가입 해야할듯');
    }
    if (userId === '') {
      alert('아이디 확인점');
    }
    if (password === '') {
      alert('비밀번호 확인좀');
    }
    try {
      const response = await axios.post(
        `${process.env.REACT_APP_API_URL}/user/login`,
        {
          email: userId,
          password1: password,
        }
      );
      console.log(response);
      if (response.status === 401) {
        alert('회원가입 해야할듯');
      }
      const accessToken = response.headers.authorization;
      const refreshToken = response.headers.refresh;
      localStorage.setItem('jwtToken', accessToken);
      localStorage.setItem('rfToken', refreshToken);
      //user Id 추가 (병민)
      try {
        const userIdGet = await axios.get(`${URL}/users`, {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
        });
        console.log('userIdGet:', userIdGet.data);
        const user = userIdGet.data.data.find((user) => user.email === userId);
        if (user) {
          const userIdSet = user.userId;
          localStorage.setItem('userId', userIdSet);
        }
        //user Id 추가 (병민)
        navigate('/');
      } catch (error) {
        console.log(error);
      }
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
        `${process.env.REACT_APP_API_URL}/login/guest`,
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
      <GoogleOAuthProvider clientId="830176255460-o74i0j4tfi22top821p6g18c5j33d787.apps.googleusercontent.com">
        <Googlebody>
          <GoogleLogin
            clientId="830176255460-o74i0j4tfi22top821p6g18c5j33d787.apps.googleusercontent.com"
            onSuccess={(res) => console.log(res, '성공')}
            width="500"
            logo_alignment="left"
            size="large"
            theme="filled_black"
            locale="300"
            shape="rectangular"
            onFailure={(res) => console.log(res, '실패')}
            render={(renderProps) => (
              <Googlelogin
                className="social_login_box google"
                onClick={renderProps.onClick}
              >
                <Googlelogin className="social_login_text_box">
                  구글로 시작하기
                </Googlelogin>
              </Googlelogin>
            )}
          />
        </Googlebody>
      </GoogleOAuthProvider>
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
  margin-bottom: 20px;
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
const Googlelogin = styled.button`
  margin-left: 10px;
`;
const Googlebody = styled.button`
  width: 430px;
  height: 50px;
  background-color: #202124;
  border: solid 0px;
  :hover {
    background-color: #555658;
    transition-delay: 0.1s;
  }
`;
export default LoginPage;
