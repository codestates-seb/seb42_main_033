import { useState, useCallback } from 'react';
import BoardCreateOrEdit from '../components/BoardCreateOrEdit.js';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import api, { setAuthToken } from '../utils/api';
import { jwtUtils } from '../utils/jwtUtils';
import { toast } from 'react-toastify';

const PostPage = () => {
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const navigate = useNavigate();
  const token = localStorage.getItem('jwtToken');
  setAuthToken(token);
  // const [IsForUpdate, setIsForUpdate] = useState(false);
  const post = {
    title: title,
    content: content.replace(/<\/?p[^>]*>/g, ''),
    //<p>로 감싸져서 나오는 것 없애기
  };
  const onSubmitPost = useCallback(
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
        const formData = new FormData();
        formData.append('title', post.title);
        formData.append('content', post.content);
        formData.append('userid', jwtUtils.getId(token));
        await api.post('/board/integrated', formData);
        await window.alert('등록이 완료되었습니다😎');
        navigate('/PostlistPage');
        console.log(post);
      } catch (e) {
        toast.error('등록이 실패하였습니다😭', {
          position: 'top-center',
        });
      }
    },
    [navigate, post.title, post.content]
  );

  return (
    <BoardCreateOrEdit
      setTitle={setTitle}
      setContent={setContent}
      title={title}
      content={content}
      handleSubmit={onSubmitPost}
      // updateRequest={IsForUpdate}
    />
  );
};

export default PostPage;
