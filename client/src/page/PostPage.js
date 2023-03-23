import { useState, useCallback } from 'react';
import BoardCreateOrEdit from '../components/BoardCreateOrEdit.jsx';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';

const PostPage = () => {
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const navigate = useNavigate();
  const token = localStorage.getItem('jwtToken');

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
        const response = await axios.post(
          'http://ec2-3-39-227-39.ap-northeast-2.compute.amazonaws.com:8080/board/integrated',
          {
            userId: '1',
            title: post.title,
            content: post.content,
            tag: '말머리',
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
