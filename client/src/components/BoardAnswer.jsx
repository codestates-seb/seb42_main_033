import styled from 'styled-components';
import { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';

const BoardAnswer = ({ nickName, content, post, id }) => {
  const [commentsId, setcommentsId] = useState();
  const token = localStorage.getItem('jwtToken');
  const postId = post.id;
  useEffect(() => {
    const getAnswer = async () => {
      try {
        const { data } = await axios.get(
          `${process.env.REACT_APP_API_URL}/board/integrated/${postId}/comment`
        );

        setcommentsId(data.commentsId);
        console.log(data);
      } catch (error) {
        console.log(error);
      }
    };
    getAnswer();
  }, [id]);

  const answerDelete = async () => {
    try {
      const response = await axios.post(
        `${process.env.REACT_APP_API_URL}/board/integrated/${postId}/comment/${commentsId}`,
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
      <div className="answernickname">{nickName}</div>
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

      <div className="answercontent"> {content} </div>
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
