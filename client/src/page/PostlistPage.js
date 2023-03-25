import BoardList from '../components/BoardList.jsx';
import axios from 'axios';
import { useEffect, useState } from 'react';

const PostlistPage = () => {
  const [boardList, setBoardList] = useState([
    {
      id: '1',
      userId: '1',
      username: 'wndud',
      title: '제목1',
      content: '내용1',
      tag: '',
      commentCount: '10',
      viewCount: '10',
      likeCount: '10',
      createdAt: '',
      modifiedAt: '',
    },
    {
      id: '2',
      userId: '3',
      username: 'wndud1',
      title: '제목2',
      content: '내용2',
      tag: '',
      commentCount: '20',
      viewCount: '20',
      likeCount: '20',
      createdAt: '',
      modifiedAt: '',
    },
    {
      id: '3',
      userId: '10',
      username: '주영2',
      title: '제목3',
      content: '내용3',
      tag: '',
      commentCount: '30',
      viewCount: '30',
      likeCount: '40',
      createdAt: '',
      modifiedAt: '',
    },
    {
      id: '4',
      userId: '10',
      username: '주영2',
      title: '제목4',
      content: '내용4',
      tag: '',
      commentCount: '30',
      viewCount: '30',
      likeCount: '40',
      createdAt: '',
      modifiedAt: '',
    },
    {
      id: '5',
      userId: '10',
      username: '주영2',
      title: '제목5',
      content: '내용5',
      tag: '',
      commentCount: '30',
      viewCount: '30',
      likeCount: '40',
      createdAt: '',
      modifiedAt: '',
    },
    {
      id: '6',
      userId: '10',
      username: '주영2',
      title: '제목6',
      content: '내용6',
      tag: '',
      commentCount: '30',
      viewCount: '30',
      likeCount: '40',
      createdAt: '',
      modifiedAt: '',
    },
  ]);
  useEffect(() => {
    axios
      .get(`${process.env.REACT_APP_API_URL}/board/integrated`)
      .then((res) => {
        console.log(res.data);
        setBoardList(res.data.boardList);
      })
      .catch((error) => console.log(error.message));
  }, []);

  return (
    <div>
      <BoardList boardList={boardList} />
    </div>
  );
};

export default PostlistPage;
