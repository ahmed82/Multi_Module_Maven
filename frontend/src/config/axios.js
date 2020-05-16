import axios from 'axios'
const baseURL = '/api'
// if (process?.env?.NODE_ENV === 'development') { baseURL = 'localhost:8098/api' }
const instance = axios.create({
  baseURL,
  auth: {
    username: 'admin',
    password: '123'
  }
})

export default instance
