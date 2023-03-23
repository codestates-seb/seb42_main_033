import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { BrowserRouter } from 'react-router-dom';
import { Provider } from 'react-redux';
import store from './store';
import history from './utils/history';
import { GoogleOAuthProvider } from '@react-oauth/google';
// import { configureStore } from '@reduxjs/toolkit';
// import rootReducer from './modules';

// const store = configureStore({
//   reducer: rootReducer,
//   devTools: true,
// });

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <Provider store={store}>
    <React.StrictMode>
      <BrowserRouter history={history}>
        <GoogleOAuthProvider clientId="830176255460-o74i0j4tfi22top821p6g18c5j33d787.apps.googleusercontent.com">
          <App />
        </GoogleOAuthProvider>
      </BrowserRouter>
    </React.StrictMode>
  </Provider>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
