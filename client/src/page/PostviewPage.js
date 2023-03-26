import BoardCarddetail from '../components/BoardCarddetail.jsx';
import { useParams } from 'react-router-dom';
import { useEffect } from 'react';
import axios from 'axios';

const PostviewPage = () => {
  const { id } = useParams();
  return <BoardCarddetail id={id} />;
};

export default PostviewPage;
