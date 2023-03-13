import { Link } from 'react-router-dom';
import styled from 'styled-components';

const Signupbody = styled.div`
  position: absolute;
  height: 100%;
  top: 70%;
  left: 50%;
  transform: translate(-50%, -50%);
`;
const Signuptext = styled.div`
  position: relative;
  font-size: 50px;
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
  margin-top: 50px;
  margin-bottom: 90px;
`;
const Signuptextboxinput = styled.input`
  display: flex;
  height: 75px;
  width: 600px;
  color: black;
  border-radius: 10px;
  border: solid 3px gray;
  font-size: 30px;
`;
const SignupbuttonLink = styled(Link)`
  text-decoration: none;
`;
const Signupbutton = styled.button`
  display: block;
  align-items: center;
  justify-content: center;
  height: 75px;
  width: 280px;
  border-radius: 5px;
  color: white;
  background-color: #64b5ff;
  font-size: 30px;
  font-weight: 600;
  margin: auto;
  margin-top: 10%;
  border: solid 10px #64b5ff;
  text-decoration: none;
`;
function Signup() {
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
        <Signuptextboxinput type="text" name="userid" placeholder=" 아이디" />
      </Signuptextbox>
      <Signuptextbox>
        <Signuptextboxinput
          type="text"
          name="userpassword"
          placeholder=" 비밀번호"
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
        <Signupbutton> 회원가입 </Signupbutton>
      </SignupbuttonLink>
    </Signupbody>
  );
}

export default Signup;
