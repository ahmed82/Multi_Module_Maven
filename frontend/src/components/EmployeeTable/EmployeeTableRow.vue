<template>
  <tr
    :class="{ 'is-selected': selected }"
    @click.stop="handleClick"
  >
    <td class="employe-table--row--cell">{{ employee.id }}</td>
    <td class="employe-table--row--cell">{{ employee.firstname }}</td>
    <td class="employe-table--row--cell">{{ employee.lastname }}</td>
    <td class="employe-table--row--cell">{{ employee.address }}</td>
    <td class="employe-table--row--cell">{{ employee.phone }}</td>
  </tr>
</template>
<script>
export default {
  name: 'EmployeeTableRow',
  props: {
    employee: {
      type: Object,
      default: () => ({})
    },
    selected: {
      type: Boolean,
      default: false
    }
  },
  methods: {
    handleClick () {
      this.$emit('select-employee', this.employee.id)
    }
  }
}
</script>
<style lang="scss">
  tbody tr {
    cursor: pointer;
  }
  .is-selected {
    background-color: red;
  }
</style>
