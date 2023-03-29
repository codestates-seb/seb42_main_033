import { useState, useCallback } from 'react';
import BoardCreateOrEdit from '../components/BoardCreateOrEdit.jsx';
import axios from 'axios';
import styled from 'styled-components';
import { useNavigate, Link } from 'react-router-dom';
import { toast } from 'react-toastify';
import moment from 'moment';
const PostPage = () => {
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const navigate = useNavigate();
  const userId = localStorage.getItem('userId');
  const now = new Date();
  now.setHours(now.getHours() + 9);
  const createdAt = now.toLocaleString('ko-KR', { timeZone: 'Asia/Seoul' });

  const post = {
    userId: userId,
    title: title,
    content: content.replace(/<\/?p[^>]*>/g, ''),
    //<p>로 감싸져서 나오는 것 없애기 1
    createdAt: createdAt,
  };

  const handleSubmit = useCallback(
    async (event) => {
      event.preventDefault();
      if (title === '' || title === null || title === undefined) {
        alert('제목을 작성하십시오.');
        return false;
      }
      if (content === '' || content === null || content === undefined) {
        alert('내용을 작성하십시오.');
        return false;
      }

      try {
        const response = await axios.post(
          `http://ec2-43-201-29-212.ap-northeast-2.compute.amazonaws.com:8080/board/integrated`,
          {
            userId: post.userId,
            title: post.title,
            content: post.content,
            tag: '',
          }
        );

        if (response.status === 201) {
          window.alert('등록이 완료되었습니다😎');
          navigate('/PostlistPage');
          console.log(post);
        }
      } catch (e) {
        toast.error('등록이 실패하였습니다😭', {
          position: 'top-center',
        });
      }
    },
    [title, content]
  );

  return (
    <PostWrapper>
      <div className="titlediv">
        <p>글쓰기</p>
      </div>
      <BoardCreateOrEdit
        setTitle={setTitle}
        setContent={setContent}
        title={title}
        content={content}
        handleSubmit={handleSubmit}
      />
      <ButtonContainer>
        <BoardButtonLink to="/PostlistPage">
          <BoardButton className="delete"> 취소 </BoardButton>
        </BoardButtonLink>
        <BoardButtonLink to="/PostviewPage">
          <BoardButton type="submit" className="submit" onClick={handleSubmit}>
            등록
          </BoardButton>
        </BoardButtonLink>
      </ButtonContainer>
    </PostWrapper>
  );
};
const PostWrapper = styled.div`
  @media only screen and (min-width: 1441px) {
    //해상도 1440보다 큰 모니터
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    height: 90vh;
    font-size: 40px;
    gap: 10px;
    margin-top: -30px;
  }
  @media only screen and (max-width: 1440px) {
    height: 800px;
    font-size: 20px;
  }
  .titlediv {
    font-size: 40px;
    font-weight: 600;
    margin-top: 70px;
    margin-bottom: 20px;
  }
`;
const ButtonContainer = styled.div`
  display: flex;
  justify-content: space-between;
  margin-left: 600px;
  width: 220px;
  margin-top: -30px;
  @media only screen and (max-width: 1440px) {
    margin-top: -250px;
  }
`;
const BoardButtonLink = styled(Link)`
  text-decoration: none;
`;
const BoardButton = styled.button`
  width: 106px;
  height: 54px;
  text-align: center;
  font-size: 20px;
  font-weight: 700;
  color: #ffffff;
  border-radius: 10px;
  border: 0;
  letter-spacing: 7px;
  text-decoration: none;
  &.delete {
    background-color: #a1a1a1;
  }
  &.submit {
    background-color: #64b5ff;
  }
`;

export default PostPage;
