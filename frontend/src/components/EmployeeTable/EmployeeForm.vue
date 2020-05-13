<template>
  <div class="level-item has=text=centered">
    <form>
      <h1 class="title is-4">{{ title }}</h1>
      <div class="field">
        <label class="label">First Name</label>
        <div class="control">
          <input class="input" type="text" v-model="form.firstname" />
        </div>
        <p class="help is-danger" v-show="!isRequiredSatisfied('firstname')">First name is required</p>
      </div>
      <div class="field">
        <label class="label">Last Name</label>
        <div class="control">
          <input class="input" type="text" v-model="form.lastname" />
        </div>
        <p class="help is-danger" v-show="!isRequiredSatisfied('lastname')">Last name is required</p>
      </div>
      <div class="field">
        <label class="label">Address</label>
        <div class="control">
          <input class="input" type="text" v-model="form.address" />
        </div>
        <p class="help is-danger" v-show="!isRequiredSatisfied('address')">Address name is required</p>
      </div>
      <div class="field">
        <label class="label">Phone</label>
        <div class="control">
          <input class="input" type="text" v-model="form.phone" />
        </div>
        <p class="help is-danger" v-show="!isRequiredSatisfied('phone')">Phone is required</p>
      </div>
      <div class="field has-addons">
        <div class="control">
          <button
            class="button is-success"
            @click.stop.prevent="$emit('save', form)"
            :disabled="!isFormValid || !hasChanged"
          >
            <span class="icon is-small">
              <i class="fas fa-save" />
            </span>
            <span>{{ buttonText }}</span>
          </button>
          <button class="button is-danger" style="margin-left: 10px;" @click.stop.prevent="$emit('cancel')">
            <span>Cancel</span>
          </button>
        </div>
      </div>
    </form>
  </div>
</template>
<script>
import Employee from '@/models/Employee'

export default {
  name: 'EmployeeForm',
  props: {
    employee: {
      type: Employee,
      default: () => new Employee({})
    },
    buttonText: {
      type: String,
      default: 'Submit'
    },
    action: {
      type: String,
      default: 'Add'
    }
  },
  data () {
    return {
      form: this.employee.toJSON()
    }
  },
  computed: {
    title () {
      return `${this.action} Employee`
    },
    isFormValid () {
      return (
        !!this.form.firstname && !!this.form.lastname && !!this.form.address && !!this.form.phone
      )
    },
    hasChanged () {
      return !Employee.compare(this.employee, this.form)
    }
  },
  methods: {
    isRequiredSatisfied (fieldName) {
      return this.form[fieldName] !== ''
    }
  }
}
</script>
<style lang="scss" scoped>
form {
  width: 400px;
  text-align: justify;
  .help {
    font-weight: 700;
  }
  .title {
    text-transform: capitalize;
    padding-bottom: 5px;
    border-bottom: 2px solid lightgrey;
    margin-bottom: 10px;
  }
}
</style>
