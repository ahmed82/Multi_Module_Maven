import axios from 'axios'

const instance = axios.create({
  baseURL: '/api',
  auth: {
    username: 'admin',
    password: '123'
  }
})

export default instance
