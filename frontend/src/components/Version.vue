<template>
    <div>
        {{apiversion}}
    </div>
</template>

<script>
import axios from 'axios'
const instance = axios.create({
  auth: {
    username: 'admin',
    password: '123'
  }
})
export default {
  data () {
    return {
      apiversion: ''
    }
  },
  async mounted () {
    const responds = await instance.get('http://localhost:8098/api/util/version')
    this.apiversion = responds.data
  }
}
</script>
