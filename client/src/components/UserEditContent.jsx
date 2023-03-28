import styled from 'styled-components';
import Button from './Button.jsx';
import MyPageSidebar from './MypageSidebar.jsx';
import axios from 'axios';
import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

function UserEditContent() {
  const [nickName, setnickName] = useState('');
  const [password1, setPassword1] = useState('');
  const [password2, setPassword2] = useState('');
  const [mbti, setMbti] = useState('');

  const userId = localStorage.getItem('userId');
  const token = localStorage.getItem('jwtToken');
  const navigate = useNavigate();

  const handleChangenickName = (e) => {
    setnickName(e.target.value);
  };

  const handleChangepassword1 = (e) => {
    setPassword1(e.target.value);
  };

  const handleChangepassword2 = (e) => {
    setPassword2(e.target.value);
  };
  const handleChangeMbti = (e) => {
    setMbti(e.target.value);
  };

  const handleClickCancle = (e) => {
    window.alert('수정을 취소하시면, 입력된 정보는 저장되지 않습니다!');
    navigate('/');
  };
  const handleClickSubmit = (e) => {
    e.preventDefault();
    axios
      .patch(
        `${process.env.REACT_APP_API_URL}/users/${userId}`,
        {
          nickName,
          password1,
          password2,
          mbti,
        },
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      )
      .then((res) => {
        setnickName(res.data.nickName);
        setPassword1(res.data.password1);
        setPassword2(res.data.password2);
        setMbti(res.data.mbti);
        window.alert('😎수정이 완료되었습니다😎');
        navigate('/EditProfile');
      })
      .catch((e) => {
        window.alert('잘못된 정보입니다. 수정할 내용을 다시 입력해주세요!😭');
      });
  };
  useEffect(() => {
    axios
      .get(`${process.env.REACT_APP_API_URL}/users/${userId}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((res) => {
        console.log('userId:', userId);
        setnickName(res.data.nickName);
        setMbti(res.data.mbti);
      })
      .catch(() => {
        window.alert('오류 발생');
      });
  }, []);

  return (
    <>
      <MyPageSidebar />
      <Container>
        <Title>내 정보 수정하기</Title>
        <InputWrapper onSubmit={UserEditContent}>
          <Input
            type="text"
            placeholder="닉네임"
            value={nickName}
            onChange={handleChangenickName}
          />
          <Input
            type="text"
            placeholder="MBTI"
            value={mbti}
            onChange={handleChangeMbti}
          />
          <Input
            type="password"
            placeholder="비밀번호"
            value={password1}
            onChange={handleChangepassword1}
          />
          <Input
            type="password"
            placeholder="비밀번호 확인"
            value={password2}
            onChange={handleChangepassword2}
          />
        </InputWrapper>
        <BtnWrapper>
          <Button background="#D9D9D9" onClick={handleClickCancle}>
            취소
          </Button>
          <Button onClick={handleClickSubmit}>수정</Button>
        </BtnWrapper>
      </Container>
    </>
  );
}

const Title = styled.div`
  position: relative;
  font-size: 30px;
  font-weight: bold;
  display: block;
  text-align: center;
`;

const Input = styled.input`
  display: flex;
  font-size: 20px;
  border: solid 1px gray;
  padding: 15px;
  margin: 15px;
  width: 350px;
`;

const InputWrapper = styled.form`
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 50px;
`;

const BtnWrapper = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
`;

const Container = styled.div`
  display: flex;
  flex-direction: column;
  position: absolute;
  top: 52%;
  left: 50%;
  transform: translate(-50%, -50%);
`;
export default UserEditContent;
