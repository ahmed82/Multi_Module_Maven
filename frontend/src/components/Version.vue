<template>
    <div>
        {{apiversion}}
    </div>
</template>

<script>
import axios from '../config/axios'
// const instance = axios.create({
//   auth: {
//     username: 'admin',
//     password: '123'
//   }
// })
export default {
  data () {
    return {
      apiversion: ''
    }
  },
  async mounted () {
    const responds = await axios.get('/util/version')
    this.apiversion = responds.data
  }
}
</script>
