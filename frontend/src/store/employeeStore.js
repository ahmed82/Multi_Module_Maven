/* eslint-disable max-len */
import Vue from 'vue'
import axios from '../config/axios'
import Employee from '../models/Employee'

const state = Vue.observable({
  employees: [],
  selectedEmployees: []
})

export const getters = {
  employees () {
    return state.employees
  },
  selectedEmployeeId () {
    const [employee] = state.selectedEmployees
    return employee?.id || 0
  },
  selectedEmployees () {
    return state.selectedEmployees
  }
}

export const setters = {
  setEmployees (employees) {
    state.employees = employees
  },
  setSelectedEmployees (employees) {
    state.selectedEmployees = employees
  }
}

export const methods = {
  async fetchAllEmployees () {
    const res = await axios.get('/employees')
    const employees = await res.data
    setters.setEmployees(employees.map((emp) => new Employee(emp)))
  },
  findEmployeeById (id) {
    return state.employees.find((e) => e.id === id)
  },
  findSelectedEmployeeById (id) {
    return getters.selectedEmployees().find((e) => e.id === id)
  },
  sortEmployees (sortType, dir) {
    const employees = getters.employees()

    employees.sort((emp1, emp2) => {
      let a
      let b

      if (dir === 'asc') {
        a = emp1[sortType].toString().toLowerCase()
        b = emp2[sortType].toString().toLowerCase()
      } else {
        a = emp2[sortType].toString().toLowerCase()
        b = emp1[sortType].toString().toLowerCase()
      }
      if (a < b) return -1
      if (a > b) return 1
      return 0
    })
  },
  selectAllEmployees () {
    const employees = getters.employees()
    setters.setSelectedEmployees([...employees])
  },
  selectEmployee (id) {
    const employee = this.findEmployeeById(id)
    const selectedEmployee = this.findSelectedEmployeeById(id)

    if (selectedEmployee) {
      setters.setSelectedEmployees(
        getters.selectedEmployees().length > 1 ? [selectedEmployee] : getters.selectedEmployees()
      )
    } else {
      setters.setSelectedEmployees([...getters.selectedEmployees(), employee])
    }
  },
  async addEmployee (employee) {
    const res = await axios.post('/employees', new Employee(employee))

    // eslint-disable-next-line no-param-reassign
    employee.id = res.data.id
    setters.setEmployees([...getters.employees(), new Employee(employee)])
  },
  async editEmployee (data) {
    const [employee] = getters.selectedEmployees()
    await axios.patch(`/employees/${employee.id}`, data)
    employee.updateValues(data)
  },
  async deleteEmployees () {
    await axios.delete('/employees', { data: [...getters.selectedEmployees()] })
    setters.setSelectedEmployees([])
    await this.fetchAllEmployees()
  }
}
