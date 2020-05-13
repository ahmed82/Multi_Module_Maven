<template>
  <div class="container">
      <template>
        <div v-show="action === 'view' || action === 'delete'">
          <h1 class="title is-3" style="text-align: justify;">
            Employees
          </h1>
          <ActionBar
            @action="handleAction"
            :disabled="!selectedEmployeeId"
            @select-all="selectAllEmployees"
            :has-multiple-selections="!!(selectedEmployees.length > 1)"/>
          <table class="table is-striped is-hoverable is-fullwidth">
            <EmployeeTableHeader @sort="sortEmployees"/>
            <tbody>
              <EmployeeTableRow
                class="employee-table"
                v-for="employee in employees"
                :key="employee.id"
                :employee="employee"
                @select-employee="selectEmployee"
                :selected="!!selectedEmployees.includes(employee)"
              />
            </tbody>
          </table>
        </div>
      </template>
      <template >
        <EmployeeForm
          v-if="action === 'edit' || action === 'add'"
          :employee="employeeData"
          @save="handleSave"
          @cancel="action = 'view'"
          :action="action"
          :buttonText="action === 'edit' ? 'Update' : 'Save'"
        />
      </template>

  </div>
</template>
<script>
import EmployeeTableHeader from '@/components/EmployeeTable/EmployeeTableHeader.vue'
import EmployeeTableRow from '@/components/EmployeeTable/EmployeeTableRow.vue'
import EmployeeForm from '@/components/EmployeeTable/EmployeeForm.vue'
import ActionBar from '@/components/ActionBar.vue'
import { getters, setters, methods } from '@/store/employeeStore'

export default {
  name: 'EmployeeTable',
  components: {
    EmployeeTableHeader,
    EmployeeTableRow,
    EmployeeForm,
    ActionBar
  },
  data () {
    return {
      action: 'view'
    }
  },
  created () {
    this.fetchAllEmployees()
  },
  mounted () {
    const tbody = document.querySelector('table tbody')
    tbody.addEventListener('click', this.handleInsideClick)
    window.addEventListener('click', this.handleOutsideClick)
  },
  destroyed () {
    const tbody = document.querySelector('table tbody')
    if (tbody === null) return
    tbody.removeEventListener('click', this.handleInsideClick)
    window.removeEventListener('click', this.handleOutsideClick)
  },
  methods: {
    ...setters,
    ...methods,
    handleInsideClick (evt) {
      evt.stopPropagation()
    },
    handleOutsideClick () {
      if (this.action !== 'view') return

      this.setSelectedEmployees([])
    },
    handleAction (action) {
      if (action === 'delete') {
        this.deleteEmployees()
      }
      this.action = action
    },
    async handleSave (data) {
      if (this.action === 'edit') {
        await this.editEmployee(data)
      } else {
        await this.addEmployee(data)
      }

      this.action = 'view'
    }
  },
  computed: {
    ...getters,
    employeeData () {
      if (this.action === 'edit') {
        const [employee] = this.selectedEmployees
        return employee
      }
      return undefined
    }
  }
}
</script>
<style lang="scss">

</style>
