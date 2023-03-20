import { createSlice } from '@reduxjs/toolkit';

export const postSlice = createSlice({
  name: 'post',
  initialState: {
    id: 0,
    userId: 0,
    username: null,
    title: '',
    content: '',
    tag: '0',
    commentCount: 0,
    viewCount: 0,
    likeCount: 0,
    createdAt: Date.now(),
    modifiedAt: '',
  },
  reducers: {
    registerPost: (state, { payload: post }) => {
      console.log('게시글 등록 액션 호출 -- registerPost');
      // return {
      //   ...post,
      //   userId: state.userId,
      // };
    },
    getPost: (state, { payload: id }) => {
      console.log('게시글 조회 액션 호출 -- getPost');
    },
    getPostAsync: (state, { payload: post }) => {
      console.log('saga에서 put 액션 호출 -- getPostAsync');
      return {
        ...state,
        id: post.id,
        userId: post.userId,
        username: post.username,
        title: post.title,
        content: post.content,
        tag: post.tag,
        commentCount: post.commentCount,
        viewCount: post.viewCount,
        likeCount: post.likeCount,
        createdAt: post.createdAt,
        modifiedAt: post.modifiedAt,
      };
    },
    fetchPost: (state, { payload: id }) => {
      console.log('게시글 조회 액션 호출 -- fetchPost'); // saga에서 감시용
    },
    updatePost: (state, { payload: post }) => {
      console.log('게시글 수정 액션 호출 -- updatePost'); // saga에서 감시용
    },
    deletePost: (state, { payload: id }) => {
      console.log('게시글 삭제 액션 호출 -- deletePost'); // saga 에서 감시용
    },
    changeRegisterInput: (state, { payload }) => {
      switch (payload.name) {
        case 'title':
          return {
            ...state,
            title: payload.value,
          };

        case 'content':
          return {
            ...state,
            content: payload.value,
          };

        default:
          break;
      }
    },
  },
});

export const postReducers = postSlice.reducer;
export const postActions = postSlice.actions;
