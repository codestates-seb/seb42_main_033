import { useState } from 'react';
import styled from 'styled-components';
import MyPageSidebar from './MypageSidebar.js';

const PageContainer = styled.div`
  display: flex;
`;

const CommentPageContainer = styled.div`
  font-size: 1vw;
  display: flex;
  flex-direction: column;
  align-items: center;

  justify-content: center;
  position: absolute;

  top: 15%;

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
  font-size: 4vh;
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

const CommentText = styled.label`
  margin-left: 5vw;
  margin-top: 0px;
  position: relative;

  & p:last-child {
    color: orange;
    position: absolute;
    left: 100%;
    bottom: 0;
  }
`;

const comments = [
  {
    id: 1,
    text: '안녕',
    comments: [
      {
        id: 1,
        text: '반가워요!',
      },
      {
        id: 2,
        text: '안녕하세요!',
      },
    ],
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

  // {
  //   id: 4,
  //   text: '오오오오오',
  // },
  // {
  //   id: 5,
  //   text: '오오오오오',
  // },
  // {
  //   id: 6,
  //   text: '오오오오오',
  // },
  // {
  //   id: 7,
  //   text: '오오오오오',
  // },
  // {
  //   id: 8,
  //   text: '오오오오오',
  // },

];

function MyPost() {
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

      <PageContainer>
        <MyPageSidebar />
        <CommentPageContainer>
          <StyledText>내 게시글</StyledText>

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
                    <p>({comment.comments ? comment.comments.length : 0})</p>
                  </CommentText>
                </CommentContainer>
              ))}
          </CommentsContainer>
          <DeleteButton> 삭제 </DeleteButton>
        </CommentPageContainer>
      </PageContainer>

      <CommentPageContainer>
        <MyPageSidebar />
        <StyledText>내 게시글</StyledText>

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
                  <p>({comment.comments ? comment.comments.length : 0})</p>
                </CommentText>
              </CommentContainer>
            ))}
        </CommentsContainer>
        <DeleteButton> 삭제 </DeleteButton>
      </CommentPageContainer>
    </>
  );
}

export default MyPost;
