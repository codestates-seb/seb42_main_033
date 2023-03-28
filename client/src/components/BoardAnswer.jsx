import styled from 'styled-components';
import { useEffect, useState } from 'react';
import axios from 'axios';

const BoardAnswer = ({ post, id, comment }) => {
  const [commentsId, setcommentsId] = useState();
  const token = localStorage.getItem('jwtToken');

  const answerDelete = async () => {
    const postId = post.id;
    const commentId = post.comments[0].commentId;
    try {
      const response = await axios.post(
        `${process.env.REACT_APP_API_URL}/board/integrated/${postId}/comment/${commentId}`,
        {
          headers: {
            Authorization: token,
            'Content-Type': 'application/json',
          },
        }
      );
      console.log(response.data);
    } catch (error) {
      console.log(error);
    }
  };
  return (
    <AnswerForm>
      <div className="answernickname">{comment.username}</div>
      <div
        className="answerbutton"
        style={{ marginLeft: '730px', fontSize: '13px' }}
      >
        <AnswerButton>수정</AnswerButton>
        <AnswerButton
          onClick={() => {
            answerDelete();
          }}
        >
          삭제
        </AnswerButton>
      </div>

      <div className="answercontent"> {comment.content} </div>
    </AnswerForm>
  );
};

const AnswerForm = styled.div`
  color: #767676;
  height: 130px;
  border-bottom: 1px solid black;
`;
const AnswerButton = styled.button`
  background-color: rgba(0, 0, 0, 0);
  border: solid 0px rgba(0, 0, 0, 0);
`;

export default BoardAnswer;
