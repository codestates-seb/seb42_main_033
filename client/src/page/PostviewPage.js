import BoardCarddetail from '../components/BoardCarddetail.jsx';
import { useParams } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { useEffect } from 'react';
import axios from 'axios';

const PostviewPage = () => {
  return <BoardCarddetail />;
};

export default PostviewPage;
