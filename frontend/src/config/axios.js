import axios from 'axios'

const instance = axios.create({
  baseURL: 'http://localhost:8098/api',
  auth: {
    username: 'admin',
    password: '123'
  }
})

export default instance
