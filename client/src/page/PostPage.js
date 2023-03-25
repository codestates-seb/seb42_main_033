import { useState, useCallback } from 'react';
import BoardCreateOrEdit from '../components/BoardCreateOrEdit.jsx';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import moment from 'moment';
// import jwtDecode from 'jwt-decode';

const PostPage = () => {
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const navigate = useNavigate();
  // const token = localStorage.getItem('jwtToken');
  // const decodedToken = jwtDecode(token);
  // console.log(decodedToken);
  // const userId = decodedToken.userId;
  // const userId = localStorage.getItem('userId');
  const createdAt = moment().format('YYYY.MM:DD HH:mm:ss');
  const userId = localStorage.getItem('userId');
  const post = {
    userId: userId,
    title: title,
    content: content.replace(/<\/?p[^>]*>/g, ''),
    //<p>로 감싸져서 나오는 것 없애기 1
    createdAt: createdAt,
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
        const response = await axios.post(
          `${process.env.REACT_APP_API_URL}/board/integrated`,
          {
            userId: '1',
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
    <BoardCreateOrEdit
      setTitle={setTitle}
      setContent={setContent}
      title={title}
      content={content}
      handleSubmit={onSubmitPost}
    />
  );
};

export default PostPage;
