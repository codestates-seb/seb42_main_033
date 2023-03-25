import { useNavigate, useParams } from 'react-router-dom';
import { useCallback, useEffect, useState } from 'react';
import api from '../utils/api';
import { Button } from '@mui/material';
import axios from 'axios';
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const EditBoard = () => {
  const token = localStorage.getItem('jwtToken');
  const navigate = useNavigate();
  // URI 파라미터 가져오기
  const { postId } = useParams();
  // 게시판 제목, 내용, 사진
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  // 사용자가 직전에 등록한 게시물의 상태를 그대로 보여주기 위해
  // 컴포넌트가 마운트되고 URI 파라미터에 해당하는 board를 가져와서
  // title, content의 상태를 바꿔줌
  useEffect(() => {
    const getBoard = async () => {
      const { data } = await axios.patch(
        `${process.env.REACT_APP_API_URL}/board/integrated/${postId}`
      );
      return data;
    };
    getBoard().then((result) => {
      setTitle(result.title);
      setContent(result.content);
    });
  }, []);

  const canSubmit = useCallback(() => {
    return content !== '' && title !== '';
  }, [title, content]);

  const handleSubmit = useCallback(async () => {
    try {
      const formData = new FormData();
      formData.append('title', title);
      formData.append('content', content);
      // 수정할 땐 postId를 보내자
      formData.append('id', postId);
      await api.put('/api/board/integrated', formData);
      window.alert('😎수정이 완료되었습니다😎');
      // 이전 페이지로 돌아가기
      window.location.href = `${process.env.REACT_APP_API_URL}/board/integrated/${postId}`;
    } catch (e) {
      // 서버에서 받은 에러 메시지 출력
      toast.error('오류가 발생했습니다!😭', {
        position: 'top-center',
      });
    }
  }, [canSubmit]);

  return (
    <div className="addBoard-wrapper">
      <div className="addBoard-header">게시물 수정하기 🖊️</div>
      <div className="submitButton">
        {canSubmit() ? (
          <Button
            onClick={handleSubmit}
            className="success-button"
            variant="outlined"
          >
            수정하기😃
          </Button>
        ) : (
          <Button className="disable-button" variant="outlined" size="large">
            제목과 내용을 모두 입력하세요😭
          </Button>
        )}
      </div>
      <div className="addBoard-body">
        <BoardCreateOrEdit
          setTitle={setTitle}
          setContent={setContent}
          title={title}
          content={content}
          handleSubmit={handleSubmit}
        />
      </div>
    </div>
  );
};

export default EditBoard;
