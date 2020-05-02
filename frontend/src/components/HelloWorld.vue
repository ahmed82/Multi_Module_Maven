<template>
  <div class="hello">
    <!-- <button class="”Search__button”"  @click="callRestService()" >CALL Spring Boot REST backend service</button> -->
    <b-btn @click="callRestService()">CALL Spring Boot REST backend service</b-btn>
    <h3 v-show="response.length">{{ response }}</h3>
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
  name: 'HelloWorld',
  props: {
    msg: String
  },
  data () {
    return {
      response: [],
      errors: []
    }
  },
  methods: {
    callRestService () {
      instance
        .get('api/hello')
        .then(response => {
          // JSON responses are automatically parsed.
          this.response = response.data
        })
        .catch(e => {
          this.errors.push(e)
        })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
