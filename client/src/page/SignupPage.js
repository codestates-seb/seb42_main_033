import { Link } from 'react-router-dom';
import styled from 'styled-components';
import axios from 'axios';

const Signupbody = styled.div`
  position: absolute;
  height: 100%;
  top: 70%;
  left: 50%;
  transform: translate(-50%, -50%);
`;
const Signuptext = styled.div`
  position: relative;
  font-size: 30px;
  font-weight: 600;
  display: block;
  text-align: center;
`;
const Signuptextbox = styled.div`
  display: flex;
  position: relative;
  justify-content: center;
  height: 2%;
  width: 100%;
  border-radius: 5px;
  margin-top: 14%;
`;
const Signuptextboxinput = styled.input`
  display: flex;
  height: 45px;
  width: 400px;
  color: black;
  border-radius: 3px;
  border: solid 1px gray;
  font-size: 1rem;
`;
const SignupbuttonLink = styled(Link)`
  text-decoration: none;
`;
const Signupbutton = styled.button`
  display: block;
  align-items: center;
  justify-content: center;
  height: 55px;
  width: 160px;
  border-radius: 5px;
  color: white;
  background-color: #64b5ff;
  font-size: 1.5rem;
  font-weight: 600;
  margin: auto;
  margin-top: 15%;
  border: solid 10px #64b5ff;
  text-decoration: none;
  :hover {
    background-color: #79bfff;
    border: solid 10px #79bfff;
  }
`;

function SignupPage() {
  const signUpPost = async () => {
    axios.post(
      `ec2-3-34-51-204.ap-northeast-2.compute.amazonaws.com:8080/v1/members`,
      {
        ID: 'abc1234',
        PASSWORD: 'pw1234',
        NICKNAME: 'hello',
        MBTI: 'ISFP',
      }
    );
  };
  return (
    <Signupbody>
      <Signuptext> 회원가입 </Signuptext>
      <Signuptextbox>
        <Signuptextboxinput
          type="text"
          name="usernickname"
          placeholder=" 닉네임"
        />
      </Signuptextbox>
      <Signuptextbox>
        <Signuptextboxinput
          type="text"
          name="userid"
          placeholder=" 아이디"
          id="userid"
        />
      </Signuptextbox>
      <Signuptextbox>
        <Signuptextboxinput
          type="password"
          name="userpassword"
          placeholder=" 비밀번호"
          id="signupPassword"
        />
      </Signuptextbox>
      <Signuptextbox>
        <Signuptextboxinput
          type="text"
          name="userpasswordco"
          placeholder=" 비밀번호확인"
        />
      </Signuptextbox>
      <Signuptextbox>
        <Signuptextboxinput
          type="text"
          name="userid"
          placeholder=" MBTI를 입력해주세요."
        />
      </Signuptextbox>
      <SignupbuttonLink to="/">
        <Signupbutton
          onClick={() => {
            signUpPost;
          }}
        >
          회원가입
        </Signupbutton>
      </SignupbuttonLink>
    </Signupbody>
  );
}

export default SignupPage;
