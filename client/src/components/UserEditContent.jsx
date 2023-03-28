import styled from 'styled-components';
import Button from './Button.jsx';
import MyPageSidebar from './MypageSidebar.jsx';
import axios from 'axios';
import { useState, useEffect } from 'react';

function UserEditContent() {
  const [name, setName] = useState('');
  const [password1, setPassword1] = useState('');
  const [password2, setPassword2] = useState('');
  const [mbti, setMbti] = useState('');

  const userId = localStorage.getItem('userId');
  // const token = localStorage.getItem('jwtToken');

  const handleChangeName = (e) => {
    setName(e.target.value);
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

  const handleClickSubmit = (e) => {
    e.preventDefault();
    axios
      .patch(`${process.env.REACT_APP_API_URL}/users/${userId}`, {
        name,
        password1,
        password2,
        mbti,
      })
      .then((res) => {
        setName(res.data.data.name);
        setPassword1(res.data.data.password1);
        setPassword2(res.data.data.password2);
        setMbti(res.data.data.mbti);
        window.alert('수정 완료');
      })
      .catch(() => {
        window.alert('오류 발생');
      });
  };
  useEffect(() => {
    axios
      .get(`${process.env.REACT_APP_API_URL}/users/${userId}`)
      .then((res) => {
        setName(res.data.data.name);
        setPassword1(res.data.data.password1);
        setPassword2(res.data.data.password2);
        setMbti(res.data.data.mbti);
      })
      .catch(() => {
        window.alert('오류 발생');
      });
  });

  // useEffect(() => {
  //   loadUsers();
  // }, []);

  // const loadUsers = async () => {
  //   const result = await axios.get(
  //     `${process.env.REACT_APP_API_URL}/users/${userId}`
  //   );
  //   setUsers(result.data);
  // };

  return (
    <>
      <MyPageSidebar />
      <Container>
        <Title>내 정보 수정하기</Title>
        <InputWrapper onSubmit={UserEditContent}>
          <Input
            type="text"
            placeholder="닉네임"
            value={name}
            onChange={handleChangeName}
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
          <Button background="#D9D9D9">취소</Button>
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
