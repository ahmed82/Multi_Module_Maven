<template>
  <div class="actions-bar">
    <div class="level">
      <div class="field is-grouped has-addons">
        <button
          class="button is-success actions-bar-button control is-small"
          @click.stop.prevent="$emit('action', 'add')"
          :disabled="!disabled"
        >
          <span class="icon is-small">
            <i class="fas fa-plus" />
          </span>
          <span>Add</span>
        </button>
        <button
          class="button is-primary actions-bar-button control is-small"
          @click.stop.prevent="$emit('action', 'edit')"
          :disabled="disabled || hasMultipleSelections"
        >
          <span class="icon is-small">
            <i class="fas fa-edit" />
          </span>
          <span>Edit</span>
        </button>
        <button
          class="button is-primary actions-bar-button control is-small"
          @click.stop.prevent="$emit('select-all')"
        >
          <span class="icon is-small">
            <i class="fas fa-check-square" />
          </span>
          <span>Select All</span>
        </button>
      </div>
      <div class="level-right has-addons">
        <button
          class="button is-danger actions-bar-button control is-small"
          @click.prevent="$emit('action', 'delete')"
          :disabled="disabled"
        >
          <span class="icon is-small">
            <i class="fas fa-times" />
          </span>
          <span>Delete</span>
        </button>
      </div>
    </div>
  </div>
</template>
<script>

export default {
  name: 'ActionBar',
  props: {
    disabled: {
      type: Boolean,
      default: false
    },
    hasMultipleSelections: {
      disabled: {
        type: Boolean,
        default: false
      }
    }
  }

}
</script>
<style lang="scss">
  .actions-bar-button {
    width: 90px;
    font-weight: 700;
  }
</style>
