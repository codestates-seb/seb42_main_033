import { useState } from 'react';
import BoardCreateOrEdit from '../components/BoardCreateOrEdit.js';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const PostPage = () => {
  const [TitleValue, setTitleValue] = useState('');
  const [ContentValue, setContentValue] = useState('');
  const navigate = useNavigate();
  // const [IsForUpdate, setIsForUpdate] = useState(false);

  const handleTitleChange = (event) => {
    setTitleValue(event.currentTarget.value);
  };
  // console.log(TitleValue);
  const handleContentChange = (value) => {
    setContentValue(value);
  };
  const onSubmitPost = (event) => {
    event.preventDefault();
    if (TitleValue === '' || TitleValue === null || TitleValue === undefined) {
      alert('제목을 작성하십시오.');
      return false;
    }
    if (
      ContentValue === '' ||
      ContentValue === null ||
      ContentValue === undefined
    ) {
      alert('내용을 작성하십시오.');
      return false;
    }
    const post = {
      title: TitleValue,
      contents: ContentValue.replace(/<\/?p[^>]*>/g, ''),
      //<p>로 감싸져서 나오는 것 없애기
    };
    console.log(post);

    axios
      .post('http://localhost:8080/board/integrated', {
        userId: 1, // userId는 임시 값으로 1로 설정
        title: post.title,
        content: post.contents,
      })
      .then((response) => {
        // console.log(post.title);
        // console.log( post.contents);
        console.log(response); // 성공 시 응답 출력
        // 글 작성 완료 후 페이지 이동 등 추가 작업
        navigate(`/board/integrated/${response.data.userId}`);
      })
      .catch((error) => {
        console.log(error); // 오류 발생 시 오류 출력
      });
  };
  // console.log(ContentValue);

  return (
    <BoardCreateOrEdit
      TitleValue={TitleValue}
      ContentValue={ContentValue}
      handleTitleChange={handleTitleChange}
      handleContentChange={handleContentChange}
      handleSubmit={onSubmitPost}
      // updateRequest={IsForUpdate}
    />
  );
};

export default PostPage;
