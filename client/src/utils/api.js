// import axios from 'axios';
// import { jwtUtils } from './jwtUtils';

// const instance = axios.create({
//   baseURL: 'https://9b33-211-217-72-99.jp.ngrok.io', // API 서버의 주소
// });

// export const setAuthToken = (token) => {
//   if (token) {
//     // token이 존재하는 경우, 로컬 스토리지에 저장
//     localStorage.setItem('jwtToken', token);
//     // axios 인스턴스의 Authorization 헤더에 JWT를 추가
//     instance.defaults.headers.common['Authorization'] = `Bearer ${token}`;
//   } else {
//     // token이 존재하지 않는 경우, 로컬 스토리지에서 제거
//     localStorage.removeItem('jwtToken');
//     // axios 인스턴스의 Authorization 헤더에서 JWT를 제거
//     delete instance.defaults.headers.common['Authorization'];
//   }
// };
// // 요청 인터셉터
// instance.interceptors.request.use(
//   (config) => {
//     // HTTP Authorization 요청 헤더에 jwt-token을 넣음
//     // 서버측 미들웨어에서 이를 확인하고 검증한 후 해당 API에 요청함.
//     const token = localStorage.getItem('jwtToken');
//     try {
//       if (token && jwtUtils.isAuth(token)) {
//         config.headers.Authorization = `Bearer ${token}`;
//       }

//       return config;
//     } catch (err) {
//       console.error('[_axios.interceptors.request] config : ' + err);
//     }
//     return config;
//   },
//   (error) => {
//     // 요청 에러 직전 호출됩니다.
//     return Promise.reject(error);
//   }
// );
// // 응답 인터셉터
// instance.interceptors.response.use(
//   (response) => {
//     //   http status가 200인 경우 응답 성공 직전 호출됨.
//     //   .then() 으로 이어집니다.
//     return response;
//   },
//   (error) => {
//     //   http status가 200이 아닌 경우 응답 에러 직전 호출됨.
//     //   .catch() 으로 이어집니다.
//     return Promise.reject(error);
//   }
// );

// export default instance;
