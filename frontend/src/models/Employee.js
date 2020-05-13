/* eslint-disable no-underscore-dangle */
export default class Employee {
  // _ signifies treating these fields as private
  // private fields have been added to JavaScript
  // and Babel handles them, but they are not reactive in Vue
  // using _ as a placeholder until I can resolve the reactivity problem
  _id

  _firstname = ''

  _lastname = ''

  _address = ''

  _phone = ''

  constructor ({
    id, firstname, lastname, address, phone
  }) {
    this._id = id
    this._firstname = firstname || ''
    this._lastname = lastname || ''
    this._address = address || ''
    this._phone = phone || ''
  }

  set id (id) {
    this._id = id
  }

  get id () {
    return this._id
  }

  set firstname (firstname) {
    this._firstname = firstname
  }

  get firstname () {
    return this._firstname
  }

  get lastname () {
    return this._lastname
  }

  set lastname (lastname) {
    this._lastname = lastname
  }

  get address () {
    return this._address
  }

  set address (address) {
    this._address = address
  }

  get phone () {
    return this._phone
  }

  set phone (phone) {
    this._phone = phone
  }

  updateValues ({
    firstname, lastname, address, phone
  }) {
    this._firstname = firstname
    this._lastname = lastname
    this._address = address
    this._phone = phone
  }

  static isEqual (emp1, emp2) {
    return emp1.id === emp2.id
  }

  static compare (emp1, emp2) {
    return emp1.firstname === emp2.firstname &&
    emp1.lastname === emp2.lastname &&
    emp1.address === emp2.address &&
    emp1.phone === emp2.phone
  }

  static clone (emp) {
    return new Employee({
      id: emp.id,
      firstname: emp.firstname,
      lastname: emp.lastname,
      address: emp.address,
      phone: emp.phone
    })
  }

  toJSON () {
    return {
      id: this.id,
      firstname: this.firstname,
      lastname: this.lastname,
      address: this.address,
      phone: this.phone
    }
  }
}
