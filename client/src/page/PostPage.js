import { useState, useEffect } from 'react';
import BoardCreateOrEdit from '../components/BoardCreateOrEdit.js';
import { useDispatch, useSelector, shallowEqual } from 'react-redux';
import { postActions } from '../slice/postSlice';

const PostPage = (props) => {
  // const [TitleValue, setTitleValue] = useState('');
  // const [ContentValue, setContentValue] = useState('');
  const dispatch = useDispatch();

  const { id, viewCount, createdAt, modifiedAt, title, content } = useSelector(
    (state) => ({
      id: state.postReducers.id,
      viewCount: state.postReducers.viewCount,
      createdAt: state.postReducers.createdAt,
      modifiedAt: state.postReducers.modifiedAt,
      title: state.postReducers.title,
      content: state.postReducers.content,
    }),
    shallowEqual
  );
  const [IsForUpdate, setIsForUpdate] = useState(false);

  useEffect(() => {
    const searchParams = new URLSearchParams(props.location.search);
    if (searchParams.get('isForEdit') === 'true') {
      dispatch(postActions.fetchPost(props.match.params.postId));
      setIsForUpdate(true);
    }
  }, [id]);
  // const handleTitleChange = (event) => {
  //   setTitleValue(event.currentTarget.value);
  // };
  // const handleContentChange = (value) => {
  //   setContentValue(value);
  // };
  const onRegisterChange = (event) => {
    const { name, value } = event.target;
    dispatch(postActions.changeRegisterInput({ name: name, value: value }));
  };

  const onSubmitPost = (event) => {
    event.preventDefault();
    if (title === '' || title === null || title === undefined) {
      alert('제목을 작성하십시오.');
      return false;
    }

    if (content === '' || content === null || content === undefined) {
      alert('내용을 작성하십시오.');
      return false;
    }

    const post = {
      id: id,
      title: title,
      content: content.replace(/<\/?p[^>]*>/g, ''),
      //<p>로 감싸져서 나오는 것 없애기
      viewCount: viewCount,
      createdAt: createdAt,
      modifiedAt: IsForUpdate ? Date.now() : modifiedAt,
    };

    if (IsForUpdate) {
      dispatch(postActions.updatePost(post));
    } else {
      dispatch(postActions.registerPost(post));
    }
  };
  //   axios
  //     .post(
  //       'ec2-3-38-150-208.ap-northeast-2.compute.amazonaws.com:8080/board/integrated',
  //       {
  //         userId: 1, // userId는 임시 값으로 1로 설정
  //         title: post.title,
  //         content: post.content,
  //         tag: '말머리',
  //       }
  //     )
  //     .then((response) => {
  //       // console.log(post.title);
  //       // console.log( post.contents);
  //       console.log(response);
  //       // 글 작성 완료 후 페이지 이동 등 추가 작업
  //       // navigate(`/board/integrated/${response.data.userId}`);
  //     })
  //     .catch((error) => {
  //       console.log(error); // 오류 발생 시 오류 출력
  //       alert('실패하셨습니다');
  //     });
  // };
  // console.log(ContentValue);
  // };
  return (
    <BoardCreateOrEdit
      TitleValue={title}
      ContentValue={content}
      // handleTitleChange={handleTitleChange}
      // handleContentChange={handleContentChange}
      handleRegisterChange={onRegisterChange}
      handleSubmit={onSubmitPost}
      updateRequest={IsForUpdate}
    />
  );
};

export default PostPage;
