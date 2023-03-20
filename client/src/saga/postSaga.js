import { put } from 'redux-saga/effects';
import axios from 'axios';
import history from '../utils/history';
import { postActions } from '../slice/postSlice';

export function* registerPostAsync(action) {
  const data = action.payload;

  const response = yield axios.post(
    // `process.env.REACT_APP_API_URL/board/integrated`,
    `process.env.REACT_APP_API_URL/board/integrated`,
    data
  );
  alert('저장되었습니다.');
  history.push(
    `process.env.REACT_APP_API_URL/board/integrated/${response.data.id}`,
    response.data.id
  );
}

export function* getPostAsync(action) {
  const id = action.payload;

  const response = yield axios.get(
    `process.env.REACT_APP_API_URL/board/integrated/${id}`
  );
  // `http://localhost:3000/PostviewPage/${id}`
  // `process.env.REACT_APP_API_URL/board/integrated/${id}`
  const request = yield axios.put(
    `process.env.REACT_APP_API_URL/board/integrated/${id}`,
    // `process.env.REACT_APP_API_URL/board/integrated/${id}`,
    // `http://localhost:3000/PostviewPage/${id}`,
    {
      ...response.data,
      viewCount: parseInt(response.data.viewCount) + 1,
    }
  );
  // console.log(response.data);
  yield put(postActions.getPostAsync(request.data));
}
export function* fetchPostAsync(action) {
  // console.log(action);
  const id = action.payload;

  const response = yield axios.get(
    // `http://localhost:3000/PostviewPage/${id}`
    // `process.env.REACT_APP_API_URL/board/integrated/${id}`
    `process.env.REACT_APP_API_URL/board/integrated/${id}`
  );

  yield put(postActions.getPostAsync(response.data));
}
export function* updatePostAsync(action) {
  const post = action.payload;

  const response = yield axios.put(
    `process.env.REACT_APP_API_URL/board/integrated/${post.id}`,
    // `http://localhost:3000/PostviewPage/${post.id}`,
    post
  );
  alert('수정되었습니다.');
  // console.log(response.data.id);
  history.push(
    `process.env.REACT_APP_API_URL/board/integrated/${response.data.id}`,
    response.data.id
  );
}
export function* deletePostAsync(action) {
  const id = action.payload;
  yield axios.delete(`process.env.REACT_APP_API_URL/board/integrated/${id}`);
  // `http://localhost:3000/PostviewPage/${id}`
  alert('삭제되었습니다.');
  history.push(`/`);
  history.go(0);
}
