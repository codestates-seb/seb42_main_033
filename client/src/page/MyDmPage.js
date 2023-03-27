import { useState } from 'react';
import styled from 'styled-components';
import MyPageSidebar from '../components/MypageSidebar.jsx';
import { Link } from 'react-router-dom';

/*121212*/
const CommentPageContainer = styled.div`
  font-size: 1vw;
  display: flex;
  flex-direction: column;
  align-items: center;

  justify-content: center;
  position: absolute;
  top: 10%;
  left: 55%;
  transform: translate(-50%, 20%);
`;

const CommentsContainer = styled.div`
  border: 0.05vh solid black;
  padding: 10px;
  width: 45vw;
  height: 100%;
  flex: 1 1 auto;
  width: 60vw;
`;

const CommentContainer = styled.div`
  display: flex;
  align-items: center;
  border-bottom: 0.05vh solid black;
  padding: 1px;
`;

const StyledText = styled.div`
  font-size: 3.5vh;
  font-weight: bold;
  margin-bottom: 2vh;
  position: absolute;
  bottom: 100%;
  left: 1%;
`;

const HeaderContainer = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
`;

const DeleteButton = styled.button`
  background-color: gray;
  width: 13vh;
  height: 6vh;
  color: white;
  padding: 5px 10px;
  margin: auto;
  border: none;
  border-radius: 5px;
  position: absolute;
  right: 10%;
  bottom: -15%;
  cursor: pointer;
`;

const SendBtnContainer = styled.div`
  width: 100%;
  text-align: right;
`;
const SendButton = styled.button`
  background-color: #64b5ff;
  width: 11vh;
  height: 4.5vh;
  color: white;
  padding: 5px 10px;
  margin: auto;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  margin-bottom: 10px;
  font-weight: bold;
`;
const CommentText = styled.label`
  margin-left: 5vw;
  margin-top: 0px;
`;

const comments = [
  {
    id: 1,
    text: '안녕',
  },
  {
    id: 2,
    text: '하세요',
  },
  {
    id: 3,
    text: '오오오오오',
  },
  {
    id: 4,
    text: '오오오오오',
  },
  {
    id: 5,
    text: '오오오오오',
  },
  {
    id: 6,
    text: '오오오오오',
  },
  {
    id: 7,
    text: '오오오오오',
  },
  {
    id: 8,
    text: '오오오오오',
  },
];

function MyDmPage() {
  const [selectedComments, setSelectedComments] = useState([]);

  const handleCheckboxClick = (id) => {
    if (selectedComments.includes(id)) {
      setSelectedComments(selectedComments.filter((cid) => cid !== id));
    } else {
      setSelectedComments([...selectedComments, id]);
    }
  };

  return (
    <>
      <MyPageSidebar />
      <CommentPageContainer>
        <StyledText>쪽지함</StyledText>
        <SendBtnContainer>
          <Link to="/SendDm">
            <SendButton>쪽지 쓰기</SendButton>
          </Link>
        </SendBtnContainer>
        <CommentsContainer>
          <HeaderContainer>선택</HeaderContainer>
          <hr />
          {comments
            .slice()
            .reverse()
            .map((comment) => (
              <CommentContainer key={comment.id}>
                <input
                  type="checkbox"
                  id={`checkbox-${comment.id}`}
                  checked={selectedComments.includes(comment.id)}
                  onChange={() => handleCheckboxClick(comment.id)}
                />
                <CommentText htmlFor={`checkbox-${comment.id}`}>
                  <p>{comment.text}</p>
                </CommentText>
              </CommentContainer>
            ))}
        </CommentsContainer>
        <DeleteButton>삭제</DeleteButton>
      </CommentPageContainer>
    </>
  );
}

export default MyDmPage;
