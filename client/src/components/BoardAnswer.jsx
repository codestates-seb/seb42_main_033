import styled from 'styled-components';
import { FaHeart } from 'react-icons/fa';
import { useState, useEffect } from 'react';
import axios from 'axios';

const BoardAnswer = ({ postId }) => {
  const [content, setContent] = useState('');
  const [comments, setComments] = useState([]);
  const [comlike, setComlike] = useState(0);

  const handleSaveComment = async () => {
    const token = localStorage.getItem('jwtToken');
    const config = {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    };
    const body = {
      postId,
      content,
    };
    try {
      const response = await axios.post('/api/comments', body, config);
      setComments(response.data.comments);
      setContent('');
    } catch (error) {
      console.log(error);
    }
  };

  const handleEdit = async (commentId, editedContent) => {
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
        `/api/comments/${commentId}`,
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
      const response = await axios.delete(`/api/comments/${commentId}`, config);
      setComments(response.data.comments);
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    const getComments = async () => {
      const { data } = await axios.get(`/api/comments/${postId}`);
      return data;
    };
    getComments().then((result) => setComments(result));
  }, [postId]);

  return (
    <AnswerForm>
      <div className="answernickname">인프피 맞습니다</div>
      <div
        className="answerbutton"
        style={{ marginLeft: '730px', fontSize: '13px' }}
      >
        <button onClick={handleEdit}>수정</button>
        <button onClick={handleDelete}>삭제</button>
      </div>

      <div className="answercontent">
        이정도면 괜찮을거 같기도 하고 아닐거 같기도 하고 잘
        모르겠네여어어어어어어어
      </div>
      <div className="answerlike">
        <Like
          onClick={() => {
            setComlike(comlike + 1);
          }}
        />
        {comlike}
      </div>
      <div className="commentform">
        <input
          type="text"
          placeholder="댓글을 입력하세요"
          value={content}
          onChange={(e) => setContent(e.target.value)}
        />
        <button onClick={handleSaveComment}>저장</button>
      </div>
      <div className="commentlist">
        {comments.map((comment) => (
          <div key={comment._id}>{comment.content}</div>
        ))}
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
