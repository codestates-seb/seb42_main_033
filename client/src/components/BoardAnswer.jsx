import styled from 'styled-components';
import { FaHeart } from 'react-icons/fa';
import { useState, useEffect } from 'react';
import axios from 'axios';

const BoardAnswer = ({ username, content, id, userId }) => {
  const [comments, setComments] = useState([]);
  const [comlike, setComlike] = useState(0);
  //댓글수정
  const handleEdit = async (userId, editedContent) => {
    const token = localStorage.getItem('jwtToken');
    const config = {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    };
    const body = {
      content: editedContent,
    };
    try {
      const response = await axios.put(
        `${process.env.REACT_APP_API_URL}/board/integrated/${id}/comment/${userId}`,
        body,
        config
      );
      setComments(response.data.comments);
    } catch (error) {
      console.log(error);
    }
  };

  const handleDelete = async (commentId) => {
    const token = localStorage.getItem('jwtToken');
    const config = {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    };
    try {
      const response = await axios.delete(
        `${process.env.REACT_APP_API_URL}/board/integrated/comments/${commentId}`,
        config
      );
      setComments(response.data.comments);
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <AnswerForm>
      <div className="answernickname">인프피 맞습니다{username}</div>
      <div
        className="answerbutton"
        style={{ marginLeft: '730px', fontSize: '13px' }}
      >
        <button onClick={handleEdit}>수정</button>
        <button onClick={handleDelete}>삭제</button>
      </div>

      <div className="answercontent">
        이정도면 괜찮을거 같기도 하고 아닐거 같기도 하고 잘
        모르겠네여어어어어어어어{content}
      </div>
      <div className="answerlike">
        <Like
          onClick={() => {
            setComlike(comlike + 1);
          }}
        />
        {comlike}
      </div>
    </AnswerForm>
  );
};

const Like = styled(FaHeart)`
  color: #64b5ff;
  margin-right: 10px;
`;

const AnswerForm = styled.div`
  color: #767676;
  height: 130px;
  border-bottom: 1px solid black;
`;

export default BoardAnswer;
