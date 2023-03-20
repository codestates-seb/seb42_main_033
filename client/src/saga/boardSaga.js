import { put } from 'redux-saga/effects';
import axios from 'axios';
import { boardActions } from '../slice/boardSlice';

export function* getBoardAsync() {
  try {
    const responseForBoard = yield axios.get(
      // `process.env.REACT_APP_API_URL/board/integrated`
      `process.env.REACT_APP_API_URL/board/integrated/`
    );
    const boardData = responseForBoard.data;
    yield put(boardActions.getBoardSuccessAsync(boardData));
  } catch (e) {
    yield put(boardActions.getBoardFailedAsync(e.message));
  }
}
