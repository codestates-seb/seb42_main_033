import { takeLatest, takeEvery } from 'redux-saga/effects';
import { postActions } from '../slice/postSlice';
import { boardActions } from '../slice/boardSlice';
import {
  registerPostAsync,
  getPostAsync,
  fetchPostAsync,
  updatePostAsync,
  deletePostAsync,
} from './postSaga';
import { getBoardAsync } from './boardSaga';

const { registerPost, getPost, fetchPost, updatePost, deletePost } =
  postActions;
const { getBoard } = boardActions;

export default function* rootWatcher() {
  yield takeLatest(registerPost.type, registerPostAsync);
  yield takeEvery(getPost.type, getPostAsync);
  yield takeEvery(getBoard.type, getBoardAsync);
  yield takeEvery(fetchPost.type, fetchPostAsync);
  yield takeLatest(updatePost.type, updatePostAsync);
  yield takeLatest(deletePost.type, deletePostAsync);
}
//getPost 액션생성함수를 dispatch 하게 되면
//postSaga의 getPostAsync 함수를 호출하도록
