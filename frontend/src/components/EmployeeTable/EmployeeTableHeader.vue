<template>
    <thead>
      <tr>
        <th  @click.stop="handleClick('id')">
          <span :class="sortOrder.id">ID</span>
        </th>
        <th style="position: relative;" @click.stop="handleClick('firstname')">
          <span :class="sortOrder.firstname">First Name</span>
        </th>
        <th :class="sortOrder.lastname" @click.stop="handleClick('lastname')">Last Name</th>
        <th :class="sortOrder.address" @click.stop="handleClick('address')">Address</th>
        <th :class="sortOrder.phone" @click.stop="handleClick('phone')">Phone</th>
      </tr>
    </thead>
</template>
<script>
export default {
  name: 'EmployeeTableHeader',
  data () {
    return {
      sortOrder: {
        id: '',
        firstname: '',
        lastname: '',
        address: '',
        phone: ''
      }
    }
  },
  methods: {
    handleClick (sortType) {
      this.sortOrder[sortType] = this.sortOrder[sortType] === 'asc' ? 'desc' : 'asc'
      this.$emit('sort', sortType, this.sortOrder[sortType])
    }
  }
}
</script>
<style lang="scss">
  th {
    cursor: pointer;
  }
  .asc::after {
    content: '\21C8';
    color: darken(lightgray, 20%);
    font-size: 18px;
    position: absolute;
    transform: translate(20px);
  }
  .desc::after {
    content: '\21CA';
    color: darken(lightgray, 20%);
    font-size: 18px;
    position: absolute;
    transform: translateX(20px);
  }

</style>
