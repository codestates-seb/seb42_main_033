import { combineReducers } from 'redux';
import { postReducers } from './postSlice';
import { boardReducers } from './boardSlice';

const rootReducer = combineReducers({ postReducers, boardReducers });
export default rootReducer;
